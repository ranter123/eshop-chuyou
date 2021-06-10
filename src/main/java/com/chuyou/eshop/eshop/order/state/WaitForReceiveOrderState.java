package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:16 下午
 * @Description: 待收货状态
 */
@Component
public class WaitForReceiveOrderState extends AbstractOrderState {

    @Autowired
    public WaitForReceiveOrderState(OrderInfoDAO orderInfoDAO) {
        super(orderInfoDAO);
    }

    @Override
    protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
        return OrderStatus.WAIT_FOR_RECEIVE;
    }

    @Override
    public Boolean canConfirmReceipt(OrderInfoDTO order) throws Exception {
        return true;
    }


}
