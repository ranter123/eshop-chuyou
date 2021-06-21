package com.chuyou.eshop.eshop.inventory.async;

import java.util.Observable;

/**
 * @Description: 商品库存更新结果观察目标
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 14:45
 */
public class StockUpdateObservable extends Observable {

    /**
     * 消息id
     */
    private String messageId;

    public StockUpdateObservable(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 设置商品库存更新结果
     * @param result 商品库存更新结果
     */
    public void setResult(Boolean result) {
        StockUpdateResult goodsStockUpdateResult = new StockUpdateResult();
        goodsStockUpdateResult.setMessageId(messageId);
        goodsStockUpdateResult.setResult(result);

        this.setChanged();
        this.notifyObservers();
    }

    public String getMessageId() {
        return messageId;
    }
}
