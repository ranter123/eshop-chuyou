package com.chuyou.eshop.eshop.inventory.stock;

/**
 * @Description: 库存更新命令工厂接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 15:49
 */
public interface StockUpdaterFactory<T> {

    /**
     * 创建库存更新命令
     * @param parameter 参数
     * @return 库存更新命令
     */
    StockUpdater create(T parameter);
}
