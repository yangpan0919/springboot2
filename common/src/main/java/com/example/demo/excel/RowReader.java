package com.example.demo.excel;

import java.util.List;

/**获取excel行数据
 * Created by qianweijie on 2018/5/25.
 */
public interface RowReader {
    public void getRows(int sheetIndex, int maxColNo, List<String> rowlist);
}
