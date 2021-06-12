package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;

/**
 * @Description: 促销活动处理组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:39
 */
public interface PromotionActivityCalculator {

    /**
     * 处理促销活动
     * @param item 订单提哦啊吗
     * @param promotionActivity 促销活动
     * @return 促销活动计算结果
     * @throws Exception
     */
    PromotionActivityResult calculate(OrderItemDTO item, PromotionActivityDTO promotionActivity) throws Exception;
}
