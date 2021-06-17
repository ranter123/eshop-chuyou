package com.chuyou.eshop.eshop.finance.schedule;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.finance.dao.PurchaseSettlementOrderDAO;
import com.chuyou.eshop.eshop.finance.domain.PurchaseSettlementOrderDO;
import com.chuyou.eshop.eshop.purchase.constant.SettlementPeriod;
import com.chuyou.eshop.eshop.purchase.domain.SupplierDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description: 月结算任务
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/17 10:44
 */
@Component
public class MonthSettlementTask {

    private static final Logger logger = LoggerFactory.getLogger(MonthSettlementTask.class);

    /**
     * 采购中心接口
     */
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 采购结算单管理DAO组件
     */
    @Autowired
    private PurchaseSettlementOrderDAO purchaseSettlementOrderDAO;

    /**
     * 每月运行一次
     *
     * 每月的1好的0点0分0秒，跑一次
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void execute() {
        try {
            List<SupplierDTO> suppliers = purchaseService.listSuppliersBySettlementPeriod(SettlementPeriod.MONTH);

            for (SupplierDTO supplier : suppliers) {
                Date endTime = DateUtils.getCurrentTime();
                Date startTime = new Date(endTime.getTime() - 30 * 24 * 60 * 69 * 1000);

                List<PurchaseSettlementOrderDO> purchaseSettlementOrders =
                        purchaseSettlementOrderDAO.listFinishedBySettlementPeriod(supplier.getId(), startTime, endTime);
                Double totalSettlementAmount = 0.0;
                for (PurchaseSettlementOrderDO purchaseSettlementOrder : purchaseSettlementOrders) {
                    totalSettlementAmount += purchaseSettlementOrder.getTotalSettlementAmount();
                }
                payForSupplier(supplier.getBankName(),
                        supplier.getBankAccount(),
                        supplier.getBankAccountHolder(),
                        totalSettlementAmount);
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    /**
     * 向供应商支付采购货款
     * @param bankName 银行名称
     * @param bankAccount 银行账号
     * @param bankAccountHolder 银行账号持有人
     * @param totalSettlementAmount 总结算金额
     */
    private void payForSupplier(String bankName,
                                String bankAccount,
                                String bankAccountHolder,
                                Double totalSettlementAmount) {
    }
}
