package com.example.demo.excel.convertor;

/**
 * Created by Knight on 2017/12/28.
 */
public class GenderConverter implements ExcelDataConverter {

    @Override
    public boolean canConvert(String className) {
        return CLASS_INTEGER.equals(className);
    }

    @Override
    public Object doConvert(String className, Long row, Integer column, String item, String sheetNo) {
        if (canConvert(className)) {
            if ("男".equals(item)) {
                return 0;
            } else if ("女".equals(item)) {
                return 1;
            } else {
                return 2;
            }
        }
        return null;
    }
}
