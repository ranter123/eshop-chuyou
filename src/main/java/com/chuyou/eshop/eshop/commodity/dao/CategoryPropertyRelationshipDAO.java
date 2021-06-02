package com.chuyou.eshop.eshop.commodity.dao;

import com.chuyou.eshop.eshop.commodity.domain.CategoryPropertyRelationshipDO;

import java.util.List;

/**
 * @Description: 类目属性关系管理DAO组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 17:53
 */
public interface CategoryPropertyRelationshipDAO {

    /**
     * 新增类目属性关系
     * @param relation 类目属性关系
     * @throws Exception
     */
    void save(CategoryPropertyRelationshipDO relation) throws Exception;

    /**
     * 根据类目id查询类目与属性的关联关系
     * @param categoryId 类目id
     * @return 类目与属性的关联关系
     * @throws Exception
     */
    List<CategoryPropertyRelationshipDO> listByCategoryId(Long categoryId) throws Exception;

    /**
     * 根据类目id查询类目与属性的关联关系
     * @param id 类目id
     * @return 类目与属性的关联关系
     * @throws Exception
     */
    CategoryPropertyRelationshipDO getById(Long id) throws Exception;

    /**
     * 根据类目id删除类目与属性的关联关系
     * @param categoryId 类目id
     * @throws Exception
     */
    void removeByCategoryId(Long categoryId) throws Exception;
}
