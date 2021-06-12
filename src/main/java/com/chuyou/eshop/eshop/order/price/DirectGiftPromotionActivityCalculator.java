package com.chuyou.eshop.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuDTO;
import com.chuyou.eshop.eshop.commodity.service.CommodityService;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 赠品促销类型的促销活动价格计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:22
 */
@Component
public class DirectGiftPromotionActivityCalculator extends AbstractGifPromotionActivityCalculator
        implements PromotionActivityCalculator{

    /**
     * 商品中心service组件
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 处理赠品促销活动
     * @param item 订单提哦啊吗
     * @param promotionActivity 促销活动
     * @return 促销活动计算结果
     * @throws Exception
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception {
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");
        PromotionActivityResult result = new PromotionActivityResult();
        for (int i = 0; i < giftGoodsSkuIds.size(); i ++) {
            Long goodsSkuId = giftGoodsSkuIds.getLong(i);
            GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
            result.getOrderItems().add(createOrderItem(goodsSku));
        }
        return result;
    }
}
