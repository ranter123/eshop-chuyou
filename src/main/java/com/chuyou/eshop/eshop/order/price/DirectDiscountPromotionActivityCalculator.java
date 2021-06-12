package com.chuyou.eshop.eshop.order.price;

import com.alibaba.fastjson.JSONObject;
import com.chuyou.eshop.eshop.common.json.JsonExtractor;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description: 单品促销活动价格计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 10:17
 */
@Component
public class DirectDiscountPromotionActivityCalculator implements PromotionActivityCalculator{

    /**
     * json解析器
     */
    @Autowired
    private JsonExtractor jsonExtractor;

    /**
     * 处理促销活动
     * @param item 订单提哦啊吗
     * @param promotionActivity 促销活动
     * @return 促销活动计算结果
     * @throws Exception
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception {
        BigDecimal totalAmount = BigDecimal.valueOf(item.getPurchaseQuantity() * item.getPurchasePrice());
        JSONObject rule = JSONObject.parseObject(promotionActivity.getRule());
        BigDecimal discountRate = BigDecimal.valueOf(jsonExtractor.getDouble(rule, "discountRate"));
        BigDecimal discountAmountRate = BigDecimal.valueOf(1.0).subtract(discountRate);
        Double discountAmount = totalAmount.multiply(discountAmountRate).doubleValue();
        return new PromotionActivityResult(discountAmount);
    }
}
