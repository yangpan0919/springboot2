package com.example.demo.excel.validator;

/**
 * Created by Knight on 2017/12/28.
 */
public abstract class ExcelDataValidator {

    private String regex;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public abstract void validate(String colname, String item, long row, long col, String sheetNo);
}
