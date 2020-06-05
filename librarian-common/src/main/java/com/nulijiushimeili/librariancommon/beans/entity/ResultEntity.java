package com.nulijiushimeili.librariancommon.beans.entity;

import com.nulijiushimeili.librariancommon.utils.MyDateUtils;
import lombok.Data;


@Data
public class ResultEntity<T> {



    private int code;
    private String message;
    private String information;
    private Long count;
    private T data;
    private String date = MyDateUtils.getCurrentTime();

    private static final String SUCCESS_MESSAGE = "SUCCESS";


    public static ResultEntity error(int code, String message) {
        ResultEntity error = new ResultEntity();
        error.setCode(code);
        error.setMessage(message);
        return error;
    }

    public static ResultEntity success() {
        ResultEntity success = new ResultEntity();
        success.setCode(0);
        success.setMessage(SUCCESS_MESSAGE);
        return success;
    }

    public static ResultEntity success(boolean flag) {
        ResultEntity success = new ResultEntity();
        success.setCode(0);
        success.setMessage(SUCCESS_MESSAGE);
        return success;
    }


    public static <V> ResultEntity<V> success(V data) {
        ResultEntity success = success();
        success.setData(data);
        return success;
    }

    public static <V> ResultEntity<V> successMsg(String message) {
        ResultEntity success = success();
        success.setMessage(message);
        return success;
    }
    public static <V> ResultEntity<V> success(String information) {
        ResultEntity success = success();
        success.setInformation(information);
        return success;
    }

    public static <V> ResultEntity<V> success(V data, String message) {
        ResultEntity success = success(data);
        success.setMessage(message);
        return success;
    }
}
