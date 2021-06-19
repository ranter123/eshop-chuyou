package com.chuyou.eshop.eshop.inventory.stock;

import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;

import java.util.List;
import java.util.Map;

/**
 * 退货入库
 * @author zhonghuashishan
 *
 */
public class ReturnGoodsInputStockUpdater extends AbstractStockUpdater {

	/**
	 * 退货入库单条目DTO集合
	 */
	private Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap;


	public ReturnGoodsInputStockUpdater(List<GoodsStockDO> goodsStockDOs,
										GoodsStockDAO goodsStockDAO,
										Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap) {
		super(goodsStockDOs, goodsStockDAO);
		this.returnGoodsInputOrderItemDTOMap = returnGoodsInputOrderItemDTOMap;
	}

	/**
	 * 更新销售库存
	 */
	@Override
	protected void updateSaleStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = 
					returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() 
					+ returnGoodsInputOrderItemDTO.getArrivalCount()); 
		}
	}

	/**
	 * 更新锁定库存
	 */
	@Override
	protected void updateLockedStockQuantity() throws Exception {
		
	}

	/**
	 * 更新已销售库存
	 */
	@Override
	protected void updateSaledStockQuantity() throws Exception {
		for(GoodsStockDO goodsStockDO : goodsStockDOs) {
			ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = 
					returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
			goodsStockDO.setSaledStockQuantity(goodsStockDO.getSaledStockQuantity() 
					- returnGoodsInputOrderItemDTO.getArrivalCount()); 
		}
	}
	
}
