package com.example.demo.excel.configure;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;


public class ExcelConfigMapperNode {


    private String className;
    /**
     * 忽略字段后缀
     */
    private String omitSuffix;

    private List<ExcelConfigRelationNode> relations;

    @XmlAttribute(name="class")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement(name="relation")
    public List<ExcelConfigRelationNode> getRelations() {
        return relations;
    }

    public void setRelations(List<ExcelConfigRelationNode> relations) {
        this.relations = relations;
    }

    @XmlAttribute(name="omitSuffix")
    public String getOmitSuffix() {
        return omitSuffix;
    }

    public void setOmitSuffix(String omitSuffix) {
        this.omitSuffix = omitSuffix;
    }


}
