package com.nulijiushimeili.librarianwebui.service;

import com.nulijiushimeili.librarian.beans.entity.ConnectionInfo;
import com.nulijiushimeili.librarian.beans.entity.HostInfo;
import com.nulijiushimeili.librarian.beans.result.RequestEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param connectionInfo
     * @return
     */
    public Integer addConnectionInfo(ConnectionInfo connectionInfo);



}
