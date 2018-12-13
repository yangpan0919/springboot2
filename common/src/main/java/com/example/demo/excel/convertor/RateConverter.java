package com.example.demo.excel.convertor;



import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeArgException;

import java.util.regex.Pattern;

/**
 * Created by Knight on 2018/1/3.
 */
public class RateConverter implements ExcelDataConverter {
    private static final String BIGDECIMAL_FORMART="(^[0-9]+(.[0-9]+))|([0-9]+(.[0-9]+%))|([0-9]+)|([0-9]+%)$";

    @Override
    public boolean canConvert(String className) {
        return CLASS_DOUBLE.equals(className);
    }

    @Override
    public Object doConvert(String className,Long row,Integer column, String item, String sheetNo) {
        if (canConvert(className)) {
            if(Pattern.matches(BIGDECIMAL_FORMART, item) != true){
                throw new ErrorCodeArgException(ErrorEnum.PUT_THROUGH_RATE_ERROR,new String[]{sheetNo, String.valueOf(row),String.valueOf(column)});
            }
            if (item.contains("%")) {
                return Double.parseDouble(item.substring(0, item.indexOf("%"))) / 100;
            } else {
                return Double.parseDouble(item);
            }
        }
        return null;
    }
}
