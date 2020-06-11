package com.nulijiushimeili.mysql5.config;

import org.springframework.stereotype.Component;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-11 17:48
 * @Desc: TODO
 ********************************************************/



public class Mysql5ConstStringConfig {

    /**
     * 列出所有的数据库的 SQL 查询语句
     */
    private static final String listDatabaseSql = "show databases";

    /**
     * 列出所有的表的 SQL 查询语句
     */
    private static final String listTableSql = "show tables";

    /**
     *  查询表结构的  SQL
     */
    private static final String queryTableStructSql = "SELECT TABLE_SCHEMA AS dbName,\n" +
            "TABLE_NAME AS tableName,\n" +
            "COLUMN_NAME AS  columnName,\n" +
            "ORDINAL_POSITION AS columnId,\n" +
            "COLUMN_DEFAULT AS columnDefaultValue,\n" +
            "IS_NULLABLE AS isNullable,\n" +
            "DATA_TYPE AS columnType,\n" +
            "CHARACTER_MAXIMUM_LENGTH AS varcharLength,\n" +
            "COLUMN_KEY AS keyType,\n" +
            "COLUMN_COMMENT AS columnComment,\n" +
            "EXTRA AS columnChangeMethod\n" +
            "FROM INFORMATION_SCHEMA.COLUMNS \n" +
            "WHERE TABLE_SCHEMA = '%s' \n" +
            "and TABLE_NAME = '%s' ";

    /**
     * 创建表结构的 SQL
     */
    private static final String createTableSructSql = "";


    /**
     * 修改表结构的 SQL
     */
    private static final  String alterTableSql = "";




    /**
     * 查询表中的数据条数
     */
    private static final String countTableRecordsSql = "select CREATE_TIME, UPDATE_TIME, DATA_LENGTH, TABLE_ROWS " +
            "FROM INFORMATION_SCHEMA.TABLES " +
            "WHERE TABLE_SCHEMA = '%s' " +
            "and TABLE_NAME = '%s'";




}
