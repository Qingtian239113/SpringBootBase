package com.kepai.base;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2017/8/8
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class ResultException extends RuntimeException {

    private HttpResult httpResult;

    public ResultException(String message) {
        super(message);
        this.httpResult = HttpResult.respCust(110, message);
    }

    public ResultException(int code, String message) {
        this.httpResult = HttpResult.respCust(code, message);
    }

    public ResultException(HttpResult httpResult) {
        this.httpResult = httpResult;
    }

    public HttpResult getHttpResult() {
        return httpResult;
    }
}
