package com.nulijiushimeili.mysql5.utils;

import com.nulijiushimeili.librariancommon.beans.ConnectionInfo;
import com.nulijiushimeili.librariancommon.beans.HostInfo;
import com.nulijiushimeili.librariancommon.exception.UserDefinedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MysqlDataSourcePool {

    // 使用容器——使用环境查询条件不多主要做增删改操作
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    // 这里的代码应该写在配置文件中
    private final int INIT_CONNECTIONS = 5;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void initMysqlConnPool(ConnectionInfo connectionInfo) {

        if (connectionInfo == null) {
            throw UserDefinedException.except(10059);
        }
        if (connectionInfo.getHostInfoList() != null && connectionInfo.getHostInfoList().size() <= 0) {
            throw UserDefinedException.except(10059);
        }
        HostInfo hostInfo = connectionInfo.getHostInfoList().get(0);
        if (hostInfo == null) {
            throw UserDefinedException.except(10059);
        }


        // 注册驱动
        try {
            Class.forName(hostInfo.getDriverClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 初始化连接
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(hostInfo.getUrl(), connectionInfo.getUsername(),
                        connectionInfo.getPassword());
                pool.addLast(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取数据库连接
    public Connection getConnection() {
        Connection conn = null;
        lock.lock();
        try {
            while (pool.size() == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!pool.isEmpty()) {
                conn = pool.removeFirst();
            }
            return conn;
        } finally {
            lock.unlock();
        }
    }

    // 释放数据库连接
    public void releaseConnection(Connection conn) {
        if (conn != null) {
            lock.lock();
            try {
                // 释放连接过程就是把连接放回连接池过程
                pool.addLast(conn);
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
