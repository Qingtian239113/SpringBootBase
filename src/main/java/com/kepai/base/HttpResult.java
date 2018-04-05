package com.kepai.base;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2017/9/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class HttpResult<T> {

    private int code;
    private String message;
    private int count;
    private T data;

    /**
     * 请求成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> respOK(T data) {
        HttpResult<T> result = new HttpResult<>();
        result.code = 200;
        result.message = "请求成功";
        result.data = data;
        return result;
    }

    /**
     * 请求成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> respOK(T data, int count) {
        HttpResult<T> result = new HttpResult<>();
        result.code = 200;
        result.message = "请求成功";
        result.data = data;
        result.count = count;
        return result;
    }

    /**
     * 自定义的错误
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> respCust(int code, String msg) {
        HttpResult<T> result = new HttpResult<>();
        result.code = code;
        result.message = msg;
        return result;
    }

    /**
     * 用户未登录
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> respNeedLogin() {
        HttpResult<T> result = new HttpResult<>();
        result.code = 401;
        result.message = "用户未登录";
        return result;
    }

    /**
     * 请求未找到
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> respNotFound() {
        HttpResult<T> result = new HttpResult<>();
        result.code = 404;
        result.message = "请求未找到";
        return result;
    }

    /**
     * 请求参数缺失
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> respNeedParam(String msg) {
        HttpResult<T> result = new HttpResult<>();
        result.code = 428;
        result.message = "缺少必要参数：" + msg;
        return result;
    }

    /**
     * 服务器异常
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> respError() {
        HttpResult<T> result = new HttpResult<>();
        result.code = 503;
        result.message = "服务器异常,请稍后再试";
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
