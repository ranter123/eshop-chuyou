package com.chuyou.eshop.eshop.order.dao;

import com.chuyou.eshop.eshop.order.domain.OrderOperateLogDO;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/6/9 9:37 下午
 * @Description: 订单操作日志管理DAO接口
 */
public interface OrderOperateLogDAO {

    /**
     * 新增订单操作日志
     * @param log 订单操作日志
     * @throws Exception
     */
    void save(OrderOperateLogDO log) throws Exception;

    /**
     * 查询订单操作日志
     * @param orderInfoId 订单id
     * @return 订单操作日志
     * @throws Exception
     */
    List<OrderOperateLogDO> listByOrderInfoId(Long orderInfoId) throws Exception;

}
