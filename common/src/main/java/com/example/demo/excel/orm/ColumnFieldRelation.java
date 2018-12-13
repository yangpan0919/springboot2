package com.example.demo.excel.orm;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.excel.convertor.ExcelDataConverter;
import com.example.demo.excel.validator.ExcelDataValidator;
import com.example.demo.exception.ErrorCodeArgException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Knight on 2017/12/26.
 */
public class ColumnFieldRelation {

    private Boolean required;
    private String columnName;
    private Integer columnNum;
    private String fieldName;
    private String regexPattern;
    private String regexId;
    private String suffix;
    private ExcelDataConverter converter;
    private ExcelDataValidator validator;

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRegexPattern() {
        return regexPattern;
    }

    public void setRegexPattern(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    public String getRegexId() {
        return regexId;
    }

    public void setRegexId(String regexId) {
        this.regexId = regexId;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public ExcelDataConverter getConverter() {
        return converter;
    }

    public void setConverter(ExcelDataConverter converter) {
        this.converter = converter;
    }

    public ExcelDataValidator getValidator() {
        return validator;
    }

    public void setValidator(ExcelDataValidator validator) {
        this.validator = validator;
    }

    public void typeFilter(String item, long row , long column ,String sheetNo) {
        if(required == true && StringUtils.isBlank(item)) {
            if(sheetNo != null){
                throw new ErrorCodeArgException(ErrorEnum.PARAM_MISSING_WITH_SHEETNO,new String[]{sheetNo,String.valueOf(row), String.valueOf(column), item});
            }else{
                throw new ErrorCodeArgException(ErrorEnum.PARAM_MISSING,new String[]{String.valueOf(row), String.valueOf(column), item});
            }

        }
        if(StringUtils.isBlank(item) ) {
            //允许为空 则不对空值做校验
            return;
        }
        if(validator != null ){
            validator.validate(columnName,item, row, column, sheetNo);
        }
    }

    public Object typeConvert(String type,Long row,Integer column, String item,String sheetNo) {
        return converter.doConvert(type,row,column,item,sheetNo);
    }
}
