package com.chuyou.eshop.eshop.order.dao.impl;

import com.chuyou.eshop.eshop.order.dao.OrderItemDAO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDO;
import com.chuyou.eshop.eshop.order.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:36 下午
 * @Description: 订单条目管理DAO组件
 */
@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

    /**
     * 订单条目管理mapper组件
     */
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 新增订单条目
     * @param orderItem
     */
    @Override
    public Long save(OrderItemDO orderItem) throws Exception {
        orderItemMapper.save(orderItem);
        return orderItem.getId();
    }

    /**
     * 查询订单条目
     * @param orderInfoId 订单id
     * @return 订单条目
     */
    @Override
    public List<OrderItemDO> listByOrderInfoId(Long orderInfoId) throws Exception {
        return orderItemMapper.listByOrderInfoId(orderInfoId);
    }

}
