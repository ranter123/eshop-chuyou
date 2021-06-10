package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/6/9 8:56 下午
 * @Description: 已取消状态
 */
@Component
public class CanceledOrderState extends AbstractOrderState {

    @Autowired
    public CanceledOrderState(OrderInfoDAO orderInfoDAO) {
        super(orderInfoDAO);
    }

    /**
     * 获取订单状态
     * @param order 订单
     * @return
     * @throws Exception
     */
    @Override
    protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
        return OrderStatus.CANCELED;
    }
}
