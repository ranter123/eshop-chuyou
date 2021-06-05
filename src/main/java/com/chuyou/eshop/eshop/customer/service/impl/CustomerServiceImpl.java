package com.chuyou.eshop.eshop.customer.service.impl;

import com.chuyou.eshop.eshop.customer.constant.ReturnGoodsWorksheetStatus;
import com.chuyou.eshop.eshop.customer.dao.ReturnGoodsWorksheetDAO;
import com.chuyou.eshop.eshop.customer.domain.ReturnGoodsWorksheetDO;
import com.chuyou.eshop.eshop.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 客服中心对外service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 15:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    /**
     * 退货工单管理DAO组件
     */
    @Autowired
    private ReturnGoodsWorksheetDAO returnGoodsWorksheetDAO;

    /**
     * 订单中心service组件
     */
    @Autowired
    private OrderService orderService;

    /**
     * 创建退货工单
     * @param orderId 订单id
     * @param orderNo 订单编号
     * @param returnGoodsReason 退货原因
     * @param returnGoodsRemark 退货备注
     * @return
     */
    @Override
    public Boolean createReturnGoodsWorksheet(Long orderId, String orderNo,
                                              Integer returnGoodsReason,
                                              String returnGoodsRemark) throws Exception {
        try {
            ReturnGoodsWorksheetDO worksheet = new ReturnGoodsWorksheetDO();
            worksheet.setOrderInfoId(orderId);
            worksheet.setOrderNo(orderNo);
            worksheet.setReturnGoodsReason(returnGoodsReason);
            worksheet.setReturnGoodsRemark(returnGoodsRemark);
            worksheet.setStatus(ReturnGoodsWorksheetStatus.WAIT_FOR_APPROVE);

            returnGoodsWorksheetDAO.save(worksheet);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * 同步物流单号
     * @param orderInfoId 订单id
     * @param returnGoodsLogisticsCode 退货物流单号
     * @return
     */
    @Override
    public Boolean syncReturnGoodsLogisticsCode(Long orderInfoId, String returnGoodsLogisticsCode) throws Exception {
        try {
            ReturnGoodsWorksheetDO worksheet = returnGoodsWorksheetDAO.getByOrderInfoId(orderInfoId);
            worksheet.setReturnGoodsLogisticsCode(returnGoodsLogisticsCode);
            returnGoodsWorksheetDAO.updateReturnGoodsLogisticsCode(worksheet);

            worksheet.setStatus(ReturnGoodsWorksheetStatus.WAIT_FOR_RECEIVE_RETURN_GOODS);
            returnGoodsWorksheetDAO.updateStatus(worksheet);

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 通知客服中心，“完成退货入库” 事件发生了
     * @param returnGoodsWorksheetId 退货工单id
     * @return
     */
    @Override
    public Boolean informReturnGoodsInputFinishedEvent(Long returnGoodsWorksheetId) {
        try {
            ReturnGoodsWorksheetDO worksheet = returnGoodsWorksheetDAO.getById(returnGoodsWorksheetId);
            worksheet.setStatus(ReturnGoodsWorksheetStatus.FINISH_RETURN_GOODS_INPUT);
            returnGoodsWorksheetDAO.updateStatus(worksheet);

            orderService.informReturnGoodsInputOrderApprovedEvent(worksheet.getOrderInfoId());

            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 通知客服中心，“完成退款” 事件发生了
     * @param returnGoodsWorksheetId 退货工单id
     * @return 处理结果
     */
    @Override
    public Boolean informRefundFinishedEvent(Long returnGoodsWorksheetId) {
        try {
            ReturnGoodsWorksheetDO worksheet = returnGoodsWorksheetDAO.getById(returnGoodsWorksheetId);
            worksheet.setStatus(ReturnGoodsWorksheetStatus.FINISH_RETURN_GOODS_REFUND);
            returnGoodsWorksheetDAO.updateStatus(worksheet);

            orderService.informRefundFinishedEvent(worksheet.getOrderInfoId());
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
