package com.chuyou.eshop.eshop.commodity.constant;

/**
 * @Description: 商品状态
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 16:39
 */
public class GoodsStatus {

    /**
     * 未知类型
     */
    public static final Integer UNKNOWN = 0;

    /**
     * 待审核
     */
    public static final Integer WAIT_FOR_APPROVE = 1;

    /**
     * 待上架
     */
    public static final Integer WAIT_FOR_PUT_ON_SHELVES = 2;

    /**
     * 审核不通过
     */
    public static final Integer APPROVE_REJECT = 3;

    /**
     * 已上架
     */
    public static final Integer PUTTED_ON_SHELVES = 4;

    public GoodsStatus() {
    }
}
