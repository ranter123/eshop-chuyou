package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;

/**
 * @Description: 订单状态
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/9 19:59
 */
public interface OrderState {

    /**
     * 订单流转到当前这个状态
     * @param order 订单
     * @throws Exception
     */
    void doTransition(OrderInfoDTO order) throws Exception;

    /**
     * 判断当前状态下能否执行取消订单操作
     * @param order 订单
     * @return 能否执行取消订单操作
     * @throws Exception
     */
    Boolean canCancel(OrderInfoDTO order) throws Exception;


    /**
     * 判断订单能否执行支付操作
     * @param order 订单
     * @return 能否执行支付操作
     * @throws Exception
     */
    Boolean canPay(OrderInfoDTO order) throws Exception;

    /**
     * 判断能否执行手动确认收货操作
     * @param order 订单
     * @return 能否执行手动确认收货的操作
     * @throws Exception
     */
    Boolean canConfirmReceipt(OrderInfoDTO order) throws Exception;

    /**
     * 判断能否申请退货
     * @param order 订单
     * @return 能否申请退货
     * @throws Exception
     */
    Boolean canApplyReturnGoods(OrderInfoDTO order) throws Exception;
}
