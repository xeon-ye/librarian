package com.nulijiushimeili.oracle11gdataservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-12 14:32
 * @Desc:         Oracle  连接器
 ********************************************************/

@Slf4j
@Service
public class OracleConnectionUtils {



    /**
     * oracle 查询
     * @param sql
     * @param fieldSize
     * @return
     */
    public static List<List<String>> executeQueryForOracle(String ip, int port, String username, String password, String namespace, String sql, Integer fieldSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<List<String>> list = new ArrayList<>();
        String url = null;
        url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + namespace;
        log.info("connect Oracle db info: " + url);


        try {
            Class.forName("oracle.jdbc.OracleDriver");

            log.info("oracle 数据库连接url：" + url);
            log.info("用户名/密码： " + username + "/" + password);

            conn = DriverManager.getConnection(url, username, password);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i < fieldSize + 1; i++) {
                    row.add(rs.getString(i));
                }
                list.add(row);
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
        } catch (SQLException e) {
            log.error("执行SQL错误：{}", e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;

    }
}
