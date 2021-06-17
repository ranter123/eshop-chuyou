package com.chuyou.eshop.eshop.pay.dao;

import com.chuyou.eshop.eshop.pay.domain.PayTransactionDO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionQuery;

import java.util.List;
import java.util.Map;

/**
 * @Description: 支付交易流水管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/17 9:42
 */
public interface PayTransactionDAO {

    /**
     * 新增支付交易流水
     * @param payTransaction 支付交易流水
     * @throws Exception
     */
    void save(PayTransactionDO payTransaction) throws Exception;

    /**
     * 根据条件查询支付交易流水
     * @param parameters 查询条件
     * @return 支付交易流水
     * @throws Exception
     */
    List<PayTransactionDO> listByCondition(Map<String, Object> parameters) throws Exception;

    /**
     * 更新支付交易流水
     * @param payTransaction 支付交易流水
     * @throws Exception
     */
    void update(PayTransactionDO payTransaction) throws Exception;

    /**
     * 分页查询支付交易流水
     * @param query 查询条件
     * @return 支付交易流水
     * @throws Exception
     */
    List<PayTransactionDO> listByPage(PayTransactionQuery query) throws Exception;


}
