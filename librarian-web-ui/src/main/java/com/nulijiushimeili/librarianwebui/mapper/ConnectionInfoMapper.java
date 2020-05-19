package com.nulijiushimeili.librarianwebui.mapper;

import com.nulijiushimeili.librarian.beans.entity.ConnectionInfo;
import com.nulijiushimeili.librarian.beans.entity.HostInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/******************************
 * @Project: librarian
 * @FileName: ConnectionInfoMapper.java
 * @ClassName: ConnectionInfoMapper
 * @time 2020/5/11 23:16
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
public interface ConnectionInfoMapper {

    /**
     * 添加数据源信息
     * @param connectionInfo
     * @return
     */
    public Integer addConnectionInfo(ConnectionInfo connectionInfo);

    /**
     * 添加数据源的节点信息
     * @param hostInfoList
     * @return
     */
    public Integer addConnectionHostInfo(@Param("hostInfoList")List<HostInfo> hostInfoList);

}
