package com.nulijiushimeili.librarianwebui.entity;

import com.nulijiushimeili.librariancommon.beans.enums.BaseEnum;

/**
 * 任务队列执行的状态
 */
public enum TaskCurrentStatusEnum implements BaseEnum {

    //-- 任务队列中标注状态
    //- TASK_CURRENT_STATUS （1，准备执行；2，正在执行；3，执行成功；4，执行失败）

    READY(1,"准备执行"),
    RUNNING(2,"正在执行"),
    SUCCESS(3,"执行成功"),
    FAILURE(4,"执行失败");



    private int code;
    private String value;

     TaskCurrentStatusEnum(int code, String value){
        this.code = code;
        this.value = value;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
