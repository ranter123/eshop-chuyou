package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 含赠品的运费计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 14:32
 */
@Component
public class GiftIncludeFreightCalculator implements FreightCalculator {

    /**
     * 物流中心接口
     */
    @Autowired
    private LogsticsService logsticsService;

    /**
     * 计算运费
     * @param order 订单
     * @param orderItem 订单条目
     * @param result 促销活动计算结果
     * @return
     */
    @Override
    public Double calculate(OrderInfoDTO order, OrderItemDTO orderItem, PromotionActivityResult result) {
        Double freight = 0.0;
        freight += logsticsService.calculateFreight(order, orderItem);
        for (OrderItemDTO giftItem : result.getOrderItems()) {
            freight += logsticsService.calculateFreight(order, giftItem);
        }
        return freight;
    }
}
