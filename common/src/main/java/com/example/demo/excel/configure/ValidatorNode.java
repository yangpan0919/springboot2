package com.example.demo.excel.configure;

import javax.xml.bind.annotation.XmlAttribute;


public class ValidatorNode {

    private String id;
    private String regexValue;
    private String clazz;

    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name="regex")
    public String getRegexValue() {
        return regexValue;
    }

    public void setRegexValue(String regexValue) {
        this.regexValue = regexValue;
    }

    @XmlAttribute(name="class")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
