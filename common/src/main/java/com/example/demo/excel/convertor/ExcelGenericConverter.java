package com.example.demo.excel.convertor;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeArgException;
import com.example.demo.utils.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Created by Knight on 2017/12/28.
 */
public class ExcelGenericConverter implements ExcelDataConverter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TIME_FORMAT1 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
    private static final String TIME_FORMAT2 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/(((0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)/(0[1-9]|[12][0-9]|30))|(02/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/02/29)$";
    private static final String TIME_FORMAT3 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-((([13578]|1[02])-([1-9]|[12][0-9]|3[01]))|(([469]|11)-([1-9]|[12][0-9]|30))|(2-([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-2-29)$";
    private static final String TIME_FORMAT4 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/((([13578]|1[02])/([1-9]|[12][0-9]|3[01]))|(([469]|11)/([1-9]|[12][0-9]|30))|(2/([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/2/29)$";
    private static final String TIME_FORMAT5 = "^\\d{4}[/]([0][1-9]|(1[0-2]))[/]([1-9]|([012]\\d)|(3[01]))([ \\t\\n\\x0B\\f\\r])(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";//2012/02/05 19:50:50
    private static final String TIME_FORMAT6 = "^\\d{4}[-]([0][1-9]|(1[0-2]))[-]([1-9]|([012]\\d)|(3[01]))([ \\t\\n\\x0B\\f\\r])(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";//2012-02-05 19:50:50


    @Override
    public boolean canConvert(String className) {
        return true;
    }

    @Override
    public Object doConvert(String className, Long row, Integer column, String item, String sheetNo) {
        if (CLASS_STRING.equals(className)) {
            return item;
        } else if (CLASS_DOUBLE.equals(className)) {
            try {
                if (item.contains("%")) {
                    item = item.substring(0, item.indexOf("%"));
                    return Double.valueOf(item) / 100;
                } else {
                    return Double.valueOf(item);
                }
            } catch (NumberFormatException e) {
                throw new ErrorCodeArgException(ErrorEnum.PATTERN_ERROR, new String[]{String.valueOf(row), String.valueOf(column)});
            }
        } else if (CLASS_BOOLEAN.equals(className)) {
            try {
                return Boolean.valueOf(item);
            } catch (Exception e) {
                throw new ErrorCodeArgException(ErrorEnum.PATTERN_ERROR, new String[]{String.valueOf(row), String.valueOf(column)});
            }
        } else if (CLASS_INTEGER.equals(className)) {
            try {
                return Integer.valueOf(item);
            } catch (NumberFormatException e) {
                throw new ErrorCodeArgException(ErrorEnum.PATTERN_ERROR, new String[]{String.valueOf(row), String.valueOf(column)});
            }
        } else if (CLASS_LONG.equals(className)) {
            try {
                return Long.valueOf(item);
            } catch (NumberFormatException e) {
                throw new ErrorCodeArgException(ErrorEnum.PATTERN_ERROR, new String[]{String.valueOf(row), String.valueOf(column)});
            }
        } else if (CLASS_DATE.equals(className)) {
            if (Pattern.matches(TIME_FORMAT3, item) == true) {
                return DateFormatUtils.parse(item, "yyyy-MM-dd");
            } else if (Pattern.matches(TIME_FORMAT4, item) == true) {
                return DateFormatUtils.parse(item, "yyyy/MM/dd");
            } else if (Pattern.matches(TIME_FORMAT1, item) == true) {
                return DateFormatUtils.parse(item, "yyyy-MM-dd");
            } else if (Pattern.matches(TIME_FORMAT2, item) == true) {
                return DateFormatUtils.parse(item, "yyyy/MM/dd");
            } else if (Pattern.matches(TIME_FORMAT5, item) == true) {
                return DateFormatUtils.parse(item, "yyyy/MM/dd");
            } else if(Pattern.matches(TIME_FORMAT6, item) == true){
                return DateFormatUtils.parse(item, "yyyy-MM-dd");
            } else {
                if (sheetNo != null) {
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR_WITH_SHEETNO, new String[]{sheetNo, String.valueOf(row), String.valueOf(column)});
                } else {
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR, new String[]{String.valueOf(row), String.valueOf(column)});
                }
            }
        }
        logger.info("转换格式类型{}， value {} 失败", className, item);
        return null;
    }
}
