package com.chuyou.eshop.eshop.commodity.service.impl;

/**
 * @Description: 类目操作
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/5/31 11:56
 */
public interface CategoryOperation<T> {

    /**
     * 执行类目操作
     * @param category 类目树节点
     * @return 处理结果
     * @throws Exception
     */
    T doExecute(Category category) throws Exception;
}
