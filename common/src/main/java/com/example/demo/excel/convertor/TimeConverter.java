package com.example.demo.excel.convertor;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeArgException;
import com.example.demo.utils.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by qianweijie on 2018/4/19.
 */
public class TimeConverter implements ExcelDataConverter {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    private static final String TIME_FORMAT1 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
    private static final String TIME_FORMAT2 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/(((0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)/(0[1-9]|[12][0-9]|30))|(02/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/02/29)$";
    private static final String TIME_FORMAT3 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-((([13578]|1[02])-([1-9]|[12][0-9]|3[01]))|(([469]|11)-([1-9]|[12][0-9]|30))|(2-([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-2-29)$";
    private static final String TIME_FORMAT4 = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/((([13578]|1[02])/([1-9]|[12][0-9]|3[01]))|(([469]|11)/([1-9]|[12][0-9]|30))|(2/([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/2/29)$";
    private static final String TIME_FORMAT5 = "^\\d{4}[/]([0][1-9]|(1[0-2]))[/]([1-9]|([012]\\d)|(3[01]))([ \\t\\n\\x0B\\f\\r])(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";//2012/02/05 19:50:50
    private static final String TIME_FORMAT6 = "^\\d{4}[-]([0][1-9]|(1[0-2]))[-]([1-9]|([012]\\d)|(3[01]))([ \\t\\n\\x0B\\f\\r])(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";//2012-02-05 19:50:50
    private static final String TIME_FORMAT7 = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/((([13578]|1[02])/([1-9]|[12][0-9]|3[01]))|(([469]|11)/([1-9]|[12][0-9]|30))|(2/([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/2/29))(\\s+)((1?[0-9]|2[0-4])([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";//2012/2/5 0:00 单元格为日期格式
    private static final String TIME_FORMAT8 = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/((([13578]|1[02])/([1-9]|[12][0-9]|3[01]))|(([469]|11)/([1-9]|[12][0-9]|30))|(2/([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/2/29))(\\s+)((([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))))$";//2012/2/5 00:00:00 文本格式
    private static final String TIME_FORMAT9 = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-((([13578]|1[02])-([1-9]|[12][0-9]|3[01]))|(([469]|11)-([1-9]|[12][0-9]|30))|(2-([1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-2-29))(\\s+)((([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))))$";//2012-2-5 00:00:00 文本格式


    @Override
    public boolean canConvert(String className) {
        return true;
    }

    @Override
    public Object doConvert(String className, Long row, Integer column, String item, String sheetNo) {
        if (CLASS_STRING.equals(className)) {
            Date date = new Date();
            if(Pattern.matches(TIME_FORMAT1, item) == true || Pattern.matches(TIME_FORMAT3, item) == true){
                date = DateFormatUtils.parse(item, "yyyy-MM-dd");
                return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
            }else if(Pattern.matches(TIME_FORMAT2, item) == true || Pattern.matches(TIME_FORMAT4, item) == true){
                date = DateFormatUtils.parse(item, "yyyy/MM/dd");
                return DateFormatUtils.format(date, "yyyy/MM/dd HH:mm:ss").replace("/","-");
            } else if (Pattern.matches(TIME_FORMAT6, item) == true || Pattern.matches(TIME_FORMAT9, item) == true) {
                date = DateFormatUtils.parse(item, "yyyy-MM-dd HH:mm:ss");
                return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
            } else if (Pattern.matches(TIME_FORMAT5, item) == true || Pattern.matches(TIME_FORMAT8, item) == true) {
                date = DateFormatUtils.parse(item, "yyyy/MM/dd HH:mm:ss");
                return DateFormatUtils.format(date, "yyyy/MM/dd HH:mm:ss").replace("/","-");
            } else if(Pattern.matches(TIME_FORMAT7, item) == true){
                date = DateFormatUtils.parse(item +":30", "yyyy/MM/dd HH:mm:ss");
                return DateFormatUtils.format(date, "yyyy/MM/dd HH:mm:ss").replace("/","-");
            } else {
                if (sheetNo != null) {
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR_WITH_SHEETNO, new String[]{sheetNo, String.valueOf(row), String.valueOf(column)});
                } else {
                    throw new ErrorCodeArgException(ErrorEnum.DATE_FORMAT_ERROR, new String[]{String.valueOf(row), String.valueOf(column)});
                }
            }
        } else if (CLASS_DATE.equals(className)) {
            if (Pattern.matches(TIME_FORMAT1, item) == true || Pattern.matches(TIME_FORMAT3, item) == true) {
                return DateFormatUtils.parse(item, "yyyy-MM-dd");
            } else if (Pattern.matches(TIME_FORMAT2, item) == true || Pattern.matches(TIME_FORMAT4, item) == true) {
                return DateFormatUtils.parse(item, "yyyy/MM/dd");
            } else if (Pattern.matches(TIME_FORMAT5, item) == true || Pattern.matches(TIME_FORMAT8, item) == true) {
                return DateFormatUtils.parse(item, "yyyy/MM/dd HH:mm:ss");
            } else if(Pattern.matches(TIME_FORMAT6, item) == true || Pattern.matches(TIME_FORMAT9, item) == true){
                return DateFormatUtils.parse(item, "yyyy-MM-dd HH:mm:ss");
            } else if(Pattern.matches(TIME_FORMAT7, item) == true){
                return DateFormatUtils.parse(item.trim()+":30", "yyyy/MM/dd HH:mm:ss");
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
