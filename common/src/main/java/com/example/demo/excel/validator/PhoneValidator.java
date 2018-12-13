package com.example.demo.excel.validator;



import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeArgException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by qianweijie on 2018/7/16.
 */
public class PhoneValidator extends ExcelDataValidator{
    private static final String NUMBER_FORMART="^[0-9]+$";

    @Override
    public void validate(String colname, String item, long row, long column, String sheetNo) {
        if(StringUtils.isNotBlank(item)){
            if(item.contains("-")){
                //-不在首尾
                String itemHead = item.trim().substring(0, 1);
                String itemTail = item.trim().substring(item.length() - 1);
                if(itemHead.contains("-") || itemTail.contains("-")){
                    if(sheetNo != null){
                        throw new ErrorCodeArgException(ErrorEnum.PHONE_ERROR_WITH_SHEETNO,new String[]{String.valueOf(sheetNo),String.valueOf(row),String.valueOf(column)});
                    }else{
                        throw new ErrorCodeArgException(ErrorEnum.PHONE_ERROR,new String[]{String.valueOf(row),String.valueOf(column)});
                    }

                }
                //除了-都是数字
                String[] items = item.split("-");
                for(String itemTmp : items){
                    if(Pattern.matches(NUMBER_FORMART, itemTmp) != true){
                        if(sheetNo != null){
                            throw new ErrorCodeArgException(ErrorEnum.PHONE_ERROR_WITH_SHEETNO,new String[]{String.valueOf(sheetNo),String.valueOf(row),String.valueOf(column)});
                        }else{
                            throw new ErrorCodeArgException(ErrorEnum.PHONE_ERROR,new String[]{String.valueOf(row),String.valueOf(column)});
                        }
                    }
                }
            }else{
                //没-都是数字
                if(Pattern.matches(NUMBER_FORMART, item) != true){
                    if(sheetNo != null){
                        throw new ErrorCodeArgException(ErrorEnum.PHONE_ERROR_WITH_SHEETNO,new String[]{String.valueOf(sheetNo),String.valueOf(row),String.valueOf(column)});
                    }else{
                        throw new ErrorCodeArgException(ErrorEnum.PHONE_ERROR,new String[]{String.valueOf(row),String.valueOf(column)});
                    }
                }
            }
        }
    }
}
