package com.chuyou.eshop.eshop.inventory.async;

import java.util.List;

/**
 * @Description: 定义离线存储数据的迭代器接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 15:23
 */
public interface OfflineStorageIterator {

    Boolean hasNext() throws Exception;

    /**
     * 获取下一批库存更新消息
     * @return 下一批库存更新消息
     * @throws Exception
     */
    List<StockUpdateMessage> next() throws Exception;
}
