package com.chuyou.eshop.eshop.commodity.service;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuDTO;

/**
 * @Description: 商品中心对外提供的接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:51
 */
public interface CommodityService {
    /**
     * 根据id查询商品sku
     * @param goodsSkuId 商品sku id
     * @return 商品sku DTO
     */
    GoodsSkuDTO getGoodsSkuById(Long goodsSkuId);

    /**
     * 根据id查询商品
     * @param goodsId 商品id
     * @return 商品
     */
    GoodsDTO getGoodsById(Long goodsId);
}
