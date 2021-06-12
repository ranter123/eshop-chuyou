package com.chuyou.eshop.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuyou.eshop.eshop.common.json.JsonExtractor;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 多买优惠促销活动价格计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:32
 */
@Component
public class MultiDiscountPromotionActivityCalculator implements PromotionActivityCalculator{

    /**
     * json解析组件
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    /**
     * 多买优惠促销活动价格计算
     * @param item 订单提哦啊吗
     * @param promotionActivity 促销活动
     * @return 多买优惠促销活动结果
     * @throws Exception
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception {
        Double totalAmount = item.getPurchasePrice() * item.getPurchaseQuantity();
        Long purchaseCount = item.getPurchaseQuantity();
        String ruleJson = promotionActivity.getRule();
        JSONArray rules = JSONArray.parseArray(ruleJson);
        for (int i = 0; i < rules.size(); i ++) {
            JSONObject rule = rules.getJSONObject(i);

            Long thresholdCount = jsonExtractor.getLong(rule, "thresholdCount");
            Double discountRate = jsonExtractor.getDouble(rule, "discountRate");

            if (purchaseCount > thresholdCount) {
                return new PromotionActivityResult(totalAmount * (1.0 - discountRate));
            }
        }
        return new PromotionActivityResult();
    }
}
