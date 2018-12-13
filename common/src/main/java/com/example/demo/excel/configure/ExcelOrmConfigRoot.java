package com.example.demo.excel.configure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name="excel-mapping")
public class ExcelOrmConfigRoot {

    private List<ExcelConfigMapperNode> mapperList;

    private List<ValidatorNode> validatorList;

    private List<ConverterNode> converterList;

    @XmlElement(name = "mapper")
    public List<ExcelConfigMapperNode> getMapperList() {
        return mapperList;
    }

    public void setMapperList(List<ExcelConfigMapperNode> mapperList) {
        this.mapperList = mapperList;
    }

    @XmlElement(name = "validator")
    public List<ValidatorNode> getValidatorList() {
        return validatorList;
    }

    public void setValidatorList(List<ValidatorNode> validatorList) {
        this.validatorList = validatorList;
    }

    @XmlElement(name = "converter")
    public List<ConverterNode> getConverterList() {
        return converterList;
    }

    public void setConverterList(List<ConverterNode> converterList) {
        this.converterList = converterList;
    }

    public ValidatorNode getRegexById(String id) {
        for(ValidatorNode reg : validatorList) {
            if(id.equals(reg.getId())) {
                return reg;
            }
        }
        return null;
    }
}
