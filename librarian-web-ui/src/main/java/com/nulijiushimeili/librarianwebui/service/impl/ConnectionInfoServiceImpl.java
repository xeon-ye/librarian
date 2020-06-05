package com.nulijiushimeili.librarianwebui.service.impl;

import com.nulijiushimeili.librariancommon.beans.entity.ConnectionInfo;
import com.nulijiushimeili.librariancommon.exception.UserDefinedException;
import com.nulijiushimeili.librarianwebui.mapper.ConnectionInfoMapper;
import com.nulijiushimeili.librarianwebui.service.IConnectionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/******************************
 * @Project: librarian-parent
 * @FileName: ConnectionInfoServiceImpl.java
 * @ClassName: ConnectionInfoServiceImpl
 * @time 2020/5/14 22:50
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
@Slf4j
@Service
public class ConnectionInfoServiceImpl implements IConnectionInfoService {

    @Autowired
    private ConnectionInfoMapper connectionInfoMapper;

    @Override
    @Transactional(rollbackFor = UserDefinedException.class)
    public Integer addConnectionInfo(ConnectionInfo connectionInfo) {
        int res1 = connectionInfoMapper.addConnectionInfo(connectionInfo);
        int res2 = connectionInfoMapper.addConnectionHostInfo(connectionInfo.getHostInfoList());
        if (res1 > 0 && res2 > 0) {
            return 1;
        } else {
            throw UserDefinedException.except(110001);
        }
    }



}
