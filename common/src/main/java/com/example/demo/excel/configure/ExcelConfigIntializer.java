package com.example.demo.excel.configure;


import com.example.demo.excel.convertor.ExcelDataConverter;
import com.example.demo.excel.convertor.ExcelGenericConverter;
import com.example.demo.excel.orm.ColumnFieldRelation;
import com.example.demo.excel.orm.ExcelOrm;
import com.example.demo.excel.orm.ExcelOrmContext;
import com.example.demo.excel.validator.ExcelDataValidator;
import com.example.demo.excel.validator.ExcelGenericDataValidator;
import com.example.demo.utils.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelConfigIntializer {

    private static Logger logger = LoggerFactory.getLogger(ExcelConfigIntializer.class);

    public static String readConfig () {
        InputStream in = ExcelConfigIntializer.class.getClassLoader().getResourceAsStream("excelMapping-config.xml");
        if(in != null) {
            BufferedReader br = null;
            InputStreamReader inr = null;
            StringBuffer sb = null;
            try {
                inr = new InputStreamReader(in,"UTF-8");
                br = new BufferedReader(inr);
                sb = new StringBuffer();
                String line = br.readLine();
                while( line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                logger.error("读取数据异常",e);
            } finally {
                try {
                    br.close();
                    inr.close();
                    in.close();
                } catch (IOException e) {
                    logger.error("关闭流异常",e);
                }
            }
            String configStr = sb.toString();
            return configStr;
        } else {
            logger.warn("找不到excel映射配置文件");
            return null;
        }
    }

    public static ExcelOrmContext getInitConfig(){
        String configStr = readConfig();
        return doGetInitConfig(configStr);
    }

    private static ExcelOrmContext doGetInitConfig(String config) {
        XmlUtil parser = new XmlUtil(ExcelOrmConfigRoot.class);
        ExcelOrmConfigRoot root = parser.fromXml(config);
        ExcelOrmContext context = new ExcelOrmContext();
        if( root == null ) {
            logger.info("Excel组件配置文件为空");
            return context;
        }
        List<ExcelConfigMapperNode> mapperList = root.getMapperList();
        List<ValidatorNode> validatorList = root.getValidatorList();
        List<ConverterNode> converterList = root.getConverterList();
        if(validatorList != null){
            doInitValidators(context,validatorList);
        }
        if (converterList != null) {
            doInitConverters(context,converterList);
        }
        if( mapperList != null ) {
            doInitMappers(context,mapperList);
        }
        return context;
        //校验通过vsd处理 TODO: 2017/12/28


    }
    private static void doInitValidators(ExcelOrmContext context, List<ValidatorNode> validatorNodes) {
        for (ValidatorNode node : validatorNodes){
            if(node.getClazz() == null ||"".equals(node.getClazz().trim())) {
                ExcelDataValidator validator = new ExcelGenericDataValidator();
                validator.setRegex(node.getRegexValue());
                context.getValidatorMap().put(node.getId(),validator);
            } else {
                try {
                    Class clazz = Class.forName(node.getClazz());
                    if(ExcelDataValidator.class.isAssignableFrom(clazz)) {
                        ExcelDataValidator validator = (ExcelDataValidator)clazz.newInstance();
                        validator.setRegex(node.getRegexValue());
                        context.getValidatorMap().put(node.getId(),validator);
                    } else{
                        logger.warn("验证器类型错误{}",node.getClazz());
                        throw new RuntimeException("验证器类型错误 需继承ExcelDataValidator");
                    }
                } catch (ClassNotFoundException |InstantiationException| IllegalAccessException e) {
                    logger.error("初始化validator失败",e);
                    throw new RuntimeException("初始化validator失败",e);
                }
            }
        }
    }

    private static void doInitConverters(ExcelOrmContext context,List<ConverterNode> converterNodes) {
        for (ConverterNode node : converterNodes){
            if(node.getClazz() == null ||"".equals(node.getClazz().trim())) {
                ExcelDataConverter converter = new ExcelGenericConverter();
                context.getConverterMap().put(node.getId(),converter);
            } else {
                try {
                    Class clazz = Class.forName(node.getClazz());
                    if(ExcelDataConverter.class.isAssignableFrom(clazz)) {
                        ExcelDataConverter converter = (ExcelDataConverter)clazz.newInstance();
                        context.getConverterMap().put(node.getId(),converter);
                    } else{
                        logger.warn("验证器类型错误{}",node.getClazz());
                        throw new RuntimeException("验证器类型错误 需继承ExcelDataConverterr");
                    }
                } catch (ClassNotFoundException |InstantiationException| IllegalAccessException e) {
                    logger.error("初始化converter失败",e);
                    throw new RuntimeException("初始化converter失败",e);
                }
            }
        }
    }

    private static void doInitMappers(ExcelOrmContext context,List<ExcelConfigMapperNode> mapperList) {
        for ( ExcelConfigMapperNode mapperNode : mapperList) {
            ExcelOrm orm = new ExcelOrm();
            orm.setClassName(mapperNode.getClassName());
            orm.setOmitSuffix(mapperNode.getOmitSuffix());
            List<ColumnFieldRelation> relations = new ArrayList();
            Map<Integer ,ColumnFieldRelation> relationMap = new HashMap();
            Map<String, ColumnFieldRelation> suffixRelationMap = new HashMap<>();
            for (ExcelConfigRelationNode relationNode : mapperNode.getRelations()) {
                ColumnFieldRelation relation = new ColumnFieldRelation();
                relation.setColumnName(relationNode.getColumnName());
                relation.setColumnNum(relationNode.getColumnNo());
                relation.setFieldName(relationNode.getFieldName());

                if(relationNode.getValidatorId() != null && context.getValidatorMap().get(relationNode.getValidatorId()) == null) {
                    logger.warn("找不到对应validator {}", relationNode.getValidatorId());
                    throw new RuntimeException("找不到对应validator");
                } else {
                    relation.setValidator(context.getValidatorMap().get(relationNode.getValidatorId()));
                }
                if(relationNode.getConverterId() == null) {
                    relation.setConverter(context.getDefaultConverter());
                } else if(context.getConverterMap().get(relationNode.getConverterId()) == null) {
                    logger.warn("找不到对应converter {}", relationNode.getConverterId());
                    throw new RuntimeException("找不到对应converter");
                } else {
                    relation.setConverter(context.getConverterMap().get(relationNode.getConverterId()));
                }
                if(relationNode.getRequired() !=null && StringUtils.isEmpty(relationNode.getSuffix())){
                    logger.warn("必填字段找不到对应的下标，字段名{}", relationNode.getColumnName());
                    throw new RuntimeException("必填字段找不到对应的下标");
                }
                relation.setRequired(relationNode.getRequired()==null? false:relationNode.getRequired());
                if(!StringUtils.isEmpty(relationNode.getSuffix())){
                    relation.setSuffix(relationNode.getSuffix());
                    suffixRelationMap.put(relationNode.getSuffix(), relation);
                }
                relations.add(relation);
                relationMap.put(relation.getColumnNum(), relation);
            }
            orm.setRelations(relations);
            orm.setRelationMap(relationMap);
            orm.setSuffixRelationMap(suffixRelationMap);
            context.getMappingCache().put(orm.getClassName(), orm);
        }
    }

}
