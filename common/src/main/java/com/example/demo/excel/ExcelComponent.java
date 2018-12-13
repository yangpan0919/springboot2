package com.example.demo.excel;


import com.example.demo.enums.ErrorEnum;
import com.example.demo.excel.configure.ExcelConfigIntializer;
import com.example.demo.excel.orm.ColumnFieldRelation;
import com.example.demo.excel.orm.ExcelOrm;
import com.example.demo.excel.orm.ExcelOrmContext;
import com.example.demo.exception.ErrorCodeArgException;
import com.example.demo.exception.ErrorCodeException;
import com.example.demo.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Knight on 2017/12/26.
 */
@Component(value="excelComponent")
public class ExcelComponent {

    @Resource
    NewExcelComponent newExcelComponent;

    Logger logger = LoggerFactory.getLogger(ExcelComponent.class);
    private ExcelOrmContext context;

    public void init(){
        initConfig();
    }

    @PostConstruct
    private void initConfig() {
        context = ExcelConfigIntializer.getInitConfig();
    }

    /**
     * 解析拨号详单excel
     * @param in
     * @param suffix
     * @return
     */
//    public DialItemDTO readDialItemExcels(InputStream in, String suffix) {
//        HashMap<Integer, List<List<String>>> excels = readExcelWithXlsOrXlsx(in, suffix);
////        HashMap<Integer,List<ArrayList<String>>> excels = readExcelForCommon(in, suffix);
//
//        if(excels == null) {
//            logger.info("excel文件解析为空");
//            throw new ErrorCodeException(ErrorEnum.EXCEL_NULL);
//        }
//        DialItemDTO dialItemDTO = new DialItemDTO();
//        for(Map.Entry<Integer,List<List<String>>> excel : excels.entrySet()){
//            List<List<String>> resultList = excel.getValue();
//            Integer sheetNo = excel.getKey();
//            if(sheetNo == 1){
//                List<String> rowOne = resultList.get(0);
//                if(rowOne.size()<18){
//                    throw new ErrorCodeException(ErrorEnum.DIAL_ITEM_NULL);
//                }
//                List<DialItemImportDTO> dialItemImportDTOs = parseObject(resultList, DialItemImportDTO.class, "sheet1");
////                List<DialItemImportDTO> dialItemImportDTOs = readObjectFromExcelStreamOneByOne(resultList, DialItemImportDTO.class, "sheet1");
//                if(dialItemImportDTOs == null || dialItemImportDTOs.size() == 0){
//                    throw new ErrorCodeException(ErrorEnum.DIAL_ITEM_NULL);
//                }
//                dialItemDTO.setDialItemImportDTOs(dialItemImportDTOs);
//            }else if(sheetNo == 2){
//                List<DialItemDetailsImportDTO> dialItemDetailsImportDTOs = parseObject(resultList, DialItemDetailsImportDTO.class, "sheet2");
////                List<DialItemDetailsImportDTO> dialItemDetailsImportDTOs = readObjectFromExcelStreamOneByOne(resultList, DialItemDetailsImportDTO.class, "sheet2");
//                dialItemDTO.setDialItemDetailsImportDTOs(dialItemDetailsImportDTOs);
//            }
//        }
//        return dialItemDTO;
//    }

    /*public <T> HashMap<Integer, List<T>> readObjects(InputStream in, String suffix, List<Class<?>> types) {
        HashMap<Integer,List<ArrayList<String>>> excels = readExcelForCommon(in, suffix);
        if(excels == null) {
            logger.info("excel文件解析为空");
            throw new ErrorCodeException();
        }
        HashMap<Integer, List<T>> readObjects = new HashMap<>();
        for(Map.Entry<Integer,List<ArrayList<String>>> excel : excels.entrySet()){
            List<ArrayList<String>> resultList = excel.getValue();
            Integer sheetNo = excel.getKey();
            List<T> readObject = readObjectFromExcelStreamOneByOne(resultList, types.get(sheetNo-1), null);
            readObjects.put(sheetNo,readObject);
        }
        return readObjects;
    }*/

