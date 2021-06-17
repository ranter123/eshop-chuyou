package com.chuyou.eshop.eshop.purchase.constant;

/**
 * @Description: 采购单状态
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 19:55
 */
public class PurchaseOrderStatus {

    /**
     * 编辑中
     */
    public static final Integer EDITING = 1;
    /**
     * 待审核
     */
    public static final Integer WAIT_FOR_APPROVE = 2;
    /**
     * 已审核
     */
    public static final Integer APPROVED = 3;
    /**
     * 待入库
     */
    public static final Integer WAIT_FOR_INPUT = 4;
    /**
     * 已入库
     */
    public static final Integer FINISHED_INPUT = 5;
    /**
     * 待结算
     */
    public static final Integer WAIT_FOR_SETTLEMENT = 6;
    /**
     * 已完成
     */
    public static final Integer FINISHED = 7;

    private PurchaseOrderStatus() {

    }

}
