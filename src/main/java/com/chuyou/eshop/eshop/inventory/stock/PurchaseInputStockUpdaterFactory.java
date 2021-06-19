package com.chuyou.eshop.eshop.inventory.stock;

import com.chuyou.eshop.eshop.common.util.CollectionSize;
import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 采购入库库存更新命令的工程
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 16:23
 */
@Component
public class PurchaseInputStockUpdaterFactory<T> extends AbstractStockUpdaterFactory<T> {


    @Autowired
    public PurchaseInputStockUpdaterFactory(GoodsStockDAO goodsStockDAO) {
        super(goodsStockDAO);
    }

    /**
     * 获取商品sku id集合
     * @param parameter 参数
     * @return 商品sku id集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) parameter;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOs =
                purchaseInputOrderDTO.getItems();
        if (purchaseInputOrderItemDTOs == null || purchaseInputOrderItemDTOs.size() == 0) {
            return new ArrayList<>();
        }

        List<Long> goodsSkuIds = new ArrayList<>(purchaseInputOrderItemDTOs.size());

        for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOs) {
            goodsSkuIds.add(purchaseInputOrderDTO.getGoodsSkuId());
        }
        return goodsSkuIds;
    }

    /**
     * 创建库存更新命令
     * @param goodsStockDOs 商品库存DO对象集合
     * @param parameter 参数
     * @return 库存更新命令
     * @throws Exception
     */
    @Override
    protected StockUpdater create(List<GoodsStockDO> goodsStockDOs, T parameter) throws Exception {
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) parameter;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOs =
                purchaseInputOrderDTO.getItems();
        Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap =
                new HashMap<>(CollectionSize.DEFAULT);

        if (purchaseInputOrderItemDTOs != null && purchaseInputOrderItemDTOs.size() > 0) {
            for (PurchaseInputOrderItemDTO purchaseInputOrderItemDTO : purchaseInputOrderItemDTOs) {
                purchaseInputOrderItemDTOMap.put(purchaseInputOrderDTO.getGoodsSkuId(), purchaseInputOrderItemDTO);
            }
        }

        return new PurchaseInputStockUpdater(goodsStockDOs, goodsStockDAO, purchaseInputOrderItemDTOMap);
    }
}
