package com.nulijiushimeili.librarian.beans.entity;

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

    private Integer id;
    private Integer metadataId;

    private String columnName;
    private String tableName;
    private String columnType;
    private Integer columnLength;
    private Integer columnPrecision;
    private Integer isNullable;
    private String indexType;
    private String comment;
    private String defaultValue;
    private Integer isPartitionField;

}
