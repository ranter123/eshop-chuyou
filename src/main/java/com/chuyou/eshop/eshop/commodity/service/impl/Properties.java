package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.domain.CategoryPropertyRelationshipVO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyGroupVO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品属性
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 19:36
 */
public class Properties {

    /**
     * 类目与属性的关联关系
     */
    private List<CategoryPropertyRelationshipVO> propertyRelations = new ArrayList<>();

    /**
     * 类目关联的属性
     */
    private List<PropertyVO> properties = new ArrayList<>();

    /**
     * 属性分组
     */
    private List<PropertyGroupVO> propertyGroups = new ArrayList<>();


    public List<CategoryPropertyRelationshipVO> getPropertyRelations() {
        return propertyRelations;
    }

    public void setPropertyRelations(List<CategoryPropertyRelationshipVO> propertyRelations) {
        this.propertyRelations = propertyRelations;
    }

    public List<PropertyVO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyVO> properties) {
        this.properties = properties;
    }

    public List<PropertyGroupVO> getPropertyGroups() {
        return propertyGroups;
    }

    public void setPropertyGroups(List<PropertyGroupVO> propertyGroups) {
        this.propertyGroups = propertyGroups;
    }
}
