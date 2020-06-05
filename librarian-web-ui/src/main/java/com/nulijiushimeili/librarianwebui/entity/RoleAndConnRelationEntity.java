package com.nulijiushimeili.librarianwebui.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleAndConnRelationEntity {

    //-- 用户角色和连接实例之间的对应关系
    //DROP TABLE IF EXISTS ROLE_CONN_RELATION;
    //CREATE TABLE ROLE_CONN_RELATION(
    //ID INT(10) NOT NULL AUTO_INCREMENT COMMENT '唯一主键id',
    //ROLE_ID INT(10) NOT NULL COMMENT '资源角色id',
    //CONN_NAME VARCHAR(100) NOT NULL COMMENT '连接实例id',
    //PRIMARY KEY (ID)
    //)ENGINE=InnoDB DEFAULT CHARSET=utf8;


    private Integer id;
    private Integer roleId;
    private String roleName;
    private String connName;

}
