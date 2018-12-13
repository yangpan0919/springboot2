package com.example.demo.excel.convertor;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeArgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Created by qianweijie on 2018/6/26.
 */
public class StringDurationConverter implements ExcelDataConverter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TIME_FORMAT1 = "^\\d{4}[/]([0][1-9]|(1[0-2]))[/]([1-9]|([012]\\d)|(3[01]))([ \\t\\n\\x0B\\f\\r])(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";//2012/02/05 19:50:50
    private static final String TIME_FORMAT2 = "^\\d{4}[-]([0][1-9]|(1[0-2]))[-]([1-9]|([012]\\d)|(3[01]))([ \\t\\n\\x0B\\f\\r])(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";//2012-02-05 19:50:50
    private static final String TIME_FORMAT3 = "^([0-9]+):([0-5][0-9]):([0-5][0-9])$";//88:59:59


    @Override
    public boolean canConvert(String className) {
        return true;
    }

    @Override
    public Object doConvert(String className, Long row, Integer column, String item, String sheetNo) {
        if (CLASS_STRING.equals(className)) {
            if(Pattern.matches(TIME_FORMAT1, item) == true || Pattern.matches(TIME_FORMAT2, item) == true
                    || Pattern.matches(TIME_FORMAT3, item) == true){
                if(item.contains(" ")){
                    item = item.split(" ")[1];
                }
                return item;
            }else {
                if(sheetNo != null){
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR_WITH_SHEETNO,new String[]{sheetNo, String.valueOf(row),String.valueOf(column)});
                }else{
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR,new String[]{String.valueOf(row),String.valueOf(column)});
                }
            }
        }
        logger.info("转换格式类型{}， value {} 失败", className, item);
        return null;
    }
}
