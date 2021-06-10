package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:05 下午
 * @Description: 订单完成状态
 */
@Component
public class FinishedOrderState extends AbstractOrderState {

    @Autowired
    public FinishedOrderState(OrderInfoDAO orderInfoDAO) {
        super(orderInfoDAO);
    }

    @Override
    protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
        return OrderStatus.FINISHED;
    }

    @Override
    public Boolean canApplyReturnGoods(OrderInfoDTO order) throws Exception {
        return true;
    }
}
