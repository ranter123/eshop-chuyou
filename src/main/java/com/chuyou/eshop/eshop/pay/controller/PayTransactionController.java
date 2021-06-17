package com.chuyou.eshop.eshop.pay.controller;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionQuery;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionVO;
import com.chuyou.eshop.eshop.pay.service.PayTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 支付交易流水管理controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/16 19:43
 */
@RestController
@RequestMapping("/pay/transaction")
public class PayTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(PayTransactionController.class);

    /**
     * 支付交易流水管理service组件
     */
    @Autowired
    private PayTransactionService payTransactionService;

    /**
     * 分页查询支付交易流水
     * @param query 查询条件
     * @return 支付交易流水
     */
    @GetMapping("/")
    public List<PayTransactionVO> listByPage(PayTransactionQuery query) {
        try {
            return ObjectUtils.convertList(
                    payTransactionService.listByPage(query),
                    PayTransactionVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

}
