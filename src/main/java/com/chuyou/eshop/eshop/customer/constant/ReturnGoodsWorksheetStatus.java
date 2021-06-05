package com.chuyou.eshop.eshop.customer.constant;

/**
 * @Description: 退货工单状态
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 14:53
 */
public class ReturnGoodsWorksheetStatus {

    /**
     * 待审核
     */
    public static final Integer WAIT_FOR_APPROVE = 1;
    /**
     * 审核不通过
     */
    public static final Integer APPROVE_REJECTED = 2;
    /**
     * 待寄送退货商品
     */
    public static final Integer WAIT_FOR_SEND_OUT_RETURN_GOODS = 3;
    /**
     * 待收货退货商品
     */
    public static final Integer WAIT_FOR_RECEIVE_RETURN_GOODS = 4;
    /**
     * 退货商品待入库
     */
    public static final Integer WAIT_FOR_RETURN_GOODS_INPUT = 5;
    /**
     * 退货商品已入库
     */
    public static final Integer FINISH_RETURN_GOODS_INPUT = 6;
    /**
     * 完成退货退款
     */
    public static final Integer FINISH_RETURN_GOODS_REFUND = 7;

    private ReturnGoodsWorksheetStatus() {

    }


}
