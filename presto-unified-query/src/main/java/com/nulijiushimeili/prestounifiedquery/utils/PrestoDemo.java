package com.nulijiushimeili.prestounifiedquery.utils;

import java.sql.*;
import java.util.Properties;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-08 21:20
 * @Desc: TODO
 ********************************************************/


public class PrestoDemo {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName("com.facebook.presto.jdbc.PrestoDriver");
            String url = "jdbc:presto://192.168.181.200:9001/system/runtime";
            String sql = "select * from hive.default.aaa, mysql.code_helper.bbb limit 10";
            Properties properties = new Properties();
            properties.setProperty("user","root");
            properties.setProperty("SSL","false");


            conn = DriverManager.getConnection(url,properties);
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1));
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
