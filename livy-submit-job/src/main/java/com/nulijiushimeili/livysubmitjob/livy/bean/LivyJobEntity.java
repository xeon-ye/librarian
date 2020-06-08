package com.nulijiushimeili.livysubmitjob.livy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivyJobEntity {
    /**
     * 程序文件地址
     */
    public String file;
    /**
     * class类Spark Main入库类
     */
    public String className;
    //代理用户
    public String proxyUser;
    //可选参数
    public ArrayList<String> args;
    //第三方依赖包
    public ArrayList<String> jars;
    //用逗号隔开的放置在Python应用程序PYTHONPATH上的.zip, .egg, .py文件列表
    public ArrayList<String> pyFiles;
    //配置文件（.xml）
    public ArrayList<String> files;
    //Driver程序使用内存大小
    public String driverMemory;
    //Driver程序的使用CPU个数
    public Integer driverCores;
    //executor内存大小
    public String executorMemory;
    //每个executor使用的内核数
    public Integer executorCores;
    //启动的executor数量
    public Integer numExecutors;
    //使用—jars 和 –archives添加应用程序所依赖的第三方jar包等
    public ArrayList<String> archives;
    //yarn队列名称
    public String queue;
    //Session的名称
    public String name;
    //Spark配置属性
    public HashMap conf;
   // public MultipartFile file1;
}
