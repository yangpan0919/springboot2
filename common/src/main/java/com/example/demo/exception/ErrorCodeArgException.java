package com.example.demo.exception;



import com.example.demo.enums.ErrorEnum;

import java.io.Serializable;

/**
 * Created by nfhu on 2015/10/15.
 */
public class ErrorCodeArgException extends ErrorCodeException {


    /**
     * 请求的参数，在post请求的时候赋值
     */
    private String request;

    /**
     * 用户接口输出的content值
     */
    private Serializable result;

    /**
     * 错误描述的参数
     */
    private String[] args;

    public ErrorCodeArgException() {
        error = ErrorEnum.SYSTEM_UNKONW_ERROR;
    }

    public ErrorCodeArgException(ErrorEnum error, String... params) {
        this.error = error;
        this.args = params;
    }

//    public ErrorCodeArgException(ErrorEnum error, String request, String... params) {
//        this.error = error;
//        this.request = request;
//        this.args = params;
//    }

    public ErrorCodeArgException(ErrorEnum error, Serializable result, String... params) {
        this.error = error;
        this.result = result;
        this.args = params;
    }

    public ErrorCodeArgException(ErrorEnum error, String request, Serializable result, String... params) {
        this.error = error;
        this.request = request;
        this.result = result;
        this.args = params;
    }

    @Override
    public ErrorEnum getError() {
        return error;
    }

    @Override
    public void setError(ErrorEnum error) {
        this.error = error;
    }

    @Override
    public String getRequest() {
        return request;
    }

    @Override
    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public Serializable getResult() {
        return result;
    }

    @Override
    public void setResult(Serializable result) {
        this.result = result;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
