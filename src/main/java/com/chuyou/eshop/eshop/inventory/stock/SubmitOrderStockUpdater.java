package com.chuyou.eshop.eshop.inventory.stock;


import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;

import java.util.List;
import java.util.Map;

/**
 * 提交订单库存更新组件
 * @author zhonghuashishan
 *
 */
public class SubmitOrderStockUpdater extends AbstractStockUpdater {

	/**
	 * 订单条目DTO对象集合
	 */
	private Map<Long, OrderItemDTO> orderItemDTOMap;


	public SubmitOrderStockUpdater(List<GoodsStockDO> goodsStockDOs,
								   GoodsStockDAO goodsStockDAO,
								   Map<Long, OrderItemDTO> orderItemDTOMap) {
		super(goodsStockDOs, goodsStockDAO);
		this.orderItemDTOMap = orderItemDTOMap;
	}

	/**
	 * 更新销售库存
	 */
	@Override
	protected void updateSaleStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() 
					- orderItemDTO.getPurchaseQuantity());  
		}
	}

	/**
	 * 更新锁定库存
	 */
	@Override
	protected void updateLockedStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			OrderItemDTO orderItemDTO = orderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setLockedStockQuantity(goodsStockDO.getLockedStockQuantity() 
					+ orderItemDTO.getPurchaseQuantity()); 
		}
	}
	
	/**
	 * 更新已销售库存
	 */
	@Override
	protected void updateSaledStockQuantity() throws Exception {
		
	}

}
