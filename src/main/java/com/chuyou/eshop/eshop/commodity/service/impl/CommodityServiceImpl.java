package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuDTO;
import com.chuyou.eshop.eshop.commodity.service.CommodityService;
import com.chuyou.eshop.eshop.commodity.service.GoodsService;
import com.chuyou.eshop.eshop.commodity.service.GoodsSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 商品中心对外接口service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:51
 */
@Component
public class CommodityServiceImpl implements CommodityService {

    private static final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    /**
     * 商品sku管理service组件
     */
    @Autowired
    private GoodsSkuService goodsSkuService;
    /**
     * 商品管理service组件
     */
    @Autowired
    private GoodsService goodsService;

    /**
     * 根据id查询商品sku
     * @param goodsSkuId 商品sku id
     * @return 商品sku DTO
     */
    @Override
    public GoodsSkuDTO getGoodsSkuById(Long goodsSkuId) {
        try {
            return goodsSkuService.getById(goodsSkuId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查询商品
     * @param goodsId 商品id
     * @return 商品
     */
    @Override
    public GoodsDTO getGoodsById(Long goodsId) {
        try {
            return goodsService.getById(goodsId);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

}
