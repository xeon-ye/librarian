package com.nulijiushimeili.prestounifiedquery.utils;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-08 21:20
 * @Desc: TODO
 ********************************************************/


public class PrestoDemo {

    public static void main(String[] args) {
        try {
            Class.forName("com.facebook.presto.jdbc.PrestoDriver");
            String url = "jdbc:presto://192.168.181.200:9001/system/runtime";







        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
