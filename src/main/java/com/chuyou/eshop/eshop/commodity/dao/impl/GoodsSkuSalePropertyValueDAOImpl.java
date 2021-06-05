package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.GoodsSkuSalePropertyValueDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuSalePropertyValueDO;
import com.chuyou.eshop.eshop.commodity.mapper.GoodsSkuSalePropertyValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 商品sku属性值管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 10:23
 */
@Repository
public class GoodsSkuSalePropertyValueDAOImpl implements GoodsSkuSalePropertyValueDAO {
    /**
     * 商品sku销售属性值管理mapper组件
     */
    @Autowired
    private GoodsSkuSalePropertyValueMapper propertyValueMapper;

    /**
     * 根据商品sku id查询属性值
     * @param goodsSkuId 商品sku id
     * @return 属性值
     */
    @Override
    public List<GoodsSkuSalePropertyValueDO> listByGoodsSkuId(Long goodsSkuId) {
        return propertyValueMapper.listByGoodsSkuId(goodsSkuId);
    }

    /**
     * 新增商品sku销售属性值
     * @param propertyValue 商品sku销售属性值
     */
    @Override
    public void save(GoodsSkuSalePropertyValueDO propertyValue) {
        propertyValueMapper.save(propertyValue);
    }

    /**
     * 根据商品sku id删除属性值
     * @param goodsSkuId 商品sku id
     */
    @Override
    public void removeByGoodsSkuId(Long goodsSkuId) {
        propertyValueMapper.removeByGoodsSkuId(goodsSkuId);
    }
}
