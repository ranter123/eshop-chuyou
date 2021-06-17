package com.chuyou.eshop.eshop.purchase.dao;

import com.chuyou.eshop.eshop.purchase.domain.PurchaseOrderItemDO;

import java.util.List;

/**
 * @Description: 采购单条目管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 20:09
 */
public interface PurchaseOrderItemDAO {

    /**
     * 批量新增采购单条目
     * @param purchaseOrderId 采购单id
     * @param purchaseOrderItems 采购单条目
     * @throws Exception
     */
    void batchSave(Long purchaseOrderId,
                   List<PurchaseOrderItemDO> purchaseOrderItems) throws Exception;

    /**
     * 根据采购单id查询采购单条目
     * @param purchaseOrderId 采购单id
     * @return 采购单条目
     * @throws Exception
     */
    List<PurchaseOrderItemDO> listByPurchaseOrderId(Long purchaseOrderId) throws Exception;

    /**
     * 根据采购单id删除采购单条目
     * @param purchaseOrderId 采购单id
     * @throws Exception
     */
    void removeByPurchaseOrderId(Long purchaseOrderId) throws Exception;

}