    /**
     * 用于解析多个sheet中的一个sheet 通用
     * @param resultList
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> readObjectFromExcelStreamOneByOne(List<ArrayList<String>> resultList, Class<?> type, String sheetNo){
        if(resultList == null) {
            logger.info("excel 文件解析为空");
            throw new ErrorCodeException();
        }
        logger.info("解析对象{}", type.getName());
        ExcelOrm orm = context.getOrmByClass(type.getName());
        if (orm == null) {
            logger.info("根据对象名找不到对应ORM");
            throw new ErrorCodeException();
        }
        Map<Integer, ColumnFieldRelation> relationMap = orm.getRelationMap();
        if(relationMap == null) {
            logger.info("关系对象缺失");
            throw new ErrorCodeException();
        }
        List<T> resultObject = new ArrayList<>();
        long r = 0;
        for (List<String> list :resultList ) {
            if(r == 0) {
                //第一条记录为列名校验列名
            }
            else {
                try {
                    T object =  (T)type.newInstance();
                    Map<String ,Object> unMatched = new HashMap<>();
                    for (int i = 0 ; i< list.size();i++) {
                        String item = list.get(i);
                        ColumnFieldRelation relation = relationMap.get(i+1);
                        if(relation == null) {
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            unMatched.put(resultList.get(0).get(i),item);
                        } else {
                            if(StringUtils.isNotBlank(item)){
                                item = item.trim();
                            }
                            relation.typeFilter(item, r+1,relation.getColumnNum(),sheetNo);
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            Field field = type.getDeclaredField(relation.getFieldName());
                            field.setAccessible(true);
                            field.set(object,relation.typeConvert(field.getGenericType().toString(),r+1,relation.getColumnNum(),item,sheetNo));
                        }
                    }
                    if(unMatched.size() > 0) {
                        try {
                            logger.info("对象{} 存在未映射关系 {}",type.getName());
                            Field otherInfoField = type.getDeclaredField("otherInfo");
                            otherInfoField.setAccessible(true);
                            otherInfoField.set(object, unMatched);
                        }catch (NoSuchFieldException e) {
                            logger.info("对象{} 存在未映射关系但未定义otherInfo",type.getName());
                        }
                    }
                    resultObject.add(object);
                } catch (InstantiationException |IllegalAccessException|NoSuchFieldException e) {
                    logger.error("构建实例出错",e);
                    throw new ErrorCodeException();
                }
            }
            r++;
        }
        return resultObject;
    }


    /**
     * 固定模板解析
     * @param resultList
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> readObjectForFixedExcel(List<List<String>> resultList, Class<?> type ){
        if(resultList == null || resultList.size() == 0) {
            logger.info("excel 文件解析为空");
            throw new ErrorCodeException(ErrorEnum.EXCEL_NULL);
        }
        logger.info("解析对象{}", type.getName());
        ExcelOrm orm = context.getOrmByClass(type.getName());
        if (orm == null) {
            logger.info("根据对象名找不到对应ORM");
            throw new ErrorCodeException();
        }
        Map<Integer, ColumnFieldRelation> relationMap = orm.getRelationMap();
        if(relationMap == null) {
            logger.info("关系对象缺失");
            throw new ErrorCodeException();
        }
        List<T> resultObject = new ArrayList<>();
        long r = 0;
        for (List<String> list :resultList ) {
            if(r == 0) {
                //第一条记录为列名校验列名
                //表头中间不能有为空的
                for (String cell : list) {
                    if (StringUtils.isBlank(cell) || ("null").equals(cell)) {
                        throw new ErrorCodeException(ErrorEnum.EXCEL_HEADER_HAS_NULL);
                    }
                }
            }
            else {
                try {
                    T object =  (T)type.newInstance();
                    Map<String ,Object> unMatched = new HashMap<>();
                    for (int i = 0 ; i< list.size();i++) {
                        String item = list.get(i);
                        ColumnFieldRelation relation = relationMap.get(i+1);
                        if(relation == null) {
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            unMatched.put(resultList.get(0).get(i),item);
                        } else {
                            if(StringUtils.isNotBlank(item)){
                                item = item.trim();
                            }
                            relation.typeFilter(item, r+1,relation.getColumnNum(),null);
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            Field field = type.getDeclaredField(relation.getFieldName());
                            field.setAccessible(true);
                            field.set(object,relation.typeConvert(field.getGenericType().toString(),r+1,relation.getColumnNum(),item,null));
                        }
                    }
                    if(unMatched.size() > 0) {
                        try {
                            logger.info("对象{} 存在未映射关系 {}",type.getName());
                            Field otherInfoField = type.getDeclaredField("otherInfo");
                            otherInfoField.setAccessible(true);
                            otherInfoField.set(object, unMatched);
                        }catch (NoSuchFieldException e) {
                            logger.info("对象{} 存在未映射关系但未定义otherInfo",type.getName());
                        }
                    }
                    resultObject.add(object);
                } catch (InstantiationException |IllegalAccessException|NoSuchFieldException e) {
                    logger.error("构建实例出错",e);
                    // TODO: 2017/12/28
                    throw new ErrorCodeException();
                }
            }
            r++;
        }
        return resultObject;
    }

    /**
     * 动态模板解析
     * @param resultList
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T>  readObjectForDynamicExcel(List<List<String>> resultList, Class<?> type ){
        if(resultList == null || resultList.size() == 0) {
            logger.info("excel 文件解析为空");
            throw new ErrorCodeException();
        }
        logger.info("解析对象{}", type.getName());
        ExcelOrm orm = context.getOrmByClass(type.getName());
        if (orm == null) {
            logger.info("根据对象名找不到对应ORM");
            throw new ErrorCodeException();
        }
        Map<String, ColumnFieldRelation> suffixRelationMap = orm.getSuffixRelationMap();
        if(suffixRelationMap == null){
            logger.info("下标字段关系对象缺失");
            throw new ErrorCodeException();
        }
        List<T> resultObject = new ArrayList<>();
        long r = 0;
        Map<Integer, ColumnFieldRelation> suffixTitle = null;
        Map<Integer, String> omitTitle = null;
        for (List<String> list :resultList ) {
            if(r == 0) {
                //表头中间不能有为空的
                for (String cell : list) {
                    if (StringUtils.isBlank(cell) || ("null").equals(cell)) {
                        throw new ErrorCodeException(ErrorEnum.EXCEL_HEADER_HAS_NULL);
                    }
                }
                Map<String, Map> resultMap = getTitleLocation(list, suffixRelationMap, orm.getOmitSuffix());
                suffixTitle = resultMap.get("suffixTitle");
                omitTitle = resultMap.get("omitTitle");
            }
            else {
                try {
                    T object =  (T)type.newInstance();
                    Map<String ,Object> unMatched = new HashMap<>();
                    for (int i = 0 ; i< list.size();i++) {
                        String item = list.get(i);
                        if(StringUtils.isNotBlank(item)){
                            item = item.trim();
                        }
                        if(omitTitle.containsKey(i)){
                            //忽略
                            continue;
                        }
                        if(suffixTitle.containsKey(i)){
                            //对应下标的字段
                            ColumnFieldRelation relation = suffixTitle.get(i);
                            relation.typeFilter(item, r+1,i+1, null);
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            Field field = type.getDeclaredField(relation.getFieldName());
                            field.setAccessible(true);
                            field.set(object,relation.typeConvert(field.getGenericType().toString(),r+1,i+1,item,null));
                        }else{
                            unMatched.put(resultList.get(0).get(i),item);
                        }
                    }
                    if(unMatched.size() > 0) {
                        try {
                            logger.info("对象{} 存在未映射关系 {}",type.getName());
                            Field otherInfoField = type.getDeclaredField("otherInfo");
                            otherInfoField.setAccessible(true);
                            otherInfoField.set(object, unMatched);
                        }catch (NoSuchFieldException e) {
                            logger.info("对象{} 存在未映射关系但未定义otherInfo",type.getName());
                        }
                    }
                    resultObject.add(object);
                } catch (InstantiationException |IllegalAccessException|NoSuchFieldException e) {
                    logger.error("构建实例出错",e);
                    // TODO: 2017/12/28
                    throw new ErrorCodeException();
                }
            }
            r++;
        }
        return resultObject;
    }

    /**
     * 固定模板解析
     * @param in
     * @param postfix
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> readObjectFromExcelStream( InputStream in, String postfix,Class<?> type ){
        List<ArrayList<String>> resultList = readExcel(in, postfix);
        if(resultList == null) {
            logger.info("excel 文件解析为空");
            throw new ErrorCodeException();
        }
        logger.info("解析对象{}", type.getName());
        ExcelOrm orm = context.getOrmByClass(type.getName());
        if (orm == null) {
            logger.info("根据对象名找不到对应ORM");
            throw new ErrorCodeException();
        }
        Map<Integer, ColumnFieldRelation> relationMap = orm.getRelationMap();
        if(relationMap == null) {
            logger.info("关系对象缺失");
            throw new ErrorCodeException();
        }
        List<T> resultObject = new ArrayList<>();
        long r = 0;
        for (List<String> list :resultList ) {
            if(r == 0) {
                //第一条记录为列名校验列名
            }
            else {
                try {
                    T object =  (T)type.newInstance();
                    Map<String ,Object> unMatched = new HashMap<>();
                    for (int i = 0 ; i< list.size();i++) {
                        String item = list.get(i);
                        ColumnFieldRelation relation = relationMap.get(i+1);
                        if(relation == null) {
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            unMatched.put(resultList.get(0).get(i),item);
                        } else {
                            if(StringUtils.isNotBlank(item)){
                                item = item.trim();
                            }
                            relation.typeFilter(item, r+1,relation.getColumnNum(),null);
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                             Field field = type.getDeclaredField(relation.getFieldName());
                            field.setAccessible(true);
                            field.set(object,relation.typeConvert(field.getGenericType().toString(),r+1,relation.getColumnNum(),item,null));
                        }
                    }
                    if(unMatched.size() > 0) {
                        try {
                            logger.info("对象{} 存在未映射关系 {}",type.getName());
                            Field otherInfoField = type.getDeclaredField("otherInfo");
                            otherInfoField.setAccessible(true);
                            otherInfoField.set(object, unMatched);
                        }catch (NoSuchFieldException e) {
                            logger.info("对象{} 存在未映射关系但未定义otherInfo",type.getName());
                        }
                    }
                    resultObject.add(object);
                } catch (InstantiationException |IllegalAccessException|NoSuchFieldException e) {
                    logger.error("构建实例出错",e);
                    // TODO: 2017/12/28
                    throw new ErrorCodeException();
                }
            }
            r++;
        }
        return resultObject;
    }

    /**
     * 动态模板解析
     * @param in
     * @param postfix
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T>  readObjectFromExcelStreamBySuffix( InputStream in, String postfix,Class<?> type ){
        List<ArrayList<String>> resultList = readExcel(in, postfix);
        if(resultList == null) {
            logger.info("excel 文件解析为空");
            throw new ErrorCodeException();
        }
        logger.info("解析对象{}", type.getName());
        ExcelOrm orm = context.getOrmByClass(type.getName());
        if (orm == null) {
            logger.info("根据对象名找不到对应ORM");
            throw new ErrorCodeException();
        }
//        Map<Integer, ColumnFieldRelation> relationMap = orm.getRelationMap();
//        if(relationMap == null) {
//            logger.info("关系对象缺失");
//            throw new ErrorCodeException();
//        }
        Map<String, ColumnFieldRelation> suffixRelationMap = orm.getSuffixRelationMap();
        if(suffixRelationMap == null){
            logger.info("下标字段关系对象缺失");
            throw new ErrorCodeException();
        }
        List<T> resultObject = new ArrayList<>();
        long r = 0;
        Map<Integer, ColumnFieldRelation> suffixTitle = null;
        Map<Integer, String> omitTitle = null;
        for (List<String> list :resultList ) {
            if(r == 0) {
                Map<String, Map> resultMap = getTitleLocation(list, suffixRelationMap, orm.getOmitSuffix());
                suffixTitle = resultMap.get("suffixTitle");
                omitTitle = resultMap.get("omitTitle");
            }
            else {
                try {
                    T object =  (T)type.newInstance();
                    Map<String ,Object> unMatched = new HashMap<>();
                    for (int i = 0 ; i< list.size();i++) {
                        String item = list.get(i);
                        if(StringUtils.isNotBlank(item)){
                            item = item.trim();
                        }
                        if(omitTitle.containsKey(i)){
                            //忽略
                            continue;
                        }
                        if(suffixTitle.containsKey(i)){
                            //对应下标的字段
                            ColumnFieldRelation relation = suffixTitle.get(i);
                            relation.typeFilter(item, r+1,i+1, null);
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            Field field = type.getDeclaredField(relation.getFieldName());
                            field.setAccessible(true);
                            field.set(object, relation.typeConvert(field.getGenericType().toString(),r+1,i+1,item,null));
                        }else{
                            unMatched.put(resultList.get(0).get(i),item);
                        }
                    }
                    if(unMatched.size() > 0) {
                        try {
                            logger.info("对象{} 存在未映射关系 {}",type.getName());
                            Field otherInfoField = type.getDeclaredField("otherInfo");
                            otherInfoField.setAccessible(true);
                            otherInfoField.set(object, unMatched);
                        }catch (NoSuchFieldException e) {
                            logger.info("对象{} 存在未映射关系但未定义otherInfo",type.getName());
                        }
                    }
                    resultObject.add(object);
                } catch (InstantiationException |IllegalAccessException|NoSuchFieldException e) {
                    logger.error("构建实例出错",e);
                    // TODO: 2017/12/28
                    throw new ErrorCodeException();
                }
            }
            r++;
        }
        return resultObject;
    }

    /**
     * 查询校验字段表头位置
     * @param list  文件表头
     * @param suffixRelationMap 带下标的表头map
     * @param omitSuffix    忽略的下标标志
     * @return
     */
    private Map<String, Map> getTitleLocation(List<String> list,Map<String, ColumnFieldRelation> suffixRelationMap,String omitSuffix){
        Map<String, Map> resultMap = new HashMap<>();
        Map<Integer, ColumnFieldRelation> suffixTitle = new HashMap<>();
        Map<Integer, String> omitTitle = new HashMap<>();
        Set<String> keyset = suffixRelationMap.keySet();
        List<String> suffixList = new ArrayList<>();
        for (int i = 0 ; i< list.size();i++) {
            String item = list.get(i);
            if(StringUtils.isNotBlank(item)){
                item = item.trim();
                if(item.contains(omitSuffix)){
                    //忽略的字段
                    omitTitle.put(i,omitSuffix);
                    continue;
                }
                for(String key : keyset){
                    ColumnFieldRelation titleRelation = suffixRelationMap.get(key);
                    if(item.contains(key)){
                        if(suffixList.contains(key)){
                            logger.warn("重复的表头字段,字段{},下标{}",titleRelation.getColumnName(),titleRelation.getSuffix());
                            throw new ErrorCodeArgException(ErrorEnum.DUPLICATE_TITLE,new String[]{titleRelation.getColumnName(),titleRelation.getSuffix()});
                        }
                        //对应下标的字段
                        suffixTitle.put(i,titleRelation);
                        suffixList.add(key);
                        break;
                    }
                }
            }
        }
        for(String key : keyset){
            ColumnFieldRelation titleRelation = suffixRelationMap.get(key);
            if(titleRelation.getRequired() && !suffixList.contains(key)){
                logger.warn("表头缺少必填字段{},下标{}",titleRelation.getColumnName(),titleRelation.getSuffix());
                throw new ErrorCodeArgException(ErrorEnum.REQUIRED_TITLE_NOT_FIND,new String[]{titleRelation.getColumnName(),titleRelation.getSuffix()});
            }

        }
        resultMap.put("suffixTitle",suffixTitle);
        resultMap.put("omitTitle",omitTitle);
        return resultMap;
    }


