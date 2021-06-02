package com.chuyou.eshop.eshop.commodity.service.impl;

/**
 * @Description: 类目
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 11:34
 */
public class Category {

    /**
     * 类目
     */
    private Long categoryId;

    public Category() {
    }

    public Category(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public <T> T execute(CategoryOperation<T> operation) throws Exception {
        return operation.doExecute(this);
    }
}
