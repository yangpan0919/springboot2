package com.example.demo.excel.convertor;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeArgException;
import com.example.demo.utils.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by qianweijie on 2018/4/19.
 */
public class DateConverter implements ExcelDataConverter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TIME_FORMAT1 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
    private static final String TIME_FORMAT2 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/(((0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)/(0[1-9]|[12][0-9]|30))|(02/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/02/29)$";
    private static final String TIME_FORMAT3 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-((([13578]|1[02])-([1-9]|[12][0-9]|3[01]))|(([469]|11)-([1-9]|[12][0-9]|30))|(2-([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-2-29)$";
    private static final String TIME_FORMAT4 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/((([13578]|1[02])/([1-9]|[12][0-9]|3[01]))|(([469]|11)/([1-9]|[12][0-9]|30))|(2/([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/2/29)$";

    @Override
    public boolean canConvert(String className) {
        return true;
    }

    @Override
    public Object doConvert(String className, Long row, Integer column, String item, String sheetNo) {
        if (CLASS_STRING.equals(className)) {
            Date date = new Date();
            if (Pattern.matches(TIME_FORMAT1, item) == true || Pattern.matches(TIME_FORMAT3, item) == true) {
                date = DateFormatUtils.parse(item, "yyyy-MM-dd");
                return DateFormatUtils.format(date, "yyyy-MM-dd");
            } else if (Pattern.matches(TIME_FORMAT2, item) == true || Pattern.matches(TIME_FORMAT4, item) == true) {
                date = DateFormatUtils.parse(item, "yyyy/MM/dd");
                return DateFormatUtils.format(date, "yyyy/MM/dd").replace("/","-");
            } else {
                if(sheetNo != null){
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR_WITH_SHEETNO,new String[]{sheetNo, String.valueOf(row),String.valueOf(column)});
                }else{
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR,new String[]{String.valueOf(row),String.valueOf(column)});
                }
            }
        } else if (CLASS_DATE.equals(className)) {
            if (Pattern.matches(TIME_FORMAT1, item) == true || Pattern.matches(TIME_FORMAT3, item) == true) {
                return DateFormatUtils.parse(item, "yyyy-MM-dd");
            } else if (Pattern.matches(TIME_FORMAT2, item) == true || Pattern.matches(TIME_FORMAT4, item) == true) {
                return DateFormatUtils.parse(item, "yyyy/MM/dd");
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