    /**
     * 解析单个sheet
     * @param in
     * @param postfix
     * @return
     */
    private List<ArrayList<String>> readExcel(InputStream in, String postfix)  {
        if (in == null || ExcelUtil.EMPTY.equals(postfix)) {
            return null;
        } else {
            if (ExcelUtil.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                return readXls(in);
            } else if (ExcelUtil.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                return readXlsx(in);
            } else {
                return null;
            }
        }
    }

    /**
     * 解析多个sheet 保留精确小数4位
     * @param in
     * @param postfix
     * @return
     */
    private HashMap<Integer,List<ArrayList<String>>> readExcels(InputStream in, String postfix)  {
        if (in == null || ExcelUtil.EMPTY.equals(postfix)) {
            return null;
        } else {
            if (ExcelUtil.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                return readXlss(in);
            } else if (ExcelUtil.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                return readXlsxs(in);
            } else {
                return null;
            }
        }
    }

    /**
     * 解析多个sheet 通用方法 小数位根据需求自己取
     * @param in
     * @param postfix
     * @return
     */
    private HashMap<Integer,List<ArrayList<String>>> readExcelForCommon(InputStream in, String postfix)  {
        if (in == null || ExcelUtil.EMPTY.equals(postfix)) {
            return null;
        } else {
            if (ExcelUtil.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                return readXlsForCommon(in);
            } else if (ExcelUtil.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                return readXlsxForCommon(in);
            } else {
                return null;
            }
        }
    }

