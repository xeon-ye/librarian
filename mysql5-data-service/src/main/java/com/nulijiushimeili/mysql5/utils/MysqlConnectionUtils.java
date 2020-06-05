package com.nulijiushimeili.mysql5.utils;


import com.nulijiushimeili.librariancommon.beans.entity.ConnectionInfoSpread;
import com.nulijiushimeili.librariancommon.exception.UserDefinedException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
public class MysqlConnectionUtils {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            UserDefinedException.except(100100);
        }
    }


    /**
     *
     * @param connectionInfo  连接信息
     * @param tClass  返回结果对象的类型
     * @param <T>  将查询结果包装成 对象列表
     * @return
     */
    public static  <T> List<T> executeQuery(ConnectionInfoSpread connectionInfo,String sql, Class<T> tClass){


        return null;
    }


    /**
     *
     * @param connectionInfo
     * @param fieldSize
     * @return
     */
    public static List<List<String>> executeQuery(ConnectionInfoSpread connectionInfo ,String sql, int fieldSize){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8",
                connectionInfo.getIp(),
                connectionInfo.getPort(),
                connectionInfo.getDbName()

        );
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error("加载驱动错误：{}", e.getMessage());
        }
        try {
            conn = DriverManager.getConnection(url, connectionInfo.getUsername(), connectionInfo.getPassword());
        } catch (SQLException e) {
            log.error("获取连接错误：{}", e.getMessage());
        }
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            log.error("获取连接错误：{}", e.getMessage());
        }
        try {
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            log.error("执行SQL错误：{}", e.getMessage());
        }
        List<List<String>> res = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                log.error("执行SQL错误：{}", e.getMessage());
            }
            List<String> row = new ArrayList<>();
            for (int i = 1; i < fieldSize + 1; i++) {
//                log.info(resultSet.getString(i));
                try {
                    row.add(resultSet.getString(i));
                } catch (SQLException e) {
                    log.error("执行SQL错误：{}", e.getMessage());
                }
            }
            res.add(row);
        }

        if (ps == null) {
            try {
                ps.close();
            } catch (SQLException e) {
                log.error("关闭连接错误：{}", e.getMessage());
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("关闭连接错误：{}", e.getMessage());
            }
        }
        return res;
    }




    public static List<Map<String, Object>> executeQuery(ConnectionInfoSpread connectionInfo, String sql , List<String> fieldNameList){


        return null;
    }



}
