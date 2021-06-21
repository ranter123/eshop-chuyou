package com.chuyou.eshop.eshop.inventory.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description: 离线数据恢复线程
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 15:38
 */
public class OfflineResumeThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(OfflineResumeThread.class);

    /**
     * 离线存储管理组件
     */
    private OfflineStorageManager offlineStorageManager;

    /**
     * 库存更新队列
     */
    private StockUpdateQueue stockUpdateQueue;

    public OfflineResumeThread(OfflineStorageManager offlineStorageManager, StockUpdateQueue stockUpdateQueue) {
        this.offlineStorageManager = offlineStorageManager;
        this.stockUpdateQueue = stockUpdateQueue;
    }

    @Override
    public void run() {
        try {
            // 如果表中还有数据
            OfflineStorageIterator offlineStorageIterator = offlineStorageManager.iterator();
            while (offlineStorageIterator.hasNext()) {
                try {
                    List<StockUpdateMessage> stockUpdateMessages = offlineStorageIterator.next();
                    for (StockUpdateMessage message : stockUpdateMessages) {
                        stockUpdateQueue.putDirect(message);
                    }
                    offlineStorageManager.removeByBatch(stockUpdateMessages);
                } catch (Exception e) {
                    logger.error("error", e);
                }
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
    }
}
