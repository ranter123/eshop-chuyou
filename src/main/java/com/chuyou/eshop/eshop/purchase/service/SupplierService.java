package com.chuyou.eshop.eshop.purchase.service;

import com.chuyou.eshop.eshop.purchase.domain.SupplierDTO;
import com.chuyou.eshop.eshop.purchase.domain.SupplierQuery;

import java.util.List;

/**
 * @Description: 供应商管理service接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 20:15
 */
public interface SupplierService {

    /**
     * 新增供应商
     * @param supplier 供应商
     * @throws Exception
     */
    void save(SupplierDTO supplier) throws Exception;

    /**
     * 分页查询供应商
     * @param query 供应商查询条件
     * @return 供应商
     * @throws Exception
     */
    List<SupplierDTO> listByPage(SupplierQuery query) throws Exception;

    /**
     * 根据id查询供应商
     * @param id 供应商id
     * @return 供应商
     * @throws Exception
     */
    SupplierDTO getById(Long id) throws Exception;

    /**
     * 根据结算周期查询供应商
     * @param settlementPeriod 结算周期
     * @return 供应商
     * @throws Exception
     */
    List<SupplierDTO> listBySettlementPeriod(Integer settlementPeriod) throws Exception;


}
