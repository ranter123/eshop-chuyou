package com.chuyou.eshop.eshop.inventory.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 商品库存更新结果管理组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 14:51
 */
@Component
public class StockUpdateResultManagerImpl implements StockUpdateResultManager  {

    /**
     * 商品库存更新结果map
     */
    private Map<String, StockUpdateObservable> observableMap =
            new ConcurrentHashMap<String, StockUpdateObservable>();

    /**
     * 商品库存更新结果观察者
     */
    @Autowired
    private StockUpdateObserver observer;

    /**
     * 设置对商品库存更新结果的观察
     * @param messageId 消息id
     */
    @Override
    public void observe(String messageId) {
        StockUpdateObservable observable = new StockUpdateObservable(messageId);
        observable.addObserver(observer);
        observableMap.put(messageId, observable);
    }

    /**
     * 获取商品库存更新结果的观察目标
     * @param messageId 商品库存更新消息id
     * @return 商品库存更新结果的观察目标
     */
    @Override
    public void inform(String messageId, Boolean result) {
        StockUpdateObservable observable = observableMap.get(messageId);
        observable.setResult(result);
        observableMap.remove(messageId);
    }

    /**
     * 获取库存更新结果观察目标
     * @param messageId 消息id
     * @return
     */
    @Override
    public StockUpdateObservable getObservable(String messageId) {
        return observableMap.get(messageId);
    }
}
