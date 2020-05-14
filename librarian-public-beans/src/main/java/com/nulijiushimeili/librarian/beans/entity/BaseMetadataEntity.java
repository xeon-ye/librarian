package com.nulijiushimeili.librarian.beans.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
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
