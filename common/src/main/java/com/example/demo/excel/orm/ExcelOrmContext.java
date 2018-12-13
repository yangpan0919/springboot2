package com.example.demo.excel.orm;





import com.example.demo.excel.convertor.ExcelDataConverter;
import com.example.demo.excel.convertor.ExcelGenericConverter;
import com.example.demo.excel.validator.ExcelDataValidator;
import com.example.demo.excel.validator.ExcelGenericDataValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Knight on 2017/12/28.
 */
public class ExcelOrmContext {

    private Map<String, ExcelOrm> mappingCache;

    private Map<String,ExcelDataConverter> converterMap;

    private Map<String,ExcelDataValidator> validatorMap;


    public ExcelOrmContext(){
        mappingCache = new HashMap<>();
        converterMap = new HashMap<>();
        validatorMap = new HashMap<>();
    }

    public ExcelDataConverter getDefaultConverter() {
        return  new ExcelGenericConverter();
    }

    public ExcelDataValidator getDefaultValidator() {
        return new ExcelGenericDataValidator();
    }

    public ExcelOrm getOrmByClass(String clazz){
        return mappingCache.get(clazz);
    }

    public Map<String, ExcelOrm> getMappingCache() {
        return mappingCache;
    }

    public void setMappingCache(Map<String, ExcelOrm> mappingCache) {
        this.mappingCache = mappingCache;
    }

    public Map<String, ExcelDataConverter> getConverterMap() {
        return converterMap;
    }

    public void setConverterMap(Map<String, ExcelDataConverter> converterMap) {
        this.converterMap = converterMap;
    }

    public Map<String, ExcelDataValidator> getValidatorMap() {
        return validatorMap;
    }

    public void setValidatorMap(Map<String, ExcelDataValidator> validatorMap) {
        this.validatorMap = validatorMap;
    }
}
