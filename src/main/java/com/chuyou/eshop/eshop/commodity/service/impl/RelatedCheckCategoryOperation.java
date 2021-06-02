package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description: 类目是否被商品关联的检查操作
 *  spring 默认情况下对bean都是维护一个单例的，bean scope 是singleton, 每次都是获取到同一个实例
 *  但是对于我们的这种情况下，我们需要的是类，prototype， bean scope 是 prototype, 每次都是获取到一个新的实例
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 16:43
 */
@Component
@Scope("prototype")
public class RelatedCheckCategoryOperation extends AbstractCategoryOperation<Boolean> {

    /**
     * 是否被商品关联
     */
    private Boolean related = false;

    /**
     * 商品管理DAO组件
     */
    private GoodsDAO goodsDAO;

    @Autowired
    public RelatedCheckCategoryOperation(CategoryDAO categoryDAO, GoodsDAO goodsDAO) {
        super(categoryDAO);
        this.goodsDAO = goodsDAO;
    }

    @Override
    protected void doRealExecute(Category category) throws Exception {
        Long count = goodsDAO.countByCategoryId(category.getCategoryId());
        if (count > 0) {
            this.related = true;
        }
    }

    @Override
    protected Boolean getResult() throws Exception {
        return related;
    }
}
