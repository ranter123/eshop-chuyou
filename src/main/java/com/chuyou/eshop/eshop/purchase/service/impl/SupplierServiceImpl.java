package com.chuyou.eshop.eshop.purchase.service.impl;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.purchase.dao.SupplierDAO;
import com.chuyou.eshop.eshop.purchase.domain.SupplierDO;
import com.chuyou.eshop.eshop.purchase.domain.SupplierDTO;
import com.chuyou.eshop.eshop.purchase.domain.SupplierQuery;
import com.chuyou.eshop.eshop.purchase.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 供应商管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 20:16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierServiceImpl implements SupplierService {
    /**
     * 供应商管理DAO组件
     */
    @Autowired
    private SupplierDAO supplierDAO;

    /**
     * 新增供应商
     * @param supplier 供应商
     */
    @Override
    public void save(SupplierDTO supplier) throws Exception {
        supplierDAO.save(supplier.clone(SupplierDO.class));
    }

    /**
     * 分页查询供应商
     * @param query 供应商查询条件
     * @return 供应商
     */
    @Override
    public List<SupplierDTO> listByPage(SupplierQuery query) throws Exception {
        return ObjectUtils.convertList(supplierDAO.listByPage(query), SupplierDTO.class);
    }

    /**
     * 根据id查询供应商
     * @param id 供应商id
     * @return 供应商
     */
    @Override
    public SupplierDTO getById(Long id) throws Exception {
        return supplierDAO.getById(id).clone(SupplierDTO.class);
    }

    /**
     * 根据结算周期查询供应商
     * @param settlementPeriod 结算周期
     * @return 供应商
     */
    @Override
    public List<SupplierDTO> listBySettlementPeriod(Integer settlementPeriod) throws Exception {
        return ObjectUtils.convertList(
                supplierDAO.listBySettlementPeriod(settlementPeriod),
                SupplierDTO.class);
    }


}
