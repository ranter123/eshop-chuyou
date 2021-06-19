package com.chuyou.eshop.eshop.inventory.async;

/**
 * @Description: 商品库存更新消息的队列接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 17:58
 */
public interface StockUpdateQueue {

    /**
     * 将一个消息放入队列
     * @param message 消息
     * @throws Exception
     */
    void put(StockUpdateMessage message) throws Exception;

    /**
     * 直接将消息放入队列
     * @param message
     * @throws Exception
     */
    void putDirect(StockUpdateMessage message) throws Exception;

    /**
     * 从队列中取出一个消息
     * @return 消息
     * @throws Exception
     */
    StockUpdateMessage take() throws Exception;

    /**
     * 获取队列的大小
     * @return 队列大小
     * @throws Exception
     */
    Integer size() throws Exception;
}
