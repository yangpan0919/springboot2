package com.example.demo.utils;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.ErrorCodeException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static SimpleDateFormat sdf = new SimpleDateFormat();

    /**
     * 获得path的后缀名
     *
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (path == null || EMPTY.equals(path.trim())) {
            return EMPTY;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return EMPTY;
    }

    /**
     * 单元格格式
     *
     * @param hssfCell
     * @return
     */
    @SuppressWarnings({"static-access", "deprecation"})
    public static String getHValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                cellValue = DateFormatUtils.format(date,DateFormatUtils.DEFAULT_TIME_PATTERN3);
            } else {
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(hssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
                if ("00".equals(strArr)) {
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    /**
     * 单元格格式
     *
     * @param xssfCell
     * @return
     */
    public static String getXValue(Cell xssfCell) {
        if(null ==  xssfCell){
            return "";
        }
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (DateUtil.isCellDateFormatted(xssfCell)) {
                Date date = DateUtil.getJavaDate(xssfCell.getNumericCellValue());
                cellValue = DateFormatUtils.format(date,DateFormatUtils.DEFAULT_TIME_PATTERN2);
            } else {
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(xssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
                if ("00".equals(strArr)) {
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            try {
                return String.valueOf(xssfCell.getStringCellValue());
            } catch (Exception e) {
                throw new ErrorCodeException(ErrorEnum.SOME_EXCEL_FALSE);
            }
        }

    }

    /**
     * 单元格格式 用于%保留四位小数
     *
     * @param xssfCell
     * @return
     */
    public static String getXValueForFourPoint(Cell xssfCell) {
        if(null ==  xssfCell){
            return "";
        }
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (DateUtil.isCellDateFormatted(xssfCell)) {
                Date date = DateUtil.getJavaDate(xssfCell.getNumericCellValue());
                cellValue = DateFormatUtils.format(date,DateFormatUtils.DEFAULT_TIME_PATTERN2);
            } else {
                DecimalFormat df = new DecimalFormat("#.####");
                cellValue = df.format(xssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
                if ("00".equals(strArr)) {
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            try {
                return String.valueOf(xssfCell.getStringCellValue());
            } catch (Exception e) {
                throw new ErrorCodeException(ErrorEnum.SOME_EXCEL_FALSE);
            }
        }

    }

    /**
     * 单元格格式 用于%保留四位小数
     *
     * @param hssfCell
     * @return
     */
    @SuppressWarnings({"static-access", "deprecation"})
    public static String getHValueForFourPoint(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                cellValue = DateFormatUtils.format(date,DateFormatUtils.DEFAULT_TIME_PATTERN3);
            } else {
                DecimalFormat df = new DecimalFormat("#.####");
                cellValue = df.format(hssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
                if ("00".equals(strArr)) {
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            try {
                return String.valueOf(hssfCell.getStringCellValue());
            } catch (Exception e) {
                throw new ErrorCodeException(ErrorEnum.SOME_EXCEL_FALSE);
            }
        }
    }


    /**
     * xssfCell单元格格式 通用
     *
     * @param xssfCell
     * @return
     */
    public static String getXValueForCommon(Cell xssfCell) {
        if(null ==  xssfCell){
            return "";
        }
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (DateUtil.isCellDateFormatted(xssfCell)) {
                Date date = DateUtil.getJavaDate(xssfCell.getNumericCellValue());
                cellValue = DateFormatUtils.format(date,DateFormatUtils.DEFAULT_TIME_PATTERN2);
            } else {
                //此处直接转换为String 在自己的逻辑里根据需要保留小数位
                cellValue = String.valueOf(xssfCell.getNumericCellValue());
                if ("0".equals(cellValue.substring(cellValue.indexOf(POINT)+1))) {
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            try {
                return String.valueOf(xssfCell.getStringCellValue());
            } catch (Exception e) {
                throw new ErrorCodeException(ErrorEnum.SOME_EXCEL_FALSE);
            }
        }
    }

    /**
     * HSSFCell单元格格式 通用
     *
     * @param hssfCell
     * @return
     */
    @SuppressWarnings({"static-access", "deprecation"})
    public static String getHValueForCommon(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                cellValue = DateFormatUtils.format(date,DateFormatUtils.DEFAULT_TIME_PATTERN3);
            } else {
                //此处直接转换为String 在自己的逻辑里根据需要保留小数位
                cellValue = String.valueOf(hssfCell.getNumericCellValue());
                if ("0".equals(cellValue.substring(cellValue.indexOf(POINT)+1))) {
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            try {
                return String.valueOf(hssfCell.getStringCellValue());
            } catch (Exception e) {
                throw new ErrorCodeException(ErrorEnum.SOME_EXCEL_FALSE);
            }
        }
    }
}
