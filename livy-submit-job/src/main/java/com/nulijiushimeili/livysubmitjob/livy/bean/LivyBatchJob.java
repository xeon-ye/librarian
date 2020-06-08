package com.nulijiushimeili.livysubmitjob.livy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivyBatchJob {

    //batch session id;
    private Integer id;
    //the application id of this session
    private String appId;
    //应用详细信息
    private HashMap appInfo;
    // 日志
    private ArrayList<String> log;
    //batch session的状态
    private String state;

    private String name;

    private String  owner;

    private String proxyUser;
}
