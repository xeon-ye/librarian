package com.nulijiushimeili.librarian.beans.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class BaseMetadataEntity {

    private Integer id;
    private Integer dbId;          // 与数据类型关联的id
    private String connName;
    private String dbName;
    private String metadataName;
    private String comment;
    private String isIndexed;
    private String physicalLocation;
    private String primaryUser;
    private String tableName;
    private String chineseName;
    private String metadataLevel;
    private String businessName;
    private String hiveSepartor;
    private Integer partition;     // 分区数
    private Integer replica;          // 副本数

    private List<Integer> idList;          //批量操作id的集合

}
