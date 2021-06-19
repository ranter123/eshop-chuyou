package com.chuyou.eshop.eshop.inventory.stock;

/**
 * @Description: 商品库存更新命令的接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 15:48
 */
public interface StockUpdater {

    /**
     * 更新商品库存
     * @return 处理结果
     */
    Boolean updateGoodsStock();
}
