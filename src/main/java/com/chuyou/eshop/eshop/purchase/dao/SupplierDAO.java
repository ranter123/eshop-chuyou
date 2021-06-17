package com.chuyou.eshop.eshop.purchase.dao;

import com.chuyou.eshop.eshop.purchase.domain.SupplierDO;
import com.chuyou.eshop.eshop.purchase.domain.SupplierQuery;

import java.util.List;

/**
 * @Description: 供应商管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 20:16
 */
public interface SupplierDAO {

    /**
     * 新增供应商
     * @param supplier 供应商
     * @throws Exception
     */
    void save(SupplierDO supplier) throws Exception;

    /**
     * 分页查询供应商
     * @param query 供应商查询条件
     * @return 供应商
     * @throws Exception
     */
    List<SupplierDO> listByPage(SupplierQuery query) throws Exception;

    /**
     * 根据id查询供应商
     * @param id 供应商id
     * @return 供应商
     * @throws Exception
     */
    SupplierDO getById(Long id) throws Exception;

    /**
     * 根据结算周期查询供应商
     * @param settlementPeriod 结算周期
     * @return 供应商
     * @throws Exception
     */
    List<SupplierDO> listBySettlementPeriod(Integer settlementPeriod) throws Exception;


}
