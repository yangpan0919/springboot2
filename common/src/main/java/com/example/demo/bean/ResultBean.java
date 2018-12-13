package com.example.demo.bean;



import com.example.demo.enums.ErrorEnum;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.MessageFormat;


/**
 * Created by YP on 2018/10/15.
 */
public class ResultBean implements Serializable {

    private  static Logger logger = LoggerFactory.getLogger(ResultBean.class);
    private static final long serialVersionUID = -4365068809657107866L;

    private int code;

    private String desc;

    private Serializable content;

    public ResultBean() {}

    public ResultBean(ErrorEnum errorEnum) {
        this.code = errorEnum.getRespCode();
        this.desc = errorEnum.getRespMsg();
    }

    public ResultBean(ErrorEnum errorEnum, String... params) {
        this.code = errorEnum.getRespCode();
        this.desc = MessageFormat.format(errorEnum.getRespMsg(),params);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Serializable getContent() {
        return content;
    }

    public void setContent(Serializable content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        try {
            return new Gson().toJson(this);
        } catch (Exception e) {
            logger.error("json转换失败",e);
        }
        return "{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", content=" + content +
                '}';
    }
}
