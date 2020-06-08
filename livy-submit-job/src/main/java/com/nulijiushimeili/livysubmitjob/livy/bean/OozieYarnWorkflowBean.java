package com.nulijiushimeili.livysubmitjob.livy.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OozieYarnWorkflowBean {
    private  String userName;
    private  String routeValue;
    private  String type;
    private  String queue;


}