    /**
     * 解析sheet xls->usermodel xlsx->eventusermodel
     * @param in
     * @param postfix
     * @return
     */
    public HashMap<Integer,List<List<String>>> readExcelWithXlsOrXlsx(InputStream in, String postfix)  {
        if (in == null || ExcelUtil.EMPTY.equals(postfix)) {
            return null;
        } else {
            if (ExcelUtil.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                return parseXls(in);
            } else if (ExcelUtil.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                return newExcelComponent.parseXlsx(in);
            } else {
                return null;
            }
        }
    }

    public List<ArrayList<String>> readXlsx(InputStream in) {
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        // IO流读取文件
        try {
            // 创建文档
            XSSFWorkbook wb  = new XSSFWorkbook(in);
            //读取sheet(页)
            //numSheet<wb.getNumberOfSheets()
            for (int numSheet = 0; numSheet < 1; numSheet++) {
                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                int totalRows = xssfSheet.getLastRowNum();
                if(xssfSheet.getRow(0)==null){
                    throw new ErrorCodeException(ErrorEnum.EXCEL_FALSE);
                }
                int totalCells = xssfSheet.getRow(0).getPhysicalNumberOfCells();
                //读取Row,从第二行开始
                //需要解析表头,从第一行开始
                for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                        ArrayList<String>  rowList = new ArrayList<>();
                        int emptyCount = 0;
                        for (int c = 0; c < totalCells; c++) {
                            XSSFCell cell = xssfRow.getCell(c);
                            if (cell == null) {
                                rowList.add(ExcelUtil.EMPTY);
                                emptyCount++;
                                continue;
                            }
                            String cellValue = ExcelUtil.getXValue(cell).trim();
                            if ("".equalsIgnoreCase(cellValue)) {
                                emptyCount++;
                                rowList.add(ExcelUtil.EMPTY);
                                continue;
                            }
                            // todo  为了得到准确的百分数，治标不治本
                            if (c == 31) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                            }
                            rowList.add(cellValue);
                        }
                        if (emptyCount >= totalCells) {
                            continue;
                        }
                        list.add(rowList);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            logger.error("读取excel 出错",e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("关闭输入流出错",e);
            }
        }
        return null;
    }

