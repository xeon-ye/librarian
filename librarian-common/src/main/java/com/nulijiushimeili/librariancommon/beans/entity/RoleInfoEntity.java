package com.nulijiushimeili.librariancommon.beans.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleInfoEntity {

    /**
     * id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 资源用户名称
     */
    private String resourceUserName;

    /**
     * 资源用户密码
     */
    private String resourcePassword;
    /**
     * 角色创建时间
     */
    private Date createTime;

    /**
     * 更新角色时间
     */
    private Date updateTime;


}

