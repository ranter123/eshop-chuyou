package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuDTO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;

/**
 * @Description: 赠品类型的促销活动计算组件的基类
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:23
 */
public class AbstractGifPromotionActivityCalculator {

    /**
     * 创建订单条目
     * @param goodsSku 商品sku
     * @return 订单条目
     */
    protected OrderItemDTO createOrderItem(GoodsSkuDTO goodsSku) {
        OrderItemDTO orderItem = new OrderItemDTO();
        orderItem.setGoodsId(goodsSku.getGoodsId());
        orderItem.setGoodsSkuId(goodsSku.getId());
        orderItem.setGoodsSkuCode(goodsSku.getGoodsSkuCode());
        orderItem.setGoodsName(goodsSku.getGoodsName());
        orderItem.setSaleProperties(goodsSku.getSaleProperties());
        orderItem.setGoodsGrossWeight(goodsSku.getGrossWeight());
        orderItem.setPurchaseQuantity(1L);
        orderItem.setPurchasePrice(0.0);
        orderItem.setPromotionActivityId(null);
        orderItem.setGoodsLength(goodsSku.getGoodsLength());
        orderItem.setGoodsWidth(goodsSku.getGoodsWidth());
        orderItem.setGoodsHeight(goodsSku.getGoodsHeight());
        return orderItem;
    }
}
