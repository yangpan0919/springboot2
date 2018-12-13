package com.example.demo.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qianweijie on 2018/5/28.
 */
@Component(value = "newExcelComponent")
public class NewExcelComponent implements RowReader{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    XlsxToCvs xlsxToCvs;

    private List<List<String>> rowsList1 = new ArrayList<>();
    private List<List<String>> rowsList2 = new ArrayList<>();

    /**
     * 解析excel
     * @param inputStream
     * @return
     * @throws Exception
     */
    public HashMap<Integer, List<List<String>>> parseXlsx(InputStream inputStream){
        try {
            xlsxToCvs.setRowReader(this);
            xlsxToCvs.process(inputStream);
            HashMap<Integer, List<List<String>>> sheetsData = new HashMap<>();
            if(rowsList1 != null && rowsList1.size() != 0){
                List<List<String>> rows1 = new ArrayList<>();
                rows1.addAll(rowsList1);
                sheetsData.put(0, rows1);
                rowsList1.clear();
            }
            if(rowsList2 != null && rowsList2.size() != 0){
                List<List<String>> rows2 = new ArrayList<>();
                rows2.addAll(rowsList2);
                sheetsData.put(1, rows2);
                rowsList2.clear();
            }
            return sheetsData;
        } catch (Exception e) {
            logger.error("解析xlsx出错",e);
            rowsList1.clear();
            rowsList2.clear();
            return null;
        }
    }

    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowlist){
        if (sheetIndex == 0) {
            rowsList1.add(rowlist);
        } else if (sheetIndex == 1) {
            rowsList2.add(rowlist);
        }
    }

}
