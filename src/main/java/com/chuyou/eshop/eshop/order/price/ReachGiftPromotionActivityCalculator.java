package com.chuyou.eshop.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuDTO;
import com.chuyou.eshop.eshop.commodity.service.CommodityService;
import com.chuyou.eshop.eshop.common.json.JsonExtractor;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 满赠类型的促销活动处理器
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:43
 */
@Component
public class ReachGiftPromotionActivityCalculator extends AbstractGifPromotionActivityCalculator
        implements PromotionActivityCalculator {

    /**
     * 商品中心接口组件
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * json解析器
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    /**
     * 满赠处理促销活动
     * @param item 订单提哦啊吗
     * @param promotionActivity 促销活动
     * @return 促销活动计算结果
     * @throws Exception
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity)
            throws Exception {
        Double totalAmount = item.getPurchasePrice() * item.getPurchaseQuantity();
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        Double thresholdAmount = jsonExtractor.getDouble(rule, "thresholdAmount");
        JSONArray giftGoodsSkuIds = rule.getJSONArray("giftGoodsSkuIds");
        for (totalAmount > thresholdAmount) {
            PromotionActivityResult result = new PromotionActivityResult();
            for (int i = 0; i < giftGoodsSkuIds.size(); i ++) {
                Long goodsSkuId = giftGoodsSkuIds.getLong(i);
                GoodsSkuDTO goodsSku = commodityService.getGoodsSkuById(goodsSkuId);
                result.getOrderItems().add(createOrderItem(goodsSku));
            }
            return result;
        }
        return new PromotionActivityResult();
    }
}
