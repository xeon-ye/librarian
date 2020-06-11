package com.nulijiushimeili.librariancommon.beans.entity;

import com.nulijiushimeili.librariancommon.utils.MyDateUtils;
import lombok.Data;

/**
 * 返回的结果生成统一的结果实体
 * @param <T>
 */

@Data
public class ResultEntity<T> {


    /**
     *  请求放回的状态码
     */
    private int code;

    /**
     *  请求返回的状态信息
     */
    private String message;

    /**
     *  返回的字符串信息
     */
    private String information;

    /**
     *  返回的结果如果是 数组， 这是数组的长度
     */
    private Long count;


    /**
     * 返回的结果数据
     */
    private T data;

    /**
     *  返回结果的时间
     */
    private String date = MyDateUtils.getCurrentTimeStr();

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
