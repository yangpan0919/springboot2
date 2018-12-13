package com.example.demo.excel.orm;

import java.util.List;
import java.util.Map;

/**
 * Created by Knight on 2017/12/26.
 */
public class ExcelOrm {

    private String className;
    private List<ColumnFieldRelation> relations;
    private Map<Integer, ColumnFieldRelation> relationMap;
    private List<String> titleList;
    private Map<String, ColumnFieldRelation> suffixRelationMap;
    private String omitSuffix;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ColumnFieldRelation> getRelations() {
        return relations;
    }

    public void setRelations(List<ColumnFieldRelation> relations) {
        this.relations = relations;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public Map<String, ColumnFieldRelation> getSuffixRelationMap() {
        return suffixRelationMap;
    }

    public void setSuffixRelationMap(Map<String, ColumnFieldRelation> suffixRelationMap) {
        this.suffixRelationMap = suffixRelationMap;
    }

    public Map<Integer, ColumnFieldRelation> getRelationMap() {
        return relationMap;
    }

    public void setRelationMap(Map<Integer, ColumnFieldRelation> relationMap) {
        this.relationMap = relationMap;
    }

    public String getOmitSuffix() {
        return omitSuffix;
    }

    public void setOmitSuffix(String omitSuffix) {
        this.omitSuffix = omitSuffix;
    }
}
