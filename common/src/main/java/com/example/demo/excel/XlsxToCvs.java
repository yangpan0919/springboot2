package com.example.demo.excel;


import com.example.demo.utils.BigDecimalUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 转换excel为CVS
 * Created by qianweijie on 2018/5/24.
 */
@Component(value = "xlsxToCvs")
public class XlsxToCvs extends DefaultHandler {

    Logger logger = LoggerFactory.getLogger(XlsxToCvs.class);

    private RowReader rowReader;//处理行数据

    public void setRowReader(RowReader rowReader) {
        this.rowReader = rowReader;
    }

    private SharedStringsTable sst;//工作簿中所有工作表共享的字符串表:文本，格式属性和语音属性
    private String lastContents;//单元格内容
    private boolean nextIsString;//判断是否为String
    private int curRow = 0; //当前行
    private int curCol = 0; //当前列索引
    private int preCol = 0; //上一列列索引
    private int titleRow = 0; //标题行，一般情况下为0
    private int maxColumn = 0; //最大列数 第一行列数
    private Integer sheetIndex = -1; //当前sheet索引
    private short formatIndex; //要转换格式的单元格的索引
    private String formatString; //要转换的格式
    private final DataFormatter formatter = new DataFormatter(); //单元格格式
    private StylesTable stylesTable; //单元格
    private CellDataType nextDataType = CellDataType.SSTINDEX; //单元格数据类型，默认为字符串类型
    private List<String> rowList = new ArrayList<>(); //行数据
    private boolean isTElement; //T元素标识

    /**
     * 将excel解析为cvs
     */
    public void process(InputStream inputStream) {
        sheetIndex = -1;
        try {
            OPCPackage pkg = OPCPackage.open(inputStream);
            XSSFReader r = new XSSFReader(pkg);
            SharedStringsTable sst = r.getSharedStringsTable();
            stylesTable = r.getStylesTable();
            XMLReader parser = fetchSheetParser(sst);

            Iterator<InputStream> sheets = r.getSheetsData();
            while (sheets.hasNext()) {
                curRow = 0;
                sheetIndex++;
                InputStream sheet = sheets.next();
                InputSource sheetSource = new InputSource(sheet);
                //解析XML文档
                parser.parse(sheetSource);
                sheet.close();
            }
        } catch (Exception e) {
            logger.error("excel转换为cvs出错",e);
            rowList.clear();
            curRow = 0;
            curCol = 0;
            preCol = 0;

        }
    }

    public XMLReader fetchSheetParser(SharedStringsTable sst)
            throws SAXException {
        //选择SAX驱动程序
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        this.sst = sst;
        //向SAX解析器注册一个实例。解析器使用实例来报告基本的文档相关事件，如元素的开始和结束以及字符数据。
        parser.setContentHandler(this);
        return parser;
    }

