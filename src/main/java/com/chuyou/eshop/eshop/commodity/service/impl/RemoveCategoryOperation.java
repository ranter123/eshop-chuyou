package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.dao.CategoryDAO;
import com.chuyou.eshop.eshop.commodity.dao.CategoryPropertyRelationshipDAO;
import com.chuyou.eshop.eshop.commodity.dao.PropertyGroupDAO;
import com.chuyou.eshop.eshop.commodity.dao.PropertyGroupRelationshipDAO;
import com.chuyou.eshop.eshop.commodity.domain.PropertyGroupDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 类目删除操作
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 17:08
 */
@Component
@Scope("prototype")
public class RemoveCategoryOperation extends AbstractCategoryOperation<Boolean>{

    /**
     * 类目与属性关系管理DAO组件
     */
    private CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO;

    /**
     * 属性分组管理DAO组件
     */
    private PropertyGroupDAO propertyGroupDAO;

    /**
     * 属性分组与属性关系管理DAO组件
     */
    private PropertyGroupRelationshipDAO propertyGroupRelationshipDAO;

    @Autowired
    public RemoveCategoryOperation(CategoryDAO categoryDAO,
                                   CategoryPropertyRelationshipDAO categoryPropertyRelationshipDAO,
                                   PropertyGroupDAO propertyGroupDAO,
                                   PropertyGroupRelationshipDAO propertyGroupRelationshipDAO) {
        super(categoryDAO);
        this.categoryPropertyRelationshipDAO = categoryPropertyRelationshipDAO;
        this.propertyGroupDAO = propertyGroupDAO;
        this.propertyGroupRelationshipDAO = propertyGroupRelationshipDAO;
    }

    /**
     * 执行实际的操作
     * @param category 类目
     * @throws Exception
     */
    @Override
    protected void doRealExecute(Category category) throws Exception {
        removePropertyRelation(category);
        removePropertyGroup(category);
        removeCategory(category);
    }

    /**
     * 删除类目
     * @param category 类目
     */
    private void removeCategory(Category category) throws Exception {
        categoryDAO.remove(category.getCategoryId());
    }

    /**
     * 删除类目的属性分组
     * @param category 类目
     * @throws Exception
     */
    private void removePropertyGroup(Category category) throws Exception{
        List<PropertyGroupDO> propertyGroups = propertyGroupDAO.listByCategoryId(category.getCategoryId());
        for (PropertyGroupDO propertyGroupDO : propertyGroups) {
            propertyGroupRelationshipDAO.removeByPropertyGroupId(propertyGroupDO.getId());
        }
        propertyGroupDAO.removeByCategoryId(category.getCategoryId());
    }

    /**
     * 删除类目与属性的关联关系
     * @param category 类目
     */
    private void removePropertyRelation(Category category) throws Exception {
        categoryPropertyRelationshipDAO.removeByCategoryId(category.getCategoryId());
    }

    /**
     * 获取操作执行结果
     * @return 操作执行的结果
     * @throws Exception
     */
    @Override
    protected Boolean getResult() throws Exception {
        return true;
    }
}
