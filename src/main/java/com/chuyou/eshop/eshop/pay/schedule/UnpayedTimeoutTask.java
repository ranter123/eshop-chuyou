package com.chuyou.eshop.eshop.pay.schedule;

import com.chuyou.eshop.eshop.common.util.CollectionSize;
import com.chuyou.eshop.eshop.pay.constant.PayTransactionStatus;
import com.chuyou.eshop.eshop.pay.dao.PayTransactionDAO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 未支付订单超时检查任务
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/17 10:06
 */
@Component
public class UnpayedTimeoutTask {

    private static final Logger logger = LoggerFactory.getLogger(UnpayedTimeoutTask.class);

    /**
     * 支付交易流水管理DAO组件
     */
    @Autowired
    private PayTransactionDAO payTransactionDAO;

    @Scheduled(fixedRate = 10 * 1000)
    public void execute() {
        try {
            List<PayTransactionDO> payTransactions = listUnpayedTransactions();

            for (PayTransactionDO payTransaction : payTransactions) {
                if (System.currentTimeMillis() - payTransaction.getGmtCreate().getTime() > 30 * 60 * 1000) {
                    payTransaction.setStatus(PayTransactionStatus.CLOSED);
                    payTransactionDAO.update(payTransaction);
                }
            }

        } catch (Exception e) {

        }
    }

    /**
     * 查询未支付的支付宝交易流水
     * @return 交易流水
     * @throws Exception
     */
    private List<PayTransactionDO> listUnpayedTransactions() throws Exception {
        Map<String, Object> parameters = new HashMap<>(CollectionSize.DEFAULT);
        parameters.put("status", PayTransactionStatus.UNPAYED);
        return payTransactionDAO.listByCondition(parameters);
    }
}
