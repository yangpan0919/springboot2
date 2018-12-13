package com.example.demo.excel.validator;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeArgException;

/**
 * Created by Knight on 2017/12/28.
 */
public class ExcelGenericDataValidator extends ExcelDataValidator{

    @Override
    public void validate(String colname, String item, long row, long col, String sheetNo) {
        if(!item.trim().matches(getRegex())){
            if(sheetNo != null){
                throw new ErrorCodeArgException(ErrorEnum.EXCEL_PATTERN_VALIDATION_ERROR_WITH_SHEETNO, new String[]{sheetNo, String.valueOf(row), String.valueOf(col), colname});
            }else{
                throw new ErrorCodeArgException(ErrorEnum.EXCEL_PATTERN_VALIDATION_ERROR, new String[]{String.valueOf(row), String.valueOf(col), colname});
            }
        }
    }
}
