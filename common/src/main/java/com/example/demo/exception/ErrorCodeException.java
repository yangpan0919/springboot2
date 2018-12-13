package com.example.demo.exception;



import com.example.demo.enums.ErrorEnum;

import java.io.Serializable;

/**
 * Created by nfhu on 2014/10/14.
 * 异常状态的Exception，统一处理异常错误码
 */
public class ErrorCodeException extends RuntimeException{

    private static final long serialVersionUID = 3063805789474333543L;

    /**
     * 错误码
     */
    protected ErrorEnum error;

    /**
     * 请求的参数，在post请求的时候赋值
     */
    private String request;

    private Serializable result;

    public ErrorCodeException() {
        error = ErrorEnum.SYSTEM_UNKONW_ERROR;
    }

    public ErrorCodeException(ErrorEnum errorEnum) {
        this.error = errorEnum;
    }

    public ErrorCodeException(ErrorEnum errorEnum, String request) {
        this.error = errorEnum;
        this.request = request;
    }

    public ErrorCodeException(ErrorEnum errorEnum, Serializable result) {
        this.error = errorEnum;
        this.result = result;
    }

    public ErrorCodeException(ErrorEnum errorEnum, String request, Serializable result) {
        this.error = errorEnum;
        this.request = request;
        this.result = result;
    }

    public ErrorEnum getError() {
        return error;
    }

    public void setError(ErrorEnum error) {
        this.error = error;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Serializable getResult() {
        return result;
    }

    public void setResult(Serializable result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ErrorCodeException{" +
                "error=" + error +
                ", request='" + request + '\'' +
                ", result=" + result +
                '}';
    }
}
