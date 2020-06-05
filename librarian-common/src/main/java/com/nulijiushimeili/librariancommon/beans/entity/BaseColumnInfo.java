package com.nulijiushimeili.librariancommon.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/******************************
 * @Project: librarian-parent
 * @FileName: BaseColumnInfo.java
 * @ClassName: BaseColumnInfo
 * @time 2020/5/11 21:20
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseColumnInfo {

    /**
     * 列id
     */
    private Integer id;

    /**
     * 列对应的 元数据 id
     */
    private Integer metadataId;

    /**
     * 表
     */
    private String metadataName;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列中文名称，提示名称
     */
    private String columnTitle;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * 列长度
     */
    private Integer columnLength;

    /**
     * 列精度
     */
    private Integer columnPrecision;

    /**
     * 是否允许为空
     */
    private Integer isNullable;

    /**
     * 列类型
     */
    private String indexType;

    /**
     * 列注释信息
     */
    private String comment;

    /**
     * 列默认值
     */
    private String defaultValue;

    /**
     * 列是否为分区字段
     */
    private Integer isPartitionField;

}
