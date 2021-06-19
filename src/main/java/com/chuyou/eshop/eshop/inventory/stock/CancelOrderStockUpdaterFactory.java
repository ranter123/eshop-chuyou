package com.chuyou.eshop.eshop.inventory.stock;

import com.chuyou.eshop.eshop.common.util.CollectionSize;
import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 取消订单库存更新组件工厂
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 16:39
 */
@Component
public class CancelOrderStockUpdaterFactory<T> extends AbstractStockUpdaterFactory<T> {

    @Autowired
    public CancelOrderStockUpdaterFactory(GoodsStockDAO goodsStockDAO) {
        super(goodsStockDAO);
    }

    /**
     * 获取商品sku id集合
     * @param parameter 参数
     * @return 商品sku id集合
     */
    @Override
    protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
        OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
        List<Long> goodsSkuIds = new ArrayList<>();
        List<OrderItemDTO> orderItemDTOs = orderInfoDTO.getOrderItems();
        for (OrderItemDTO orderItemDTO : orderItemDTOs) {
            goodsSkuIds.add(orderItemDTO.getGoodsSkuId());
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
        OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
        Map<Long, OrderItemDTO> orderItemDTOMap = new HashMap<>(CollectionSize.DEFAULT);
        for (OrderItemDTO orderItemDTO : orderInfoDTO.getOrderItems()) {
            orderItemDTOMap.put(orderItemDTO.getGoodsSkuId(), orderItemDTO);
        }
        return new CancelOrderStockUpdater(goodsStockDOs, goodsStockDAO, orderItemDTOMap);
    }
}
