package com.chuyou.eshop.eshop.inventory.stock;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.inventory.constant.StockStatus;
import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 库存更新命令工厂的父类
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 16:03
 */
public abstract class AbstractStockUpdaterFactory<T> implements StockUpdaterFactory<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractStockUpdaterFactory.class);

    /**
     * 商品库存管理模块的DAO组件
     */
    protected GoodsStockDAO goodsStockDAO;

    public AbstractStockUpdaterFactory(GoodsStockDAO goodsStockDAO) {
        this.goodsStockDAO = goodsStockDAO;
    }

    /**
     * 创建库存更新命令
     * @param parameter 参数
     * @return 库存更新命令
     */
    @Override
    public StockUpdater create(T parameter) {
        try {
            List<Long> goodsSkuIds = getGoodsSkuIds(parameter);
            List<GoodsStockDO> goodsStockDOs = createGoodsStockDOs(goodsSkuIds);
            return create(goodsStockDOs, parameter);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * 创建商品库存DO对象集合
     * @param goodsSkuIds 商品sku id集合
     * @return 商品库存DO对象集合
     * @throws Exception
     */
    private List<GoodsStockDO> createGoodsStockDOs(List<Long> goodsSkuIds) throws Exception {
        List<GoodsStockDO> goodsStocks = new ArrayList<>(goodsSkuIds.size());

        for (Long goodsSkuId : goodsSkuIds) {
            GoodsStockDO goodsStock = goodsStockDAO.getGoodsStockBySkuId(goodsSkuId);
            if (goodsStock == null) {
                goodsStock = createGoodsStock(goodsSkuId);
                goodsStockDAO.saveGoodsStock(goodsStock);
            }
            goodsStocks.add(goodsStock);
        }
        return goodsStocks;
    }

    /**
     * 创建商品库存DO对象
     * @param goodsSkuId 商品sku id
     * @return 商品库存DO对象
     */
    private GoodsStockDO createGoodsStock(Long goodsSkuId) throws Exception {
        GoodsStockDO goodsStockDO = new GoodsStockDO();
        goodsStockDO.setGoodsSkuId(goodsSkuId);
        goodsStockDO.setSaleStockQuantity(0L);
        goodsStockDO.setLockedStockQuantity(0L);
        goodsStockDO.setSaledStockQuantity(0L);
        goodsStockDO.setStockStatus(StockStatus.NOT_IN_STOCK);
        goodsStockDO.setGmtCreate(DateUtils.getCurrentTime());
        goodsStockDO.setGmtModified(DateUtils.getCurrentTime());
        return goodsStockDO;
    }

    /**
     * 获取商品sku id集合
     * @param parameter 参数
     * @return 商品sku id集合
     */
    protected abstract List<Long> getGoodsSkuIds(T parameter) throws Exception;

    /**
     * 创建库存更新命令
     * @param goodsStockDOs 商品库存DO对象集合
     * @param parameter 参数
     * @return 库存更新命令
     * @throws Exception
     */
    protected abstract StockUpdater create(List<GoodsStockDO> goodsStockDOs, T parameter) throws Exception;


}
