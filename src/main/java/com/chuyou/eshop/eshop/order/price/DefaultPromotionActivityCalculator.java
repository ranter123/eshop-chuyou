package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.stereotype.Component;

/**
 * @Description: 默认促销活动计算组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:55
 */
@Component
public class DefaultPromotionActivityCalculator implements PromotionActivityCalculator{

    /**
     * 默认处理促销活动
     * @param item 订单提哦啊吗
     * @param promotionActivity 促销活动
     * @return 促销活动计算结果
     * @throws Exception
     */
    @Override
    public PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception {
        return new PromotionActivityResult();
    }
}
