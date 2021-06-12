package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import org.springframework.stereotype.Component;

/**
 * @Description: 默认价格计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:55
 */
@Component
public class DefaultTotalPriceCalculator implements TotalPriceCalculator{

    /**
     * 计算订单条目总金额
     * @param item 订单条目
     * @return 订单条目总金额
     */
    @Override
    public Double calculate(OrderItemDTO item) {
        return item.getPurchaseQuantity() * item.getPurchasePrice();
    }
}
