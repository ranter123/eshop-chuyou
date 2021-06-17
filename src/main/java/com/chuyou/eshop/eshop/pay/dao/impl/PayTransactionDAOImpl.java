package com.chuyou.eshop.eshop.pay.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.pay.dao.PayTransactionDAO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionDO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionQuery;
import com.chuyou.eshop.eshop.pay.mapper.PayTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 支付交易流水管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/17 9:43
 */
@Repository
public class PayTransactionDAOImpl implements PayTransactionDAO {

    /**
     * 支付交易流水管理mapper组件
     */
    @Autowired
    private PayTransactionMapper payTransactionMapper;

    /**
     * 新增支付交易流水
     * @param payTransaction 支付交易流水
     */
    @Override
    public void save(PayTransactionDO payTransaction) throws Exception {
        payTransaction.setGmtCreate(DateUtils.getCurrentTime());
        payTransaction.setGmtModified(DateUtils.getCurrentTime());
        payTransactionMapper.save(payTransaction);
    }

    /**
     * 根据条件查询支付交易流水
     * @param parameters 查询条件
     * @return 支付交易流水
     * @throws Exception
     */
    @Override
    public List<PayTransactionDO> listByCondition(Map<String, Object> parameters) throws Exception {
        return payTransactionMapper.listByCondition(parameters);
    }

    /**
     * 更新支付交易流水
     * @param payTransaction 支付交易流水
     */
    @Override
    public void update(PayTransactionDO payTransaction) throws Exception {
        payTransaction.setGmtModified(DateUtils.getCurrentTime());
        payTransactionMapper.update(payTransaction);
    }

    /**
     * 分页查询支付交易流水
     * @param query 查询条件
     * @return 支付交易流水
     */
    @Override
    public List<PayTransactionDO> listByPage(PayTransactionQuery query) throws Exception {
        return payTransactionMapper.listByPage(query);
    }


}
