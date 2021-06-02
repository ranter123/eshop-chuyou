package com.chuyou.eshop.eshop.commodity.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description: 查询属性类目操作
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 20:03
 */
@Component
@Scope("prototype")
public class QueryPropertyCategoryOperation implements CategoryOperation<Properties>{


    @Override
    public Properties doExecute(Category category) throws Exception {
        return null;
    }
}