    public List<ArrayList<String>> readXls(InputStream in) {
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        try {
            // 创建文档
            HSSFWorkbook wb  = new HSSFWorkbook(in);
            //读取sheet(页)
            for (int numSheet = 0; numSheet < 1; numSheet++) {
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                 int totalRows = hssfSheet.getLastRowNum();
                int totalCells = hssfSheet.getRow(0).getPhysicalNumberOfCells();
                //读取Row,从第二行开始
                for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        ArrayList<String> rowList = new ArrayList<>();
                        int emptyCount = 0;
                        for (int c = 0; c < totalCells; c++) {
                            HSSFCell cell = hssfRow.getCell(c);
                            if (cell == null) {
                                rowList.add(ExcelUtil.EMPTY);
                                emptyCount++;
                                continue;
                            }
                            String cellValue = ExcelUtil.getHValue(cell).trim();
                            if ("".equalsIgnoreCase(cellValue)) {
                                emptyCount++;
                                rowList.add(ExcelUtil.EMPTY);
                                continue;
                            }
                            // todo  为了得到准确的百分数，治标不治本
                            if (c == 31) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                            }
                            rowList.add(cellValue);
                        }
                        if (emptyCount >= totalCells) {
                            continue;
                        }
                        list.add(rowList);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            logger.error("IOException",e);
        } finally {
            try {
//                input.close();
                in.close();
            } catch (IOException e) {
                logger.error("IOException", e);
            }
        }
        return null;
    }

