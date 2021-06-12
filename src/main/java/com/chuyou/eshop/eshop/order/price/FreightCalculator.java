package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;

/**
 * @Description: 运费计算组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:44
 */
public interface FreightCalculator {

    /**
     * 计算运费
     * @param order 订单
     * @param orderItem 订单条目
     * @param result 促销活动计算结果
     * @return 运费
     */
    Double calculate(OrderInfoDTO order, OrderItemDTO orderItem, PromotionActivityResult result);
}
