package com.chuyou.eshop.eshop.inventory.service;

import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;

/**
 * @Description: 库存中心对外提供接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/19 15:08
 */
public interface InventoryService {

    /**
     * 通知库存中心，“采购入库完成” 事件发生了
     * @param purchaseInputOrderDTO 采购入库DTO
     * @return 处理结果
     */
    Boolean informPurchaseInputFinished(PurchaseInputOrderDTO purchaseInputOrderDTO);

    /**
     * 通知库存中心，“提交订单” 事件发生了
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informSubmitOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知库存中心，“支付订单” 事件发生了
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informPayOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知库存中心，“取消订单” 事件发生了
     * @param orderDTO 订单DTO
     * @return 处理结果
     */
    Boolean informCancelOrderEvent(OrderInfoDTO orderDTO);

    /**
     * 通知库存中心，“完成退货入库” 事件发生了
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return 处理结果
     */
    Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO);

    /**
     * 查询商品sku的库存
     * @param goodsSkuId 商品sku id
     * @return 商品sku的库存
     */
    Long getSaleStockQuantity(Long goodsSkuId);

    /**
     * 设置销售库存
     * @param goodsSkuId 商品sku id
     * @param saleStockQuantity 销售库存
     * @return 处理结果
     */
    Boolean setSaleStockQuantity(Long goodsSkuId, Long saleStockQuantity);
}
