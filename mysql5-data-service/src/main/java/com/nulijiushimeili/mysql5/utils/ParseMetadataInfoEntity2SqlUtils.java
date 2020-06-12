package com.nulijiushimeili.mysql5.utils;


import com.nulijiushimeili.librariancommon.beans.entity.BaseColumnInfo;
import com.nulijiushimeili.librariancommon.beans.entity.BaseMetadataEntity;
import com.nulijiushimeili.librariancommon.beans.entity.ConnectionInfo;
import com.nulijiushimeili.librariancommon.beans.entity.CreateTableEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author 努力就是魅力
 * 生成创建表结构的 建表SQL语句
 */
@Slf4j
@Component
public class ParseMetadataInfoEntity2SqlUtils {


    /**
     * 生成建表sql
     * create table newTable (
     * name varchar(255)  not null comment 'user name' ,
     * gender varchar(255) default male comment 'gender' ,
     * address varchar(255)  default null comment 'address' ,
     * ) engine = innodb default charset = utf8
     *
     * @return
     */
    public String generateCreateTableSQLForMysql(
            BaseMetadataEntity baseMetadataEntity,
            List<BaseColumnInfo> baseColumnInfoList
    ) {
        String tableName = baseMetadataEntity.getMetadataName();
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("create table %s ( \n", tableName));
//        sql.append(String.format(" %s ( ",tableName));
        for (BaseColumnInfo column : baseColumnInfoList) {
            sql.append(column.getColumnName()).append(" ")             // 添加列名
                    .append(column.getColumnType()).append(" ");
            if (column.getColumnLength() == 0) {

            } else {
                sql.append("(")       // 添加列类型
                        .append(column.getColumnLength()).append(") ");
            }
            if (null != column.getDefaultValue() && !"".equals(column.getDefaultValue())) {
                sql.append("default \'").append(column.getDefaultValue()).append("\' ");
            } else {
                if (column.getIsNullable() == 0) {
//                    sql.append(" default null ");             // 默认允许空值
                } else if (column.getIsNullable() == 1) {
                    sql.append(" not null ");                 // 非空
                }
            }
            if (!"-1".equals(column.getIndexType())) {
                sql.append(column.getIndexType() + " ");
            }

            sql.append(String.format("comment '%s',\n", column.getComment()));
        }

        sql.replace(sql.toString().length() - 2, sql.toString().length(), " ");
//        sql.append(" ) ");
        sql.append("\n) engine = innodb default charset = utf8");

