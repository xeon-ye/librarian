package com.nulijiushimeili.librarian.beans.entity;

import lombok.*;

/******************************
 * @Project: librarian-parent
 * @FileName: HostInfo.java
 * @ClassName: HostInfo
 * @time 2020/5/11 21:15
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class HostInfo {

    /**
     * id
     */
    private Integer id;

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


}
