package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 默认运费计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:55
 */
@Component
public class DefaultFreightCalculator implements FreightCalculator{

    /**
     * 物流中心接口
     */
    @Autowired
    private LogisticsService logisticsService;

    /**
     * 计算运费
     * @param order 订单
     * @param orderItem 订单条目
     * @param result 促销活动计算结果
     * @return 运费
     */
    @Override
    public Double calculate(OrderInfoDTO order, OrderItemDTO orderItem, PromotionActivityResult result) {
        return logisticsService.calculateFreight(order, orderItem);
    }
}
