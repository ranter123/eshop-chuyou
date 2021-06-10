package com.chuyou.eshop.eshop.order.state;

import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 完成退款状态
 * @author zhonghuashishan
 *
 */
@Component
public class FinishedRefundOrderState extends AbstractOrderState {

	@Autowired
	public FinishedRefundOrderState(OrderInfoDAO orderInfoDAO) {
		super(orderInfoDAO);
	}

	@Override
	protected Integer getOrderStatus(OrderInfoDTO order) throws Exception {
		return OrderStatus.FINISHED_REFUND;
	}

}
