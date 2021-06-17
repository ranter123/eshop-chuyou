package com.chuyou.eshop.eshop.finance.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.finance.dao.PurchaseSettlementOrderItemDAO;
import com.chuyou.eshop.eshop.finance.domain.PurchaseSettlementOrderItemDO;
import com.chuyou.eshop.eshop.finance.mapper.PurchaseSettlementOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 采购结算单条目管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/17 10:41
 */
@Repository
public class PurchaseSettlementOrderItemDAOImpl implements PurchaseSettlementOrderItemDAO {

    /**
     * 采购结算单条目管理mapper组件
     */
    @Autowired
    private PurchaseSettlementOrderItemMapper purchaseSettlementOrderItemMapper;

    /**
     * 批量新增采购结算单条目
     * @param purchaseSettlementOrderId 采购结算单id
     * @param purchaseSettlementOrderItems 采购结算单条目
     * @throws Exception
     */
    @Override
    public void batchSave(Long purchaseSettlementOrderId,
                          List<PurchaseSettlementOrderItemDO> purchaseSettlementOrderItems) throws Exception {
        for(PurchaseSettlementOrderItemDO purchaseSettlementOrderItem : purchaseSettlementOrderItems) {
            purchaseSettlementOrderItem.setGmtCreate(DateUtils.getCurrentTime());
            purchaseSettlementOrderItem.setGmtModified(DateUtils.getCurrentTime());
            purchaseSettlementOrderItem.setPurchaseSettlementOrderId(purchaseSettlementOrderId);
            purchaseSettlementOrderItemMapper.save(purchaseSettlementOrderItem);
        }
    }

    /**
     * 根据采购结算单id查询采购结算单条目
     * @param purchaseSettlementOrderId 采购结算单id
     * @return 采购结算单条目
     */
    @Override
    public List<PurchaseSettlementOrderItemDO> listByPurchaseSettlementOrderId(
            Long purchaseSettlementOrderId) throws Exception {
        return purchaseSettlementOrderItemMapper.listByPurchaseSettlementOrderId(
                purchaseSettlementOrderId);
    }

}
