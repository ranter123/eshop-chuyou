package com.chuyou.eshop.eshop.purchase.controller;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.purchase.domain.SupplierDTO;
import com.chuyou.eshop.eshop.purchase.domain.SupplierQuery;
import com.chuyou.eshop.eshop.purchase.domain.SupplierVO;
import com.chuyou.eshop.eshop.purchase.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 供应商管理controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 20:15
 */
@RestController
@RequestMapping("/purchase/supplier")
public class SupplierController {

    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    /**
     * 供应商管理service组件
     */
    @Autowired
    private SupplierService supplierService;

    /**
     * 新增供应商
     * @param supplier 供应商
     */
    @PostMapping("/")
    public Boolean save(@RequestBody SupplierVO supplier) {
        try {
            supplierService.save(supplier.clone(SupplierDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 分页查询供应商
     * @param query 供应商查询条件
     * @return 供应商
     */
    @GetMapping("/")
    public List<SupplierVO> listByPage(SupplierQuery query) {
        try {
            return ObjectUtils.convertList(
                    supplierService.listByPage(query),
                    SupplierVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 根据id查询供应商
     * @param id 供应商id
     * @return 供应商
     */
    @GetMapping("/{id}")
    public SupplierVO getById(@PathVariable("id") Long id) {
        try {
            return supplierService.getById(id).clone(SupplierVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }



}
