package com.nulijiushimeili.mysql5.utils;


import com.nulijiushimeili.librariancommon.beans.entity.ConnectionInfoSpread;
import com.nulijiushimeili.librariancommon.exception.UserDefinedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class MysqlConnectionUtils {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            UserDefinedException.except(100100);
        }
    }


    /**
     * @param connectionInfo 连接信息
     * @param tClass         返回结果对象的类型
     * @param <T>            将查询结果包装成 对象列表
     * @return
     */
    public static <T> List<T> executeQuery(ConnectionInfoSpread connectionInfo, String sql, Class<T> tClass) {


        return null;
    }


    /**
     * 执行mysql的查询操作
     *
     * @param connectionInfo
     * @param fieldSize
     * @return
     */
    public static List<List<String>> executeQuery(ConnectionInfoSpread connectionInfo, String sql, int fieldSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8",
                connectionInfo.getIp(),
                connectionInfo.getPort(),
                connectionInfo.getDbName()
        );
        List<List<String>> res = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, connectionInfo.getUsername(), connectionInfo.getPassword());
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (true) {
                if (!rs.next()) {
                    break;
                }
                List<String> row = new ArrayList<>();
                for (int i = 1; i < fieldSize + 1; i++) {
                    try {
                        row.add(rs.getString(i));
                    } catch (SQLException e) {
                        log.error("执行SQL错误：{}", e.getMessage());
                    }
                }
                res.add(row);
            }
            if (ps == null) {
                ps.close();
            }
            if (conn != null) {

                conn.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error(throwable.getMessage());
            throw UserDefinedException.except(100500);
        }
        return res;
    }


    /**
     * mysql 执行更新修改操作
     * @param sql      执行无返回值的sql
     * @return
     * @throws Exception
     */
    public static boolean execute(ConnectionInfoSpread connectionInfo, String sql) {
        Connection conn = null;
        PreparedStatement ps = null;

        String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8&useSSL=true&verifyServerCertificate=false",
                connectionInfo.getIp(),
                connectionInfo.getPort(),
                connectionInfo.getDbName());
        try {
            conn = DriverManager.getConnection(url, connectionInfo.getUsername(), connectionInfo.getPassword());
            ps = conn.prepareStatement(sql);
            ps.execute();
            if (ps == null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("执行SQL错误：{}", throwable.getMessage());
            throw  UserDefinedException.except(100500);
        }

        return true;
    }


    public static List<Map<String, Object>> executeQuery(ConnectionInfoSpread connectionInfo, String sql, List<String> fieldNameList) {


        return null;
    }


}
