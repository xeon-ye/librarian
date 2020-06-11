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


    /**
     *  数据源服务的类型
     */
    private String resourceTypeName;
    /**
     * 数据源的名称
     */
    private String connName;
    /**
     *  数据库的名称
     */
    private String dbName;
    /**
     *  元数据的名称
     */
    private String tableName;
    /**
     *  用户名称
     */
    private String resourceUserName;




    /**
     *  权限信息对应的id
     */
    private Integer id;
    /**
     *  角色信息对应的id
     */
    private Integer roleId;
    /**
     * 元数据对应的id
     */
    private Integer metadataId;
    /**
     * 权限信息创建的时间
     */
    private Date createTime;
    /**
     * 权限信息的更新时间
     */
    private Date updateTime;
    /**
     *  文件的读权限
     */
    private Integer readPermission = 0;
    /**
     * 文件的写权限
     */
    private Integer writePermission = 0;
    /**
     *  文件的执行权限
     */
    private Integer executePermission = 0;
    /**
     *  创建表的权限
     */
    private Integer createAnyTable = 0;
    /**
     * 删除表的权限
     */
    private Integer dropAnyTable = 0;
    /**
     *  修改表的权限
     */
    private Integer alterAnyTable = 0;
    /**
     *  授权的权限
     */
    private Integer grantPermission = 0;

    /**
     *  创建索引的权限
     */
    private Integer indexPermission = 0;
    /**
     *  删除数据的权限
     */
    private Integer deletePermission = 0;

    /**
     * 插入数据的权限
     */
    private Integer insertPermission = 0;
    /**
     * 查询数据的权限
     */
    private Integer selectPermission = 0;
    /**
     * 更新数据的权限
     */
    private Integer updatePermission = 0;



}