    /**
     * 解析器将在XML文档的每个元素的开始处调用此方法
     * @param uri 名称空间URI，或者如果该元素没有名称空间URI或名称空间处理未执行，则为空字符串
     * @param localName 本地名称（不带前缀），或者在命名空间处理未执行时为空字符串
     * @param name 限定名称（带前缀），如果限定名称不可用，则为空字符串
     * @param attributes 附加到元素的属性。如果没有属性，它应该是一个空的属性对象。startElement返回后的此对象的值未定义
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        // c => 单元格
        if ("c".equals(name)) {
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t");
            if (cellType != null && cellType.equals("s")) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
            // 当前单元格的位置
            String rowStr = attributes.getValue("r");
            // 当前列索引
            curCol = this.getRowIndex(rowStr);
            // 设定单元格类型
            this.setNextDataType(attributes);
        }

        if ("t".equals(name)) {
            isTElement = true;
        } else {
            isTElement = false;
        }

        // 置空
        lastContents = "";
    }

    /**
     * SAX解析器将在XML文档中的每个元素的末尾调用此方法
     * @param uri 名称空间URI，或者如果该元素没有名称空间URI或名称空间处理未执行，则为空字符串
     * @param localName 本地名称（不带前缀），或者在命名空间处理未执行时为空字符串
     * @param name 限定的XML名称（带前缀），或者如果限定名称不可用，则为空字符串
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次 空格换行等导致 多次调用拼接数据
//        if (nextIsString && StringUtils.isNotBlank(lastContents) && StringUtils.isNumeric(lastContents)) {
//            try {
//                int idx = Integer.parseInt(lastContents.trim());
//                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
//            } catch (NumberFormatException e) {
//                lastContents = lastContents.trim();
//            }
//        }

        if (isTElement) {
            // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
            String value = lastContents.trim();
            rowList.add(curCol-1, value);
            curCol++;
            isTElement = false;
        } else if ("v".equals(name)) {
            // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
            // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
            String value = this.getDataValue(lastContents.trim(), "");
            value = value.equals("") ? null : value;
            //补上没有值的单元格
            int cols = curCol - preCol;
            if (cols > 1) {
                for (int i = 0; i < cols - 1; i++) {
                    rowList.add(preCol, null);
                }
            }
            preCol = curCol;
            //行数据 大于表头长度的不计
            if (curRow == 0 || (curRow != 0 && curCol <= maxColumn)) {
                rowList.add(curCol - 1, value);
            }
        } else {
            //如果标签名称为row ，这说明已到行尾
            if (name.equals("row")) {
                //获取最大列数 首行列数
                if (curRow == this.titleRow) {
                    this.maxColumn = rowList.size();
                }
                //补齐每行最后的空格
                int tmpCols = rowList.size();
                if (curRow > this.titleRow && tmpCols < this.maxColumn) {
                    for (int i = 0; i < this.maxColumn - tmpCols; i++) {
                        rowList.add(rowList.size(), null);
                    }
                }

                //整行为空舍弃掉 不为空时调用getRows返回行数据
                if (rowList != null && rowList.size() != 0) {
                    //重新组装行数据的list供返回
                    List<String> newRowList = new ArrayList<>();
                    for(int i = 0;i < rowList.size();i++){
                        if(i < maxColumn){
                            newRowList.add(rowList.get(i));
                        }
                    }
                    //判断是否为非空行
                    int maxCol = 0;
                    for (String row : newRowList) {
                        if (StringUtils.isBlank(row) || "null".equals(row)) {
                            maxCol++;
                        }
                    }
                    //非空行返回行数据
                    if (maxCol != maxColumn) {
                        rowReader.getRows(sheetIndex, curRow, newRowList);
                    }
                }
                rowList.clear();
                curRow++;
                curCol = 0;
                preCol = 0;
            }
        }
    }

    /**
     * 得到单元格内容的值
     * @param ch XML文档的字符
     * @param start 数组中的开始位置
     * @param length 从数组中读取的字符数
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        lastContents += new String(ch, start, length);
    }


    /**
     * 得到列索引，每一列c元素的r属性构成为字母加数字的形式，字母组合为列索引，数字组合为行索引，
     * 如AB45,表示为第（A-A+1）*26+（B-A+1）*26列，45行
     *
     * @param rowStr
     * @return
     */
    public int getRowIndex(String rowStr) {
        rowStr = rowStr.replaceAll("[^A-Z]", "");
        byte[] rowAbc = rowStr.getBytes();
        int len = rowAbc.length;
        float num = 0;
        for (int i = 0; i < len; i++) {
            num += (rowAbc[i] - 'A' + 1) * Math.pow(26, len - i - 1);
        }
        return (int) num;
    }


