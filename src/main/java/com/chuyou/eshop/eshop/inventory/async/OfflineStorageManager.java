package com.chuyou.eshop.eshop.inventory.async;

import java.util.List;

/**
 * @Description: 离线存储管理组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 14:55
 */
public interface OfflineStorageManager {

    /**
     * 离线存储更新库存消息
     * @param message 库存更新消息
     * @throws Exception
     */
    void store(StockUpdateMessage message) throws Exception;

    /**
     * 获取离线存储标识
     * @return 离线存储标识
     * @throws Exception
     */
    Boolean getOffline() throws Exception;

    /**
     * 设置离线存储标识
     * @param offline 离线存储标识
     * @throws Exception
     */
    void setOffline(Boolean offline) throws Exception;

    /**
     * 获取迭代器
     * @return
     * @throws Exception
     */
    OfflineStorageIterator iterator() throws Exception;

    /**
     * 批量删除库存更新消息
     * @param stockUpdateMessages 库存更新消息
     * @throws Exception
     */
    void removeByBatch(List<StockUpdateMessage> stockUpdateMessages) throws Exception;
}
