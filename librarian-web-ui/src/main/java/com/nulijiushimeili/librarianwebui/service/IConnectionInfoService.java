package com.nulijiushimeili.librarianwebui.service;


import com.nulijiushimeili.librariancommon.beans.entity.ConnectionInfo;

/******************************
 * @Project: librarian-parent
 * @FileName: IConnectionInfoService.java
 * @ClassName: IConnectionInfoService
 * @time 2020/5/14 22:49
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
public interface IConnectionInfoService {


    /**
     * 添加数据源信息
     *
     * @param connectionInfo
     * @return
     */
    public Integer addConnectionInfo(ConnectionInfo connectionInfo);


}
