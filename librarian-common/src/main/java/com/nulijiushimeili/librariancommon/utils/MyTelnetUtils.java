package com.nulijiushimeili.librariancommon.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-16 16:13
 * @Desc: TODO
 ********************************************************/

@Slf4j
public class MyTelnetUtils {


    /**
     * 测试telnet 机器端口的连通性
     *
     * @param hostname
     * @param port
     * @param timeout
     * @return
     */
    public static boolean telnet(String hostname, int port, int timeout) {
        Socket socket = new Socket();
        boolean isConnected = false;
        try {
            socket.connect(new InetSocketAddress(hostname, port), timeout); // 建立连接
            isConnected = socket.isConnected(); // 通过现有方法查看连通状态
//            System.out.println(isConnected);    // true为连通
        } catch (IOException e) {
            System.out.println("false");        // 当连不通时，直接抛异常，异常捕获即可
        } finally {
            try {
                socket.close();   // 关闭连接
            } catch (IOException e) {
                System.out.println("false");
            }
        }
        return isConnected;
    }

//    public static void main(String[] args) {
//        String hostname = "39.103.131.58";    // hostname 可以是主机的 IP 或者 域名
//        int port = 8098;
//        int timeout = 200;
//        boolean isConnected = telnet(hostname, port, timeout);
//        System.out.println("telnet " + hostname + " " + port + "\n==>isConnected: " + isConnected);
//    }
}
