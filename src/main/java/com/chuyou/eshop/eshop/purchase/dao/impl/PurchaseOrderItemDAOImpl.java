package com.chuyou.eshop.eshop.purchase.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.purchase.dao.PurchaseOrderItemDAO;
import com.chuyou.eshop.eshop.purchase.domain.PurchaseOrderItemDO;
import com.chuyou.eshop.eshop.purchase.mapper.PurchaseOrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 采购单条目管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 20:09
 */
@Repository
public class PurchaseOrderItemDAOImpl implements PurchaseOrderItemDAO {

    /**
     * 采购单条目管理mapper组件
     */
    @Autowired
    private PurchaseOrderItemMapper purchaseOrderItemMapper;

    /**
     * 批量新增采购单条目
     * @param purchaseOrderId 采购单id
     * @param purchaseOrderItems 采购单条目
     * @throws Exception
     */
    @Override
    public void batchSave(Long purchaseOrderId, List<PurchaseOrderItemDO> purchaseOrderItems) throws Exception {
        for(PurchaseOrderItemDO purchaseOrderItem : purchaseOrderItems) {
            purchaseOrderItem.setPurchaseOrderId(purchaseOrderId);
            purchaseOrderItem.setGmtCreate(DateUtils.getCurrentTime());
            purchaseOrderItem.setGmtModified(DateUtils.getCurrentTime());
            purchaseOrderItemMapper.save(purchaseOrderItem);
        }
    }

    /**
     * 根据采购单id查询采购单条目
     * @param purchaseOrderId 采购单id
     * @return 采购单条目
     */
    @Override
    public List<PurchaseOrderItemDO> listByPurchaseOrderId(Long purchaseOrderId) throws Exception {
        return purchaseOrderItemMapper.listByPurchaseOrderId(purchaseOrderId);
    }

    /**
     * 根据采购单id删除采购单条目
     * @param purchaseOrderId 采购单id
     */
    @Override
    public void removeByPurchaseOrderId(Long purchaseOrderId) throws Exception {
        purchaseOrderItemMapper.removeByPurchaseOrderId(purchaseOrderId);
    }


}
