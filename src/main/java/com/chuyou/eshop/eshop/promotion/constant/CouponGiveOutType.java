package com.chuyou.eshop.eshop.promotion.constant;

/**
 * @Description: 优惠券发放类型
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:20
 */
public class CouponGiveOutType {

    /**
     * 可领取可发放
     */
    public static final Integer ACHIEVABLE_AND_GIVE_OUT = 1;
    /**
     * 仅可发放
     */
    public static final Integer ONLY_GIVE_OUT = 2;
    /**
     * 仅可领取
     */
    public static final Integer ONLY_ACHIEVABLE = 3;

    private CouponGiveOutType() {

    }
}
