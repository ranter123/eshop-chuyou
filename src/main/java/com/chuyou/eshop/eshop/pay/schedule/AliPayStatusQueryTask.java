package com.chuyou.eshop.eshop.pay.schedule;

import com.chuyou.eshop.eshop.common.util.CollectionSize;
import com.chuyou.eshop.eshop.pay.api.PayApi;
import com.chuyou.eshop.eshop.pay.api.QueryPayStatusResponse;
import com.chuyou.eshop.eshop.pay.constant.PayTransactionStatus;
import com.chuyou.eshop.eshop.pay.constant.PayType;
import com.chuyou.eshop.eshop.pay.dao.PayTransactionDAO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionDO;
import com.chuyou.eshop.eshop.pay.domain.PayTransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支付宝支付状态查询的后台任务
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/17 9:53
 */
@Component
public class AliPayStatusQueryTask {

    private static final Logger logger = LoggerFactory.getLogger(AliPayStatusQueryTask.class);

    /**
     * 支付交易流水管理DAO组件
     */
    @Autowired
    private PayTransactionDAO payTransactionDAO;

    /**
     * 支付接口
     */
    @Autowired
    private PayApi payApi;

    /**
     * 执行任务
     */
    @Autowired
    private OrderService orderService;

    @Scheduled(fixedRate = 10 * 1000)
    public void execute() {
        try {
            List<PayTransactionDO> payTransactions = listUnPayedAlipayTransactions();
            for (PayTransactionDO payTransaction : payTransactions) {
                // 此处会调用支付宝代理接口，去查询支付交易的状态
                QueryPayStatusResponse response = payApi.queryPayStatus(
                        payTransaction.getTransactionChannel(), payTransaction.getOrderNo());
                if (!PayTransactionStatus.UNPAYED.equals(response.getPayTransactionStatus())) {
                    payTransaction.setUserPayAccount(response.getUserPayAccount());
                    payTransaction.setTransactionNumber(response.getTransactionNumber());
                    payTransaction.setFinishPayTime(response.getFinishPayTime());
                    payTransaction.setResponseCode(response.getResponseCode());
                    payTransaction.setStatus(response.getPayTransactionStatus());
                    payTransactionDAO.update(payTransaction);

                    if (PayTransactionStatus.SUCCESS.equals(response.getPayTransactionStatus())) {
                        orderService.informPayOrderSuccessed(payTransaction.getOrderInfoId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    /**
     * 查询未支付的支付宝交易流水
     * @return 交易流水
     */
    private List<PayTransactionDO> listUnPayedAlipayTransactions() throws Exception {
        Map<String, Object> parameters = new HashMap<>(CollectionSize.DEFAULT);
        parameters.put("transactionChannel", PayType.ALIPAY);
        parameters.put("status", PayTransactionStatus.UNPAYED);
        return payTransactionDAO.listByCondition(parameters);
    }
}
