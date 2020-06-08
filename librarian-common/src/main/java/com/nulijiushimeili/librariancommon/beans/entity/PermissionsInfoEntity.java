package com.nulijiushimeili.librariancommon.beans.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionsInfoEntity {


    private String resourceTypeName;
    private String connName;
    private String dbName;
    private String tableName;
    private String resourceUserName;

    private Integer id;
    private Integer roleId;
    private Integer metadataId;
    private Date createTime;
    private Date updateTime;
    private Integer readPermission = 0;
    private Integer writePermission = 0;
    private Integer executePermission = 0;
    private Integer createAnyTable = 0;
    private Integer dropAnyTable = 0;
    private Integer alterAnyTable = 0;
    private Integer grantPermission = 0;
    private Integer deletePermission = 0;
    private Integer indexPermission = 0;
    private Integer insertPermission = 0;
    private Integer selectPermission = 0;
    private Integer updatePermission = 0;


}
