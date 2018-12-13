package com.example.demo.excel.configure;

import javax.xml.bind.annotation.XmlAttribute;


public class ExcelConfigRelationNode {
    private Integer columnNo;
    private String  columnName;
    private Boolean required;
    private String fieldName;
    private String validatorId;
    private String converterId;
    private String suffix;

    @XmlAttribute(name="cno")
    public Integer getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(Integer columnNo) {
        this.columnNo = columnNo;
    }
    @XmlAttribute(name="cname")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @XmlAttribute(name="required")
    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @XmlAttribute(name="validator-ref")
    public String getValidatorId() {
        return validatorId;
    }

    public void setValidatorId(String validatorId) {
        this.validatorId = validatorId;
    }

    @XmlAttribute(name="param")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @XmlAttribute(name="converter-ref")
    public String getConverterId() {
        return converterId;
    }

    public void setConverterId(String converterId) {
        this.converterId = converterId;
    }

    @XmlAttribute(name="suffix")
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
