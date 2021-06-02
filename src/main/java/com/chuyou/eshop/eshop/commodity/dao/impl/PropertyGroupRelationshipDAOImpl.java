package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.PropertyGroupRelationshipDAO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyGroupRelationshipDO;
import com.chuyou.eshop.eshop.commodity.mapper.PropertyGroupRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 属性分组与属性关系管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 19:46
 */
@Repository
public class PropertyGroupRelationshipDAOImpl implements PropertyGroupRelationshipDAO {

    /**
     * 属性分组与属性关系管理mapper组件
     */
    @Autowired
    private PropertyGroupRelationshipMapper propertyGroupRelationMapper;

    /**
     * 新增属性分组与属性关系
     * @param relation 属性分组与属性关系
     * @throws Exception
     */
    @Override
    public void save(PropertyGroupRelationshipDO relation) throws Exception {
        propertyGroupRelationMapper.save(relation);
    }

    /**
     * 根据属性分组id查询属性分组与属性的关联关系
     * @param propertyGroupId 属性分组id
     * @return 属性分组与属性的关联关系
     * @throws Exception
     */
    @Override
    public List<PropertyGroupRelationshipDO> listByPropertyGroupId(Long propertyGroupId) throws Exception {
        return propertyGroupRelationMapper.listByPropertyGroupId(propertyGroupId);
    }

    /**
     * 根据属性分组id删除属性分组与属性的关联关系
     * @param propertyGroupId 属性分组id
     * @throws Exception
     */
    @Override
    public void removeByPropertyGroupId(Long propertyGroupId) throws Exception {
        propertyGroupRelationMapper.removeByPropertyGroupId(propertyGroupId);
    }
}
