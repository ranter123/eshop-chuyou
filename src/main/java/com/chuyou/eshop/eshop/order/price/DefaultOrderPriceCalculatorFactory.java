package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 默认订单价格计算工厂组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:48
 */
@Component
public class DefaultOrderPriceCalculatorFactory implements OrderPriceCalculatorFactory {

    /**
     * 默认价格订单计算组件
     */
    @Autowired
    private DefaultTotalPriceCalculator defaultTotalPriceCalculator;

    /**
     * 默认促销活动计算组件
     */
    @Autowired
    private DefaultPromotionActivityCalculator defaultPromotionActivityCalculator;

    /**
     * 默认运费计算组件
     */
    @Autowired
    private DefaultFreightCalculator defaultFreightCalculator;

    /**
     * 创建订单总金额价格计算组件
     * @return 订单总金额价格计算组件
     */
    @Override
    public TotalPriceCalculator createTotalPriceCalculator() {
        return defaultTotalPriceCalculator;
    }

    /**
     * 创建促销活动价格计算组件
     * @param promotionActivity 促销活动
     * @return 促销活动价格计算组件
     */
    @Override
    public PromotionActivityCalculator createPromotionActivityCalculator(PromotionActivityDTO promotionActivity) {
        return defaultPromotionActivityCalculator;
    }

    /**
     * 创建运费价格计算组件
     * @return 运费价格计算组件
     */
    @Override
    public FreightCalculator createFreightCalculator() {
        return defaultFreightCalculator;
    }
}
