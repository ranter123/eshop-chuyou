package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.PropertyGroupDAO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyGroupDO;
import com.chuyou.eshop.eshop.commodity.mapper.PropertyGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 属性分组管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 19:41
 */
@Repository
public class PropertyGroupDAOImpl implements PropertyGroupDAO {

    /**
     * 属性分组管理mapper组件
     */
    @Autowired
    private PropertyGroupMapper propertyGroupMapper;

    /**
     * 新增属性分组
     * @param group 属性分组
     * @return 属性分组id
     * @throws Exception
     */
    @Override
    public Long save(PropertyGroupDO group) throws Exception {
        propertyGroupMapper.save(group);
        return group.getId();
    }

    /**
     * 根据类目id查询属性分组
     * @param categoryId 类目id
     * @return 属性分组
     * @throws Exception
     */
    @Override
    public List<PropertyGroupDO> listByCategoryId(Long categoryId) throws Exception {
        return propertyGroupMapper.listByCategoryId(categoryId);
    }

    /**
     * 根据类目id删除属性分组
     * @param categoryId 类目id
     * @throws Exception
     */
    @Override
    public void removeByCategoryId(Long categoryId) throws Exception {
        propertyGroupMapper.removeByCategoryId(categoryId);
    }
}
