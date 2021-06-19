package com.chuyou.eshop.eshop.inventory.stock;

import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;

import java.util.List;

/**
 * @Description: 采购入库库存更新命令
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 16:32
 */
public class PurchaseInputStockUpdater extends AbstractStockUpdater {

    /**
     * 采购入库单条目DTO集合
     */
    private Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap;

    public PurchaseInputStockUpdater(List<GoodsStockDO> goodsStockDOs,
                                     GoodsStockDAO goodsStockDAO,
                                     Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap) {
        super(goodsStockDOs, goodsStockDAO);
        this.purchaseInputOrderItemDTOMap = purchaseInputOrderItemDTOMap;
    }

    /**
     * 更新商品销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaleStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            PurchaseInputOrderItemDTO purchaseInputOrderItemDTO =
                    purchaseInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setSaledStockQuantity(goodsStockDO.getSaleStockQuantity()
                    + purchaseInputOrderItemDTO.getArrivalCount());
        }
    }

    /**
     * 更新商品的锁定库存
     * @throws Exception
     */
    @Override
    protected void updateLockedStockQuantity() throws Exception {

    }

    /**
     * 更新商品的已销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaledStockQuantity() throws Exception {

    }
}
