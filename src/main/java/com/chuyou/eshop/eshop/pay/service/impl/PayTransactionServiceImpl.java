package com.chuyou.eshop.eshop.pay.service.impl;

import com.chuyou.eshop.eshop.common.util.CollectionSize;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.pay.dao.PayTransactionDAO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionDO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionDTO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionQuery;
import com.chuyou.eshop.eshop.pay.service.PayTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支付交易流水管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/16 19:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayTransactionServiceImpl implements PayTransactionService {

    /**
     * 支付交易流水管理DAO组件
     */
    @Autowired
    private PayTransactionDAO payTransactionDAO;

    /**
     * 新增支付交易流水
     * @param payTransaction 支付交易流水
     */
    @Override
    public void save(PayTransactionDTO payTransaction) throws Exception {
        payTransactionDAO.save(payTransaction.clone(PayTransactionDO.class));
    }

    /**
     * 更新支付交易流水
     * @param payTransaction 支付交易流水
     * @throws Exception
     */
    @Override
    public void update(PayTransactionDTO payTransaction) throws Exception {
        payTransactionDAO.update(payTransaction.clone(PayTransactionDO.class));
    }

    /**
     * 根据订单编号查询支付交易流水
     * @param orderNo 订单编号
     * @return 支付交易流水
     * @throws Exception
     */
    @Override
    public PayTransactionDTO getByOrderNo(String orderNo) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>(CollectionSize.DEFAULT);
        parameters.put("orderNo", orderNo);

        List<PayTransactionDO> payTransactions = payTransactionDAO.listByCondition(parameters);
        if(!CollectionUtils.isEmpty(payTransactions)) {
            return payTransactions.get(0).clone(PayTransactionDTO.class);
        }

        return null;
    }

    /**
     * 分页查询支付交易流水
     * @param query 查询条件
     * @return 支付交易流水
     */
    @Override
    public List<PayTransactionDTO> listByPage(PayTransactionQuery query) throws Exception {
        return ObjectUtils.convertList(
                payTransactionDAO.listByPage(query),
                PayTransactionDTO.class);
    }
}
