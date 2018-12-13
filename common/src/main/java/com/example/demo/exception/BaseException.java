package com.example.demo.exception;




import com.example.demo.enums.ErrorEnum;

import java.io.Serializable;

/**
 * 其实和ErrorCodeException一样，作为父类，子类分需要回滚和不回滚的
 */
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 3063805789474333543L;

    /**
     * 错误码
     */
    private ErrorEnum error;

    /**
     * 请求的参数，在post请求的时候赋值
     */
    private String request;

    private Serializable result;

    public BaseException() {
        error = ErrorEnum.SYSTEM_UNKONW_ERROR;
    }

    public BaseException(ErrorEnum errorEnum) {
        this.error = errorEnum;
    }

    public BaseException(ErrorEnum errorEnum, String request) {
        this.error = errorEnum;
        this.request = request;
    }

    public BaseException(ErrorEnum errorEnum, Serializable result) {
        this.error = errorEnum;
        this.result = result;
    }

    public BaseException(ErrorEnum errorEnum, String request, Serializable result) {
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
