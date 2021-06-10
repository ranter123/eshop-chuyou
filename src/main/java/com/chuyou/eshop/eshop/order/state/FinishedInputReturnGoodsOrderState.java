package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:04 下午
 * @Description: 退货已入库状态
 */
@Component
public class FinishedInputReturnGoodsOrderState extends AbstractOrderState {

    @Autowired
    public FinishedInputReturnGoodsOrderState(OrderInfoDAO orderInfoDAO) {
        super(orderInfoDAO);
    }

    @Override
    protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
        return OrderStatus.FINISHED_INPUT_RETURN_GOODS;
    }
}
