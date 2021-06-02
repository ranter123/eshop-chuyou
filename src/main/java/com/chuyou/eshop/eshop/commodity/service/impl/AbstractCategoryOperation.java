package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.dao.CategoryDAO;
import com.chuyou.eshop.eshop.commodity.domain.CategoryDO;

import java.util.List;

/**
 * @Description: 类目操作的抽象基类
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 16:47
 */
public abstract class AbstractCategoryOperation<T> implements CategoryOperation<T> {

    CategoryDAO categoryDAO;

    public AbstractCategoryOperation(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * 执行类目操作
     * @param category 类目树节点
     * @return
     * @throws Exception
     */
    @Override
    public T doExecute(Category category) throws Exception {
        doExecuteForChildren(category);
        doRealExecute(category);
        return null;
    }

    /**
     * 执行实际的操作
     * @param category 类目
     */
    protected abstract void doRealExecute(Category category) throws Exception;

    /**
     * 递归对子类目执行当前操作
     * @param category 当前类目
     * @throws Exception
     */
    private void doExecuteForChildren(Category category) throws Exception{
        List<CategoryDO> children = categoryDAO.listChildren(category.getCategoryId());
        if (children != null && children.size() > 0) {
            for (CategoryDO child : children) {
                Category childCategory = new Category(child.getId());
                childCategory.execute(this);
            }
        }
    }

    /**
     * 获取操作的执行结果
     * @return 操作的执行结果
     * @throws Exception
     */
    protected abstract T getResult() throws Exception;
}
