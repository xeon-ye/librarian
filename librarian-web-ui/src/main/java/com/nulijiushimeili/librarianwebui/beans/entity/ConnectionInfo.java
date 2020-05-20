package com.nulijiushimeili.librarianwebui.beans.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/******************************
 * @Project: librarian-parent
 * @FileName: ConnectionInfo.java
 * @ClassName: ConnectionInfo
 * @time 2020/5/11 20:42
 * @version 1.00
 * @author nulijiushimeili
 * @Description:  连接数据源的连接信息
 ******************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConnectionInfo {



    /**
     * 数据源的名称，唯一标识一个数据源
     */
    @Excel(name="连接名称", orderNum = "0")
    private String connName;

    /**
     * 数据源的类型
     */
    private String datasourceType;

    /**
     * 数据源的版本号
     */
    private String datasourceVersion;

    /**
     * 连接数据源使用的用户名
     */
    private String username;

    /**
     * 连接数据源使用的密码
     */
    private String password;

    /**
     * 数据源的命名空间， 类比  Oracle 的服务名
     */
    private String namespace;

    /**
     * 连接模式:  cluster/standalone
     */
    private String connectionMode;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 连接是否可用，数据源是否存活
     */
    private Boolean alive;

    /**
     * 数据源的描述
     */
    private String resourceArgs;

    /**
     * 数据源节点列表
     */
    private List<HostInfo> hostInfoList;

    /**
     * 数据库 列表
     */
    private List<DatabaseInfo> databaseInfoList;









}
