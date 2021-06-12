package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;

/**
 * @Description: 订单价格计算工厂组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:36
 */
public interface OrderPriceCalculatorFactory {

    /**
     * 创建订单总金额价格计算组件
     * @return 订单总金额价格计算组件
     */
    TotalPriceCalculator createTotalPriceCalculator();

    /**
     * 创建促销活动价格计算组件
     * @param promotionActivity 促销活动
     * @return 促销活动价格计算组件
     */
    PromotionActivityCalculator createPromotionActivityCalculator(PromotionActivityDTO promotionActivity);

    /**
     * 创建运费价格计算组件
     * @return 运费价格计算组件
     */
    FreightCalculator createFreightCalculator();
}
