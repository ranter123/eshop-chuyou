package com.chuyou.eshop.eshop.commodity.dao;

import com.chuyou.eshop.eshop.commodity.domain.PropertyGroupDO;

import java.util.List;

/**
 * @Description: 属性分组管理DAO组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 19:39
 */
public interface PropertyGroupDAO {

    /**
     * 新增属性分组
     * @param group 属性分组
     * @return 属性分组id
     * @throws Exception
     */
    Long save(PropertyGroupDO group) throws Exception;

    /**
     * 根据类目id查询属性分组
     * @param categoryId 类目id
     * @return 属性分组
     * @throws Exception
     */
    List<PropertyGroupDO> listByCategoryId(Long categoryId) throws Exception;

    /**
     * 根据类目id删除属性分组
     * @param categoryId 类目id
     * @throws Exception
     */
    void removeByCategoryId(Long categoryId) throws Exception;
}
