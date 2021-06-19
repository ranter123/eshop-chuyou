package com.chuyou.eshop.eshop.inventory.stock;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.inventory.constant.StockStatus;
import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description: 商品库存更新命令的抽象基类
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 15:50
 */
public abstract class AbstractStockUpdater implements StockUpdater{

    private static final Logger logger = LoggerFactory.getLogger(AbstractStockUpdater.class);

    /**
     * 商品库存DO对象
     */
    protected List<GoodsStockDO> goodsStockDOs;

    /**
     * 商品库存管理模块的DAO组件
     */
    protected GoodsStockDAO goodsStockDAO;

    public AbstractStockUpdater(List<GoodsStockDO> goodsStockDOs, GoodsStockDAO goodsStockDAO) {
        this.goodsStockDOs = goodsStockDOs;
        this.goodsStockDAO = goodsStockDAO;
    }

    /**
     * 更新商品库存
     * @return 处理结果
     */
    @Override
    public Boolean updateGoodsStock() {
        try {
            updateSaleStockQuantity();
            updateLockedStockQuantity();
            updateSaledStockQuantity();
            updateStockStatus();
            updateGmtModified();
            executeUpdateGoodsStock();
        } catch (Exception e) {
            logger.error("error", e);
        }
        return true;
    }

    /**
     * 实际执行更新商品库存的操作
     */
    private void executeUpdateGoodsStock() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            goodsStockDAO.updateGoodsStock(goodsStockDO);
        }
    }

    /**
     * 更新商品的库存的修改时间
     * @throws Exception
     */
    private void updateGmtModified() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            goodsStockDO.setGmtModified(DateUtils.getCurrentTime());
        }
    }

    /**
     * 更新商品销售库存
     * @throws Exception
     */
    protected abstract void updateSaleStockQuantity() throws Exception;

    /**
     * 更新商品的锁定库存
     * @throws Exception
     */
    protected abstract void updateLockedStockQuantity() throws Exception;

    /**
     * 更新商品的已销售库存
     * @throws Exception
     */
    protected abstract void updateSaledStockQuantity() throws Exception;

    /**
     * 更新商品的库存状态
     * @throws Exception
     */
    private void updateStockStatus() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            if (goodsStockDO.getSaleStockQuantity() > 0L) {
                goodsStockDO.setStockStatus(StockStatus.IN_STOCK);
            } else {
                goodsStockDO.setStockStatus(StockStatus.NOT_IN_STOCK);
            }
        }
    }


}
