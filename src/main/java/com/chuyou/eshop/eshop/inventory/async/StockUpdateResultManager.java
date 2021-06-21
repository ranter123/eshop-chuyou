package com.chuyou.eshop.eshop.inventory.async;

/**
 * @Description: 商品库存更新结果管理组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 14:51
 */
public interface StockUpdateResultManager {

    /**
     * 设置对商品库存更新结果的观察
     * @param messageId 消息id
     */
    void observe(String messageId);

    /**
     * 获取商品库存更新结果的观察目标
     * @param messageId 商品库存更新消息id
     * @param result 结果
     * @return 商品库存更新结果的观察目标
     */
    void inform(String messageId, Boolean result);

    /**
     * 获取库存更新结果观察目标
     * @param messageId 消息id
     * @return
     */
    StockUpdateObservable getObservable(String messageId);


}
