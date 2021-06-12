package com.chuyou.eshop.eshop.order.price;

import com.chuyou.eshop.eshop.promotion.constant.PromotionActivityType;
import com.chuyou.eshop.eshop.promotion.domain.PromotionActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 折扣订单价格计算工厂组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/11 8:49
 */
@Component
public class DiscountOrderPriceCalculatorFactory implements OrderPriceCalculatorFactory{

    @Autowired
    private DefaultTotalPriceCalculator defaultTotalPriceCalculator;
    /**
     * 满减类型的促销活动计算组件
     */
    @Autowired
    private ReachDiscountPromotionActivityCalculator reachDiscountPromotionActivityCalculator;
    /**
     * 多买优惠型的促销活动计算组件
     */
    @Autowired
    private MultiDiscountPromotionActivityCalculator multiDiscountPromotionActivityCalculator;
    /**
     * 单品促销型的促销活动计算组件
     */
    @Autowired
    private DirectGiftPromotionActivityCalculator directGiftPromotionActivityCalculator;
    /**
     * 默认的促销活动计算组件
     */
    @Autowired
    private DefaultPromotionActivityCalculator defaultPromotionActivityCalculator;
    /**
     * 默认的运费计算组件
     */
    @Autowired
    private DefaultFreightCalculator freightCalculator;

    /**
     * 折扣创建订单总金额价格计算组件
     * @return 订单总金额价格计算组件
     */
    @Override
    public TotalPriceCalculator createTotalPriceCalculator() {
        return defaultTotalPriceCalculator;
    }

    /**
     * 折扣创建促销活动价格计算组件
     * @param promotionActivity 促销活动
     * @return 促销活动价格计算组件
     */
    @Override
    public PromotionActivityCalculator createPromotionActivityCalculator(PromotionActivityDTO promotionActivity) {
        if (promotionActivity == null) {
            return defaultPromotionActivityCalculator;
        }
        Integer promotionActivityType = promotionActivity.getType();
        if (PromotionActivityType.REACH_DISCOUNT.equals(promotionActivityType)) {
            return reachDiscountPromotionActivityCalculator;
        } else if (PromotionActivityType.MULTI_DISCOUNT.equals(promotionActivityType)) {
            return multiDiscountPromotionActivityCalculator;
        } else if (PromotionActivityType.DIRECT_DISCOUNT.equals(promotionActivityType)) {
            return directGiftPromotionActivityCalculator;
        }
        return defaultPromotionActivityCalculator;
    }

    /**
     * 折扣创建运费价格计算组件
     * @return 运费价格计算组件
     */
    @Override
    public FreightCalculator createFreightCalculator() {
        return freightCalculator;
    }
}