    public HashMap<Integer,List<ArrayList<String>>> readXlsxs(InputStream in) {
        HashMap<Integer,List<ArrayList<String>>> excels = new HashMap<>();
        // IO流读取文件
        try {
            // 创建文档
            XSSFWorkbook wb  = new XSSFWorkbook(in);
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                List<ArrayList<String>> list = new ArrayList<>();
                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                int totalRows = xssfSheet.getLastRowNum();
                if(xssfSheet.getRow(0)==null){
                    throw new ErrorCodeException(ErrorEnum.EXCEL_FALSE);
                }
                int totalCells = xssfSheet.getRow(0).getPhysicalNumberOfCells();
                //读取Row,从第二行开始
                //需要解析表头,从第一行开始
                for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                        ArrayList<String>  rowList = new ArrayList<>();
                        int emptyCount = 0;
                        for (int c = 0; c < totalCells; c++) {
                            XSSFCell cell = xssfRow.getCell(c);
                            if (cell == null) {
                                rowList.add(ExcelUtil.EMPTY);
                                emptyCount++;
                                continue;
                            }
                            //治标不治本 %保留精确小数4位
                            String cellValue = null;
                            if (c == 11) {
                                cellValue = ExcelUtil.getXValueForFourPoint(cell).trim();
                            }else{
                                cellValue = ExcelUtil.getXValue(cell).trim();
                            }

                            if ("".equalsIgnoreCase(cellValue)) {
                                emptyCount++;
                                rowList.add(ExcelUtil.EMPTY);
                                continue;
                            }
                            rowList.add(cellValue);
                        }
                        if (emptyCount >= totalCells) {
                            continue;
                        }
                        list.add(rowList);
                    }
                }
                excels.put(numSheet+1,list);
            }
            return excels;
        } catch (IOException e) {
            logger.error("读取excel 出错", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("关闭输入流出错", e);
            }
        }
        return null;
    }

    public HashMap<Integer,List<ArrayList<String>>> readXlss(InputStream in) {
        HashMap<Integer,List<ArrayList<String>>> excels = new HashMap<>();
        try {
            // 创建文档
            HSSFWorkbook wb  = new HSSFWorkbook(in);
            //读取sheet(页)
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                List<ArrayList<String>> list = new ArrayList<>();
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                int totalRows = hssfSheet.getLastRowNum();
                int totalCells = hssfSheet.getRow(0).getPhysicalNumberOfCells();
                //读取Row,从第二行开始
                for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        ArrayList<String> rowList = new ArrayList<>();
                        int emptyCount = 0;
                        for (int c = 0; c < totalCells; c++) {
                            HSSFCell cell = hssfRow.getCell(c);
                            if (cell == null) {
                                rowList.add(ExcelUtil.EMPTY);
                                emptyCount++;
                                continue;
                            }

                            //治标不治本 %保留精确小数4位
                            String cellValue = null;
                            if (c == 11) {
                                cellValue = ExcelUtil.getHValueForFourPoint(cell).trim();
                            }else{
                                cellValue = ExcelUtil.getHValue(cell).trim();
                            }
                            if ("".equalsIgnoreCase(cellValue)) {
                                emptyCount++;
                                rowList.add(ExcelUtil.EMPTY);
                                continue;
                            }
                            rowList.add(cellValue);
                        }
                        if (emptyCount >= totalCells) {
                            continue;
                        }
                        list.add(rowList);
                    }
                }
                excels.put(numSheet+1,list);
            }
            return excels;
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("IOException",e);
            }
        }
        return null;
    }

    /**
     * 通用 读取excel2007
     * @param in
     * @return
     */
    public HashMap<Integer,List<ArrayList<String>>> readXlsxForCommon(InputStream in) {
        HashMap<Integer,List<ArrayList<String>>> excels = new HashMap<>();
        // IO流读取文件
        try {
            // 创建文档
            XSSFWorkbook wb  = new XSSFWorkbook(in);
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                List<ArrayList<String>> list = new ArrayList<>();
                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                int totalRows = xssfSheet.getLastRowNum();
                if(xssfSheet.getRow(0)==null){
                    throw new ErrorCodeException(ErrorEnum.EXCEL_FALSE);
                }
                int totalCells = xssfSheet.getRow(0).getPhysicalNumberOfCells();
                //读取Row,从第一行开始
                for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                        ArrayList<String>  rowList = new ArrayList<>();
                        int emptyCount = 0;
                        for (int c = 0; c < totalCells; c++) {
                            XSSFCell cell = xssfRow.getCell(c);
                            if (cell == null) {
                                rowList.add(ExcelUtil.EMPTY);
                                emptyCount++;
                                continue;
                            }

                            String cellValue = ExcelUtil.getXValueForCommon(cell).trim();
                            if ("".equalsIgnoreCase(cellValue)) {
                                emptyCount++;
                                rowList.add(ExcelUtil.EMPTY);
                                continue;
                            }
                            rowList.add(cellValue);
                        }
                        if (emptyCount >= totalCells) {
                            continue;
                        }
                        list.add(rowList);
                    }
                }
                excels.put(numSheet+1,list);
            }
            return excels;
        } catch (IOException e) {
            logger.error("读取excel 出错", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("关闭输入流出错", e);
            }
        }
        return null;
    }

    /**
     * 通用 读取excel2003
     * @param in
     * @return
     */
    public HashMap<Integer,List<ArrayList<String>>> readXlsForCommon(InputStream in) {
        HashMap<Integer,List<ArrayList<String>>> excels = new HashMap<>();
        try {
            // 创建文档
            HSSFWorkbook wb  = new HSSFWorkbook(in);
            //读取sheet(页)
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                List<ArrayList<String>> list = new ArrayList<>();
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                int totalRows = hssfSheet.getLastRowNum();
                int totalCells = hssfSheet.getRow(0).getPhysicalNumberOfCells();
                //读取Row,从第二行开始
                for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        ArrayList<String> rowList = new ArrayList<>();
                        int emptyCount = 0;
                        for (int c = 0; c < totalCells; c++) {
                            HSSFCell cell = hssfRow.getCell(c);
                            if (cell == null) {
                                rowList.add(ExcelUtil.EMPTY);
                                emptyCount++;
                                continue;
                            }
                            String cellValue = ExcelUtil.getHValueForCommon(cell).trim();
                            if ("".equalsIgnoreCase(cellValue)) {
                                emptyCount++;
                                rowList.add(ExcelUtil.EMPTY);
                                continue;
                            }
                            rowList.add(cellValue);
                        }
                        if (emptyCount >= totalCells) {
                            continue;
                        }
                        list.add(rowList);
                    }
                }
                excels.put(numSheet+1,list);
            }
            return excels;
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("IOException",e);
            }
        }
        return null;
    }


    /**
     * 读取excel2003
     * @param in
     * @return
     */
    public HashMap<Integer,List<List<String>>> parseXls(InputStream in) {
        HashMap<Integer,List<List<String>>> excels = new HashMap<>();
        try {
            // 创建文档
            HSSFWorkbook wb  = new HSSFWorkbook(in);
            //读取sheet(页)
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                List<List<String>> list = new ArrayList<>();
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                int totalRows = hssfSheet.getLastRowNum();
                int totalCells = hssfSheet.getRow(0).getPhysicalNumberOfCells();
                //读取Row,从第二行开始
                for (int rowNum = 0; rowNum <= totalRows; rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        ArrayList<String> rowList = new ArrayList<>();
                        int emptyCount = 0;
                        for (int c = 0; c < totalCells; c++) {
                            HSSFCell cell = hssfRow.getCell(c);
                            if (cell == null) {
                                rowList.add(ExcelUtil.EMPTY);
                                emptyCount++;
                                continue;
                            }
                            String cellValue = ExcelUtil.getHValueForCommon(cell).trim();
                            if ("".equalsIgnoreCase(cellValue)) {
                                emptyCount++;
                                rowList.add(ExcelUtil.EMPTY);
                                continue;
                            }
                            rowList.add(cellValue);
                        }
                        if (emptyCount >= totalCells) {
                            continue;
                        }
                        list.add(rowList);
                    }
                }
                excels.put(numSheet+1,list);
            }
            return excels;
        } catch (IOException e) {
            logger.error("IOException",e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("IOException",e);
            }
        }
        return null;
    }

    /**
     * 用于解析多个sheet中的一个sheet 提示错误时会可带有sheetno
     * @param resultList
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> parseObject(List<List<String>> resultList, Class<?> type, String sheetNo){
        if(resultList == null) {
            logger.info("excel 文件解析为空");
            throw new ErrorCodeException();
        }
        logger.info("解析对象{}", type.getName());
        ExcelOrm orm = context.getOrmByClass(type.getName());
        if (orm == null) {
            logger.info("根据对象名找不到对应ORM");
            throw new ErrorCodeException();
        }
        Map<Integer, ColumnFieldRelation> relationMap = orm.getRelationMap();
        if(relationMap == null) {
            logger.info("关系对象缺失");
            throw new ErrorCodeException();
        }
        List<T> resultObject = new ArrayList<>();
        long r = 0;
        for (List<String> list :resultList ) {
            if(r == 0) {
                //第一条记录为列名校验列名
            }
            else {
                try {
                    T object =  (T)type.newInstance();
                    Map<String ,Object> unMatched = new HashMap<>();
                    for (int i = 0 ; i< list.size();i++) {
                        String item = list.get(i);
                        ColumnFieldRelation relation = relationMap.get(i+1);
                        if(relation == null) {
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            unMatched.put(resultList.get(0).get(i),item);
                        } else {
                            if(StringUtils.isNotBlank(item)){
                                item = item.trim();
                            }
                            relation.typeFilter(item, r+1,relation.getColumnNum(),sheetNo);
                            if(StringUtils.isBlank(item)) {
                                //允许为空 则不对对象字段赋值
                                continue;
                            }
                            Field field = type.getDeclaredField(relation.getFieldName());
                            field.setAccessible(true);
                            field.set(object,relation.typeConvert(field.getGenericType().toString(),r+1,relation.getColumnNum(),item,sheetNo));
                        }
                    }
                    if(unMatched.size() > 0) {
                        try {
                            logger.info("对象{} 存在未映射关系 {}",type.getName());
                            Field otherInfoField = type.getDeclaredField("otherInfo");
                            otherInfoField.setAccessible(true);
                            otherInfoField.set(object, unMatched);
                        }catch (NoSuchFieldException e) {
                            logger.info("对象{} 存在未映射关系但未定义otherInfo",type.getName());
                        }
                    }
                    resultObject.add(object);
                } catch (InstantiationException |IllegalAccessException|NoSuchFieldException e) {
                    logger.error("构建实例出错",e);
                    throw new ErrorCodeException();
                }
            }
            r++;
        }
        return resultObject;
    }

}
