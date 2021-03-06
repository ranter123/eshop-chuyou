package com.chuyou.eshop.eshop.order.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.order.constant.OrderStatus;
import com.chuyou.eshop.eshop.order.dao.OrderInfoDAO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoQuery;
import com.chuyou.eshop.eshop.order.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:27 下午
 * @Description: 订单管理DAO组件
 */
@Repository
public class OrderInfoDAOImpl implements OrderInfoDAO {

    /**
     * 订单管理mapper组件
     */
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 新增订单
     * @param order
     */
    @Override
    public Long save(OrderInfoDO order) throws Exception {
        orderInfoMapper.save(order);
        return order.getId();
    }

    /**
     * 分页查询订单
     * @param query 查询条件
     * @return 订单
     */
    @Override
    public List<OrderInfoDO> listByPage(OrderInfoQuery query) throws Exception {
        return orderInfoMapper.listByPage(query);
    }

    /**
     * 根据id查询订单
     * @param query 查询条件
     * @return 订单
     */
    @Override
    public OrderInfoDO getById(Long id) throws Exception {
        return orderInfoMapper.getById(id);
    }

    /**
     * 查询所有未付款的订单
     * @return 所有未付款的订单
     */
    @Override
    public List<OrderInfoDO> listAllUnpayed() throws Exception {
        return orderInfoMapper.listByStatus(OrderStatus.WAIT_FOR_PAY);
    }

    /**
     * 更新订单
     * @param order 订单
     * @throws Exception
     */
    @Override
    public void update(OrderInfoDO order) throws Exception {
        order.setGmtModified(DateUtils.getCurrentTime());
        orderInfoMapper.update(order);
    }

    /**
     * 更新订单状态
     * @param order 订单
     */
    @Override
    public void updateStatus(Long id, Integer status) throws Exception {
        OrderInfoDO order = getById(id);
        order.setOrderStatus(status);
        update(order);
    }

    /**
     * 查询待收货的订单
     * @return 订单
     * @throws Exception
     */
    @Override
    public List<OrderInfoDO> listUnreceived() throws Exception {
        return orderInfoMapper.listByStatus(OrderStatus.WAIT_FOR_RECEIVE);
    }

    /**
     * 查询确认收货时间超过了7天而且还没有发表评论的订单
     * @return 订单
     */
    @Override
    public List<OrderInfoDO> listNotPublishedCommentOrders() throws Exception {
        return orderInfoMapper.listNotPublishedCommentOrders();
    }

}
