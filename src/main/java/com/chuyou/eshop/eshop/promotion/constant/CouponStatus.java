package com.chuyou.eshop.eshop.promotion.constant;

/**
 * @Description: 优惠券状态
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/12 15:20
 */
public class CouponStatus {

    /**
     * 未开始
     */
    public static final Integer UNSTARTED = 1;
    /**
     * 发放中
     */
    public static final Integer GIVING_OUT = 2;
    /**
     * 已发完
     */
    public static final Integer GIVEN_OUT = 3;
    /**
     * 已过期
     */
    public static final Integer EXPIRED = 4;

    private CouponStatus() {

    }
}
