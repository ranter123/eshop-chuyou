package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.PropertyDAO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyDO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyQuery;
import com.chuyou.eshop.eshop.commodity.mapper.PropertyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 商品属性管理模块的DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 19:35
 */
@Repository
public class PropertyDAOImpl implements PropertyDAO {

    /**
     * 商品属性管理模块的mapper组件
     */
    @Autowired
    private PropertyMapper propertyMapper;

    /**
     * 分页查询商品属性
     * @param propertyQuery 查询条件
     * @return 商品属性
     * @throws Exception
     */
    @Override
    public List<PropertyDO> listPropertiesByPage(PropertyQuery propertyQuery) throws Exception {
        return propertyMapper.listPropertiesByPage(propertyQuery);
    }

    /**
     * 新增商品属性
     * @param propertyDO 商品属性DO对象
     * @throws Exception
     */
    @Override
    public void saveProperty(PropertyDO propertyDO) throws Exception {
        propertyMapper.saveProperty(propertyDO);
    }

    /**
     * 根据id查询商品属性
     * @param id 商品属性id
     * @return 商品属性
     * @throws Exception
     */
    @Override
    public PropertyDO getPropertyById(Long id) throws Exception {
        return propertyMapper.getPropertyById(id);
    }

    /**
     * 更新商品属性
     * @param propertyDO 商品属性DO对象
     * @throws Exception
     */
    @Override
    public void updateProperty(PropertyDO propertyDO) throws Exception {
        propertyMapper.updateProperty(propertyDO);
    }
}
