package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.CategoryPropertyRelationshipDAO;
import com.chuyou.eshop.eshop.commodity.domain.CategoryPropertyRelationshipDO;
import com.chuyou.eshop.eshop.commodity.mapper.CategoryPropertyRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 类目属性管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 19:19
 */
@Repository
public class CategoryPropertyRelationshipDAOImpl implements CategoryPropertyRelationshipDAO {

    @Autowired
    private CategoryPropertyRelationshipMapper categoryPropertyRelationMapper;

    /**
     * 新增类目属性关系
     * @param relation 类目属性关系
     * @throws Exception
     */
    @Override
    public void save(CategoryPropertyRelationshipDO relation) throws Exception {
        categoryPropertyRelationMapper.save(relation);
    }

    /**
     * 根据类目id查询类目与属性的关联关系
     * @param categoryId 类目id
     * @return 类目与属性的关联关系
     * @throws Exception
     */
    @Override
    public List<CategoryPropertyRelationshipDO> listByCategoryId(Long categoryId) throws Exception {
        return categoryPropertyRelationMapper.listByCategoryId(categoryId);
    }

    /**
     * 根据类目id查询类目与属性的关联关系
     * @param id 类目id
     * @return 类目与属性的关联关系
     * @throws Exception
     */
    @Override
    public CategoryPropertyRelationshipDO getById(Long id) throws Exception {
        return categoryPropertyRelationMapper.getById(id);
    }

    /**
     * 根据类目id删除类目与属性的关联关系
     * @param categoryId 类目id
     * @throws Exception
     */
    @Override
    public void removeByCategoryId(Long categoryId) throws Exception {
        categoryPropertyRelationMapper.removeByCategoryId(categoryId);
    }
}
