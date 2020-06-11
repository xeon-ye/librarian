package com.nulijiushimeili.librarianwebui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rmi.server.InactiveGroupException;

import java.util.Date;

/**
 * 任务队列 实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskInfoEntity {

    //-- 任务队列 表
    //DROP TABLE IF EXISTS T_TASK_QUEUE;
    //CREATE TABLE `T_TASK_QUEUE` (
    //ID INT(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键id',
    //TASK_NAME VARCHAR(100) NOT NULL COMMENT '任务名称',
    //TASK_CURRENT_STATUS INT(1) NOT NULL COMMENT '任务当前的状态',
    //TASK_CREATE_TIME DATETIME NOT NULL COMMENT '任务创建的时间',
    //TASK_FINISH_TIME DATETIME COMMENT '任务结束时间',
    //TASK_DURATION INT COMMENT '执行任务花费的时间',
    //TASK_FAILURE_MSG TEXT COMMENT '任务失败的原因',
    //USERNAME VARCHAR(100) NOT NULL COMMENT '提交任务的用户名',
    //CHECKED INT(3) NOT NULL COMMENT '用户是否查看过',
    //PRIMARY KEY (`ID`)
    //)ENGINE=InnoDB DEFAULT CHARSET=utf8;
    //-- NDBCLUSTER;
    //
    //-- 任务队列中标注状态
    //-- TASK_CURRENT_STATUS （1，准备执行；2，正在执行；3，执行成功；4，执行失败）


    /**
     * 任务序号 id
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务当前的状态
     */
    private TaskCurrentStatusEnum taskStatus;

    /**
     * 任务创建时间
     */
    private Date taskCreateTime;

    /**
     * 任务结束时间
     */
    private Date taskFinishTime;

    /**
     * 任务失败原因
     */
    private String taskFailureMsg;

    /**
     * 任务执行时间
     */
    private Long taskDuration;

    /**
     * 提交任务的用户名
     */
    private String username;

    /**
     * 用户是否查看过该信息，用户主页通知功能(0，表示没有查看过；1，表示已查看)
     */
    private Integer checked;


}
