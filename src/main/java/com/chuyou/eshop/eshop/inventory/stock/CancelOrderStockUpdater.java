package com.chuyou.eshop.eshop.inventory.stock;

import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Description: 取消订单库存更新组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 16:44
 */
public class CancelOrderStockUpdater extends AbstractStockUpdater {

    /**
     * 订单条目DTO对象集合
     */
    private Map<Long, OrderItemDTO> orderItemDTOMap;

    @Autowired
    public CancelOrderStockUpdater(List<GoodsStockDO> goodsStockDOs,
                                   GoodsStockDAO goodsStockDAO,
                                   Map<Long, OrderItemDTO> orderItemDTOMap) {
        super(goodsStockDOs, goodsStockDAO);
        this.orderItemDTOMap = orderItemDTOMap;
    }

    /**
     * 更新商品销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaleStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setSaledStockQuantity(goodsStockDO.getSaleStockQuantity()
                    + orderItemDTO.getPurchaseQuantity());
        }
    }

    /**
     * 更新商品的锁定库存
     * @throws Exception
     */
    @Override
    protected void updateLockedStockQuantity() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOs) {
            OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setLockedStockQuantity(goodsStockDO.getLockedStockQuantity() - orderItemDTO.getPurchaseQuantity());
        }
    }

    /**
     * 更新商品的已销售库存
     * @throws Exception
     */
    @Override
    protected void updateSaledStockQuantity() throws Exception {

    }
}
