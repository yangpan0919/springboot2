package com.example.demo.excel.convertor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Knight on 2017/12/28.
 */
public class CasePhaseConverter implements ExcelDataConverter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean canConvert(String className) {
        return CLASS_INTEGER.equals(className);
    }

    @Override
    public Object doConvert(String className, Long row, Integer column, String item, String sheetNo) {
        if (canConvert(className)) {
            if (!"M0".equalsIgnoreCase(item)&&!"M1".equalsIgnoreCase(item) && !"M2".equalsIgnoreCase(item) && !"M3".equalsIgnoreCase(item) && !"M4-M6".equalsIgnoreCase(item) &&
                    !"M7-M12".equalsIgnoreCase(item) && !"M13-M24".equalsIgnoreCase(item) && !"M24+".equalsIgnoreCase(item)) {
//                throw new ErrorCodeArgException(ErrorEnum.PHASE_ERROR,new String[]{String.valueOf(row),String.valueOf(column)});
            }
//            return PhaseEnum.getCaseCodeByMessage(item);
        }
        return null;
    }
}