        log.info("生成的mysql的建表语句是：\n" + sql.toString());
        return sql.toString();
    }


    /**
     * 生成hive的建表sql
     *
     * @return
     */
    public String generateCreateTableSQLForHive(
                                                BaseMetadataEntity baseMetadataEntity,
                                                List<BaseColumnInfo> baseColumnInfoList
    ) {
        String tableName = baseMetadataEntity.getMetadataName();
        String separator =baseMetadataEntity.getHiveFieldSeparator();

        switch (separator) {
            case "空格":
                separator = "\" \"";
                break;
            case "制表符":
                separator = "\t";
                break;
            default:
                break;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(String.format("create table %s ( \n", tableName));
        StringBuilder partitionFields = new StringBuilder();
        for (BaseColumnInfo column : baseColumnInfoList) {
            if (column.getIsPartitionField() == 1) {
                partitionFields.append(column.getColumnName()).append(" ").append(column.getColumnType()).append(",");
            } else {
                sql.append(column.getColumnName()).append(" ")
                        .append(column.getColumnType())
                        .append(String.format(" comment '%s',\n", column.getComment()));
            }

        }
        sql.replace(sql.toString().length() - 2, sql.toString().length(), " ");
        sql.append(" ) ");
        if (!"".equals(partitionFields.toString())) {
            sql.append("\n partitioned by (").append(partitionFields.replace(partitionFields.toString().length() - 2, partitionFields.toString().length(), "")).append(")");
        }
        sql.append("\n row format delimited fields terminated by ").append(separator);

        log.info("生成的hive的建表语句是：\n" + sql.toString());

        return sql.toString();
    }


    /**
     * 生成Phoenix的建表sql
     *
     * @return
     */
    public String generateCreateTableSQLForPhoenix(
            BaseMetadataEntity baseMetadataEntity,
            List<BaseColumnInfo> baseColumnInfoList
    ) {
        String tableName = baseMetadataEntity.getMetadataName();

        StringBuilder sql = new StringBuilder();
//        sql.append(String.format("create table %s ( \n", "\""+tableName+"\""));
        sql.append(String.format("create table %s ( \n", "" + tableName + ""));
        for (BaseColumnInfo column : baseColumnInfoList) {
            sql.append(column.getColumnName()).append(" ")             // 添加列名
                    .append(column.getColumnType()).append(" "); //添加类型
            if (column.getIsNullable() == 0) {
                sql.append(" , ");             // 默认允许空值
            } else if (column.getIsNullable() == 1) {
                sql.append(" not null ,");                 // 非空
            }
        }
        String pk = baseColumnInfoList.stream().filter(x -> !x.getIndexType().equals("-1")).map(x -> x.getColumnName()).collect(Collectors.joining(","));
        sql.append("\n constraint my_pk PRIMARY KEY (" + pk + "))");

        log.info("生成的Phoenix的建表语句是：\n" + sql.toString());
        return sql.toString();
    }


    /**
     * create table DEMO(
     * id   VARCHAR2(32) not null,
     * name VARCHAR2(255) not null,
     * age  NUMBER(10) default '18' not null
     * );
     * comment on column DEMO.id
     * is '????????';
     * alter table DEMO
     * add constraint ID primary key (ID)
     * using index;
     * alter table DEMO
     * add constraint NAME unique (NAME)
     * using index;
     */
    public String generateCreateTableSQLForOracle(
            BaseMetadataEntity baseMetadataEntity,
            List<BaseColumnInfo> baseColumnInfoList
    ) {
        String tableName = baseMetadataEntity.getDbName() + "." + baseMetadataEntity.getMetadataName();
        StringBuilder sql = new StringBuilder();
        StringBuilder sqlComment = new StringBuilder();
        StringBuilder sqlIndexType = new StringBuilder();
        sql.append(String.format("create table %s ( \n", tableName));
        int i = 1;
        for (BaseColumnInfo column : baseColumnInfoList) {
            sql.append(column.getColumnName() + " ");          // 添加列名
            if (column.getColumnLength() == 0) {
                sql.append(column.getColumnType() + " ");     // 添加列类型
            } else {
                sql.append(column.getColumnType() + "(" + column.getColumnLength() + ")");
            }
            if (null != column.getDefaultValue() && !"".equals(column.getDefaultValue())) {
                if (i == baseColumnInfoList.size()) {
                    if (column.getIsNullable() == 1) {
                        sql.append(" default \'" + column.getDefaultValue() + "\' not null\n);\n");
                    } else {
                        sql.append(" default \'" + column.getDefaultValue() + "\'\n);\n");
                    }
                } else {
                    if (column.getIsNullable() == 1) {
                        sql.append(" default \'" + column.getDefaultValue() + "\' not null,\n");
                    } else {
                        sql.append(" default \'" + column.getDefaultValue() + "\',\n");
                    }
                }
            } else {
                if (i == baseColumnInfoList.size()) {
                    if (column.getIsNullable() == 1) {
                        sql.append(" not null\n);\n");                 // 非空
                    } else {
                        sql.append("\n);\n");
                    }
                } else {
                    if (column.getIsNullable() == 1) {
                        sql.append(" not null,\n");                 // 非空
                    } else {
                        sql.append(",\n");
                    }
                }

            }

            if (null != column.getComment() && !"".equals(column.getComment())) {
                sqlComment.append("comment on column " + tableName + "." + column.getColumnName() + "\nis " + "\'" + column.getComment() + "\';\n");
            }


            if (column.getIndexType() != null && !"-1".equals(column.getIndexType())) {
//                sqlIndexType.append("alter table " + tableName + "\nadd constraint " + column.getColumnName() + " " + column.getIndexType() + " ("
//                        + column.getColumnName() + ")\nusing index;\n");

                sqlIndexType.append("alter table " + tableName + "\nadd primary key ("
                        + column.getColumnName() + ")\nusing index;\n");
            }

            i++;

        }


        String finalSql = sql.toString() + sqlComment.toString() + sqlIndexType.toString();
        log.info("生成的Oracle的建表语句是：\n" + finalSql);
        return finalSql;
    }


    /**
     * create table newTable (
     * name varchar(255)  not null comment 'user name' ,
     * gender varchar(255) default male comment 'gender' ,
     * address varchar(255)  default null comment 'address' ,
     * ) engine = innodb default charset = utf8
     *
     * @param args
     */
//    public static void main(String[] args) {
////         测试创建sql的代码
//        BaseMetadataEntity baseMetadataEntity = new BaseMetadataEntity();
//        baseMetadataEntity.setTableName("test_table");
//
//        DataTypeEntity dataTypeEntity = new DataTypeEntity();
//        dataTypeEntity.setUsername("ccs");
//
//
//        List<BaseColumnInfo> columnList = new ArrayList<>();
//        BaseColumnInfo baseColumnMetadataEntity = new BaseColumnInfo();
//        baseColumnMetadataEntity.setColumnName("name");
//        baseColumnMetadataEntity.setColumnType("varchar");
//        baseColumnMetadataEntity.setColumnLength(255);
//        baseColumnMetadataEntity.setComment("user name");
//        baseColumnMetadataEntity.setIsNullable(1);
//        columnList.add(baseColumnMetadataEntity);
//
//        BaseColumnInfo baseColumnMetadataEntity1 = new BaseColumnInfo();
//        baseColumnMetadataEntity1.setColumnType("varchar");
//        baseColumnMetadataEntity1.setColumnLength(255);
//        baseColumnMetadataEntity1.setComment("gender");
//        baseColumnMetadataEntity1.setDefaultValue("male");
//        baseColumnMetadataEntity1.setIsNullable(1);
//        baseColumnMetadataEntity1.setColumnName("gender");
//        columnList.add(baseColumnMetadataEntity1);
//
//        BaseColumnInfo baseColumnMetadataEntity2 = new BaseColumnInfo();
//        baseColumnMetadataEntity2.setColumnType("varchar");
//        baseColumnMetadataEntity2.setColumnLength(255);
//        baseColumnMetadataEntity2.setComment("address");
//        baseColumnMetadataEntity2.setIsNullable(0);
//        baseColumnMetadataEntity2.setColumnName("address");
//        columnList.add(baseColumnMetadataEntity2);
//
//        CreateTableEntry createTableEntry = new CreateTableEntry();
//        createTableEntry.setDataTypeEntity(dataTypeEntity);
//        createTableEntry.setBaseColumnInfoList(columnList);
//        createTableEntry.setBaseMetadataEntity(baseMetadataEntity);
//        RegistryTableParamParseUtils registryTableParamParseUtils = new RegistryTableParamParseUtils();
//        String sql1 = registryTableParamParseUtils.generateCreateTableSQLForMysql(createTableEntry);
//        System.out.println("mysql 建表语句");
//        System.out.println(sql1);
//
//
//        System.out.println("创建oracle表");
//        String sql2 = registryTableParamParseUtils.generateCreateTableSQLForOracle(createTableEntry);
//        System.out.println(sql2);
//
//        System.out.println("创建hive 表");
//        baseColumnMetadataEntity.setColumnType("string");
//        baseColumnMetadataEntity1.setColumnType("string");
//        baseColumnMetadataEntity2.setColumnType("string");
//        String sql = registryTableParamParseUtils.generateCreateTableSQLForHive(createTableEntry);
//        System.out.println(sql);
//
//    }


}
