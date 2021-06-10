package com.chuyou.eshop.eshop.order.dao;

import com.chuyou.eshop.eshop.order.domain.OrderItemDO;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:36 下午
 * @Description: 订单条目管理DAO组件接口
 */
public interface OrderItemDAO {

    /**
     * 新增订单条目
     * @param orderItem 订单条目
     * @return 订单条目id
     * @throws Exception
     */
    Long save(OrderItemDO orderItem) throws Exception;

    /**
     * 查询订单条目
     * @param orderInfoId 订单id
     * @return 订单条目
     * @throws Exception
     */
    List<OrderItemDO> listByOrderInfoId(Long orderInfoId) throws Exception;

}
