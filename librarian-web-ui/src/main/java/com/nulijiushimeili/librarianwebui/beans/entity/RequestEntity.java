package com.nulijiushimeili.librarianwebui.beans.entity;

import com.nulijiushimeili.librarianwebui.utils.MyDateUtils;
import lombok.Data;


@Data
public class RequestEntity<T> {



    private int code;
    private String message;
    private String information;
    private Long count;
    private T data;
    private String date = MyDateUtils.getCurrentTime();

    private static final String SUCCESS_MESSAGE = "SUCCESS";


    public static RequestEntity error(int code,String message) {
        RequestEntity error = new RequestEntity();
        error.setCode(code);
        error.setMessage(message);
        return error;
    }

    public static RequestEntity success() {
        RequestEntity success = new RequestEntity();
        success.setCode(0);
        success.setMessage(SUCCESS_MESSAGE);
        return success;
    }

    public static RequestEntity success(boolean flag) {
        RequestEntity success = new RequestEntity();
        success.setCode(0);
        success.setMessage(SUCCESS_MESSAGE);
        return success;
    }


    public static <V> RequestEntity<V> success(V data) {
        RequestEntity success = success();
        success.setData(data);
        return success;
    }

    public static <V> RequestEntity<V> successMsg(String message) {
        RequestEntity success = success();
        success.setMessage(message);
        return success;
    }
    public static <V> RequestEntity<V> success(String information) {
        RequestEntity success = success();
        success.setInformation(information);
        return success;
    }

    public static <V> RequestEntity<V> success(V data, String message) {
        RequestEntity success = success(data);
        success.setMessage(message);
        return success;
    }
}
