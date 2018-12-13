package com.example.demo.excel.convertor;

/**
 * Created by Knight on 2017/12/28.
 */
public interface ExcelDataConverter {

    String CLASS_STRING = "class java.lang.String";
    String CLASS_DOUBLE = "class java.lang.Double";
    String CLASS_INTEGER = "class java.lang.Integer";
    String CLASS_LONG = "class java.lang.Long";
    String CLASS_BOOLEAN = "class java.lang.Boolean";
    String CLASS_DATE = "class java.util.Date";

    boolean canConvert(String className);

    Object doConvert(String className, Long row, Integer column, String item, String sheetNo);
}
