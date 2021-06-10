package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:15 下午
 * @Description: 待付款状态
 */
@Component
public class WaitForPayOrderState extends AbstractOrderState {

    @Autowired
    public WaitForPayOrderState(OrderInfoDAO orderInfoDAO) {
        super(orderInfoDAO);
    }

    @Override
    protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
        return OrderStatus.WAIT_FOR_PAY;
    }

    @Override
    public Boolean canPay(OrderInfoDTO order) throws Exception {
        return true;
    }

    @Override
    public Boolean canCancel(OrderInfoDTO order) throws Exception {
        return true;
    }


}
