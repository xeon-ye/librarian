package com.nulijiushimeili.librariancommon.beans.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/******************************
 * @Project: librarian-parent
 * @FileName: BaseMetadataEntity.java
 * @ClassName: BaseMetadataEntity
 * @time 2020/5/11 20:42
 * @version 1.00
 * @author nulijiushimeili
 * @Description:
 *
 *    lombok \@Accessors(chain = true)
 *    Accessor的中文含义是存取器，@Accessors用于配置getter和setter方法的生成结果，下面介绍三个属性
 *
 *    \@Accessors(fluent = true)   // 省略前缀，getter/setter 方法的名字和属性的名字相同。
 *    \@Accessors(chain = true)    // 生成的 setter 方法是链式调用，返回对象本身
 *    \@Accessors(prefix = true)   // 默认， 生成的getter/setter方法遵循驼峰命名
 *
 *
 ******************************/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Accessors(chain = true)    // setter 方法使用链式调用
public class BaseMetadataEntity {

    /**
     * 元数据 id
     */
    private Integer id;

    /**
     * 元数据所属 数据库id
     */
    private Integer dbId;

    /**
     * 元数据所在库的连接名称
     */
    private String connName;

    /**
     * 元数据 所在 库
     */
    private String dbName;

    /**
     * 元数据名称， hdfs路径，RDBMS表名称
     */
    private String metadataName;

    /**
     * 表描述信息
     */
    private String comment;

    /**
     *
     */
    private String isIndexed;

    /**
     * 物理路径
     */
    private String physicalLocation;

    /**
     * 分区数
     */
    private Integer partitionNum;

    /**
     * 副本数
     */
    private Integer replica;


    /**
     * hive 字段分隔符
     */
    private String hiveFieldSeparator;

    /**
     * 批量操作id的集合
     */
    private List<Integer> idList;

    /**
     * 列信息集合
     */
    private List<BaseColumnInfo> columnInfoList;

}