    /**
     * 单元格中的数据可能的数据类型
     */
    enum CellDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
    }

    /**
     * 处理数据类型
     *
     * @param attributes
     */
    public void setNextDataType(Attributes attributes) {
        nextDataType = CellDataType.NUMBER;
        formatIndex = -1;
        formatString = null;
        String cellType = attributes.getValue("t");
        String cellStyleStr = attributes.getValue("s");

        if ("b".equals(cellType)) {
            nextDataType = CellDataType.BOOL;
        } else if ("e".equals(cellType)) {
            nextDataType = CellDataType.ERROR;
        } else if ("inlineStr".equals(cellType)) {
            nextDataType = CellDataType.INLINESTR;
        } else if ("s".equals(cellType)) {
            nextDataType = CellDataType.SSTINDEX;
        } else if ("str".equals(cellType)) {
            nextDataType = CellDataType.FORMULA;
        }

        if (cellStyleStr != null) {
            int styleIndex = Integer.parseInt(cellStyleStr);
            XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
            formatIndex = style.getDataFormat();
            formatString = style.getDataFormatString();

            if (formatString == null) {
                nextDataType = CellDataType.NULL;
                formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
            }

            if(formatString.contains("h:mm")){
                nextDataType = CellDataType.DATE;
                formatString = "yyyy-MM-dd hh:mm:ss";
            } else if (formatString.contains("m/d/yy") ||formatString.contains("yyyy/m/d")) {
                nextDataType = CellDataType.DATE;
                formatString = "yyyy-MM-dd";
            }
        }
    }

    /**
     * 对解析出来的数据进行类型处理
     *
     * @param value   单元格的值（这时候是一串数字）
     * @param thisStr 一个空字符串
     * @return
     */
    @SuppressWarnings("deprecation")
    public String getDataValue(String value, String thisStr) {
        switch (nextDataType) {
            // 这几个的顺序不能随便交换，交换了很可能会导致数据错误
            case BOOL:
                char first = value.charAt(0);
                thisStr = first == '0' ? "FALSE" : "TRUE";
                break;
            case ERROR:
                thisStr = "\"ERROR:" + value.toString() + '"';
                break;
            case FORMULA:
                thisStr = '"' + value.toString() + '"';
                break;
            case INLINESTR:
                XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                thisStr = rtsi.toString();
                break;
            case SSTINDEX:
//                thisStr = value.toString();
//                //会有错误 数据窜位 偶现
                String sstIndex = value.toString();
                try {
                    int idx = Integer.parseInt(sstIndex);
                    XSSFRichTextString rtss = new XSSFRichTextString(sst.getEntryAt(idx));
                    thisStr = rtss.toString();
                } catch (NumberFormatException ex) {
                    thisStr = value.toString();
                }
                break;
            case NUMBER:
                if(StringUtils.isNotBlank(value) && StringUtils.isNotBlank(formatString)){
                    //单元格内容为 数值、千位分隔符、科学计数法、%  直接取value  时间要格式转换
                    if(formatString.contains("E+") || formatString.contains("#") || formatString.contains("[Red]") || formatString.contains("%")){
                        if(value.contains("E")){
                            thisStr = BigDecimalUtils.round2String(new BigDecimal(value).toPlainString(),4);
                        } else{
                            thisStr = value;
                        }

                    }else{
                        thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
                    }
                }else {
                    //常规格式
                    thisStr = value;
                }


//                if (StringUtils.isNotBlank(formatString)) {
//                    try {
////                        if (("yyyy/m/d\\ h:mm;@".equals(formatString) || "yyyy/m/d;@".equals(formatString))) {
//                        if(true){
//                            //时间格式转换
//                            thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
//                        } else if (formatString.contains("E+") || formatString.contains("_")) {
//                            thisStr = BigDecimalUtils.round2String(new BigDecimal(value).toPlainString(),4);
//                        } else {
//                            thisStr = BigDecimalUtils.round2String(value,4);
//                        }
//                    } catch (NumberFormatException e) {
//                        thisStr = value;
//                    }
//                } else {
//                    thisStr = value;
//                }
                break;
            case DATE:
                try {
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString);
                } catch (NumberFormatException e) {
                    thisStr = value;
                }
                break;
            default:
                thisStr = " ";
                break;
        }
        return thisStr;
    }

}



