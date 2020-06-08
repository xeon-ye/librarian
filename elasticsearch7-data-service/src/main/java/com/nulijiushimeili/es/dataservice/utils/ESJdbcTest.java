package com.nulijiushimeili.es.dataservice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ESJdbcTest {


    public static void main(String[] args) {
        try {
            Class.forName("org.elasticsearch.xpack.sql.jdbc.EsDriver");
            Connection connection = DriverManager.getConnection("jdbc:es://http://192.168.181.200:9200");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(
                    "select * from aaa  LIMIT 5");
            while (results.next()) {
                System.out.println(results.getString("request"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}