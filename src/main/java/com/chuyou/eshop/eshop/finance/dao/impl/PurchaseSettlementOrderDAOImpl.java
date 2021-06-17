package com.chuyou.eshop.eshop.finance.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.finance.dao.PurchaseSettlementOrderDAO;
import com.chuyou.eshop.eshop.finance.domain.PurchaseSettlementOrderDO;
import com.chuyou.eshop.eshop.finance.domain.PurchaseSettlementOrderQuery;
import com.chuyou.eshop.eshop.finance.mapper.PurchaseSettlementOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 采购结算单管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/17 10:39
 */
@Repository
public class PurchaseSettlementOrderDAOImpl implements PurchaseSettlementOrderDAO {

    /**
     * 采购结算单管理mapper组件
     */
    @Autowired
    private PurchaseSettlementOrderMapper purchaseSettlementOrderMapper;

    /**
     * 新增采购结算单
     * @param purchaseSettlementOrder 采购结算单
     */
    @Override
    public Long save(PurchaseSettlementOrderDO purchaseSettlementOrder) throws Exception {
        purchaseSettlementOrder.setGmtCreate(DateUtils.getCurrentTime());
        purchaseSettlementOrder.setGmtModified(DateUtils.getCurrentTime());
        purchaseSettlementOrderMapper.save(purchaseSettlementOrder);
        return purchaseSettlementOrder.getId();
    }

    /**
     * 分页查询采购结算单
     * @param query 查询条件
     * @return 采购结算单
     */
    @Override
    public List<PurchaseSettlementOrderDO> listByPage(
            PurchaseSettlementOrderQuery query) throws Exception {
        return purchaseSettlementOrderMapper.listByPage(query);
    }

    /**
     * 根据id查询采购结算单
     * @param id 采购结算单id
     * @return 采购结算单
     */
    @Override
    public PurchaseSettlementOrderDO getById(Long id) throws Exception {
        return purchaseSettlementOrderMapper.getById(id);
    }

    /**
     * 更新采购结算单
     * @param purchaseSettlementOrder 采购结算单
     */
    @Override
    public void update(PurchaseSettlementOrderDO purchaseSettlementOrder) throws Exception {
        purchaseSettlementOrder.setGmtModified(DateUtils.getCurrentTime());
        purchaseSettlementOrderMapper.update(purchaseSettlementOrder);
    }

    /**
     * 更新采购结算单状态
     * @param purchaseSettlementOrder 采购结算单
     */
    @Override
    public void updateStatus(PurchaseSettlementOrderDO purchaseSettlementOrder) throws Exception {
        purchaseSettlementOrder.setGmtModified(DateUtils.getCurrentTime());
        purchaseSettlementOrderMapper.updateStatus(purchaseSettlementOrder);
    }

    /**
     * 更新采购结算单状态
     * @param id 采购结算单id
     * @param status 采购结算单状态
     * @throws Exception
     */
    @Override
    public void updateStatus(Long id, Integer status) throws Exception {
        PurchaseSettlementOrderDO purchaseSettlementOrder = getById(id);
        purchaseSettlementOrder.setStatus(status);
        updateStatus(purchaseSettlementOrder);
    }

    /**
     * 查询指定供应商在指定时间范围内的已完成的采购结算单
     * @param supplierId 供应商id
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @return 采购结算单
     */
    @Override
    public List<PurchaseSettlementOrderDO> listFinishedBySettlementPeriod(
            Long supplierId, Date startTime, Date endTime) throws Exception {
        return purchaseSettlementOrderMapper.listFinishedBySettlementPeriod(
                supplierId, startTime, endTime);
    }


}
