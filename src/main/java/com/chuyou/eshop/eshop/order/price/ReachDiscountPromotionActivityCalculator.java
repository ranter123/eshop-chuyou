package com.chuyou.eshop.eshop.order.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuyou.eshop.eshop.common.json.JsonExtractor;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 满减类型的促销活动价格计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:38
 */
@Component
public class ReachDiscountPromotionActivityCalculator implements PromotionActivityCalculator{

    /**
     * json解析器
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    /**
     * 满减处理促销活动
     * @param item 订单提哦啊吗
     * @param promotionActivity 促销活动
     * @return 促销活动计算结果
     * @throws Exception
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception {
        Double totalAmount = item.getPurchasePrice() * item.getPurchaseQuantity();
        String rulesJson = promotionActivity.getRule();
        JSONArray rules = JSONArray.parseArray(rulesJson);
        for (int i = 0; i < rules.size(); i ++) {
            JSONObject rule = rules.getJSONObject(i);
            Double thresholdAmount = jsonExtractor.getDouble(rule, "thresholdAmount");
            Double reduceAmount = jsonExtractor.getDouble(rule, "reduceAmount");
            if (totalAmount > thresholdAmount) {
                return new PromotionActivityResult(reduceAmount);
            }
        }
        return new PromotionActivityResult();
    }
}
