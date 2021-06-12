package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 促销活动处理结果
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:41
 */
public class PromotionActivityResult {

    /**
     * 促销活动减免金额
     */
    private Double discountAmount = 0.0;

    /**
     * 赠品对应的订单条目
     */
    private List<OrderItemDTO> orderItems = new ArrayList<>();

    public PromotionActivityResult(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public PromotionActivityResult() {
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
