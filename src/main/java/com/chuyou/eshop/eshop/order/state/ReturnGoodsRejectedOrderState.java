package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:13 下午
 * @Description: 退货审核不通过状态
 */
@Component
public class ReturnGoodsRejectedOrderState extends AbstractOrderState {

    @Autowired
    public ReturnGoodsRejectedOrderState(OrderInfoDAO orderInfoDAO) {
        super(orderInfoDAO);
    }

    @Override
    protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
        return OrderStatus.RETURN_GOODS_REJECTED;
    }

}
