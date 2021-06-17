package com.chuyou.eshop.eshop.pay.service;

import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;

/**
 * @Description: 支付中心接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/16 19:44
 */
public interface PayService {

    /**
     * 获取支付二维码
     * @param order 订单
     * @return 支付二维码
     */
    String getQrCode(OrderInfoDTO order);

    /**
     * 进行退款
     * @param returnGoodsInputOrder 退货入库单
     * @return 退款结果
     */
    Boolean refund(ReturnGoodsInputOrderDTO returnGoodsInputOrder);


}
