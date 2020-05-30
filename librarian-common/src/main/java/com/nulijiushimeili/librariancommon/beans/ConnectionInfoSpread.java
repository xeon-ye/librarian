package com.nulijiushimeili.librariancommon.beans;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConnectionInfoSpread {

    /**
     * id
     */
    private Integer id;

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
     * 主机名称
     */
    private String hostName;


    /**
     * IP地址
     */
    private String ip;


    /**
     * 端口号
     */
    private Integer port;

    /**
     * 连接标识， 类比Oracle的服务名连接、sid连接等
     */
    private String connFlag;

    /**
     * 驱动名称
     */
    private String driverName;


    /**
     * 驱动类名称
     */
    private String driverClassName;

    /**
     * 连接url
     */
    private String url;


    /**
     * 数据库名称
     */
    private String dbName;


    /**
     * 数据库描述信息
     */
    private String dbDesc;


}
