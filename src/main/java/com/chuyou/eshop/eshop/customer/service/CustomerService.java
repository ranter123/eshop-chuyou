package com.chuyou.eshop.eshop.customer.service;

/**
 * @Description: 客服中心对外提供接口service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 15:16
 */
public interface CustomerService {

    /**
     * 创建退货工单
     * @param orderId 订单id
     * @param orderNo 订单编号
     * @param returnGoodsReason 退货原因
     * @param returnGoodsRemark 退货备注
     * @return
     */
    Boolean createReturnGoodsWorksheet(Long orderId, String orderNo,
                                       Integer returnGoodsReason,
                                       String returnGoodsRemark) throws Exception;

    /**
     * 同步物流单号
     * @param orderInfoId 订单id
     * @param returnGoodsLogisticsCode 退货物流单号
     * @return
     */
    Boolean syncReturnGoodsLogisticsCode(Long orderInfoId, String returnGoodsLogisticsCode) throws Exception;

    /**
     * 通知客服中心，“完成退货入库” 事件发生了
     * @param returnGoodsWorksheetId 退货工单id
     * @return
     */
    Boolean informReturnGoodsInputFinishedEvent(Long returnGoodsWorksheetId);

    /**
     * 通知客服中心，“完成退款” 事件发生了
     * @param returnGoodsWorksheetId 退货工单id
     * @return 处理结果
     */
    Boolean informRefundFinishedEvent(Long returnGoodsWorksheetId);
}
