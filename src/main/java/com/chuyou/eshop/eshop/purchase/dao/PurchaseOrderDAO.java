package com.chuyou.eshop.eshop.purchase.dao;

import com.chuyou.eshop.eshop.purchase.domain.PurchaseOrderDO;
import com.chuyou.eshop.eshop.purchase.domain.PurchaseOrderQuery;

import java.util.List;

/**
 * @Description: 采购单管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 20:07
 */
public interface PurchaseOrderDAO {

    /**
     * 新增采购单
     * @param purchaseOrder 采购单
     * @return 采购单id
     * @throws Exception
     */
    Long save(PurchaseOrderDO purchaseOrder) throws Exception;

    /**
     * 分页查询采购单
     * @param query 查询条件
     * @return 采购单
     * @throws Exception
     */
    List<PurchaseOrderDO> listByPage(PurchaseOrderQuery query) throws Exception;

    /**
     * 根据id查询采购单
     * @param id 采购单id
     * @return 采购单
     * @throws Exception
     */
    PurchaseOrderDO getById(Long id) throws Exception;

    /**
     * 更新采购单
     * @param purchaseOrder 采购单
     * @throws Exception
     */
    void update(PurchaseOrderDO purchaseOrder) throws Exception;

    /**
     * 更新采购单的状态
     * @param id 采购单id
     * @param status 采购单状态
     * @throws Exception
     */
    void updateStatus(Long id, Integer status) throws Exception;

}
