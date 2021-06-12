package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;

/**
 * @Description: 商品条目中金额价格计算器
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:38
 */
public interface TotalPriceCalculator {

    /**
     * 计算订单条目总金额
     * @param item 订单条目
     * @return 订单条目总金额
     */
    Double calculate(OrderItemDTO item);
}
