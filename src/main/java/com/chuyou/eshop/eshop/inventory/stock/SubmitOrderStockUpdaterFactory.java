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
 * 提交订单库存更新组件工厂
 * @author zhonghuashishan
 *
 */
@Component
public class SubmitOrderStockUpdaterFactory<T>  
		extends AbstractStockUpdaterFactory<T> {

	/**
	 * 构造函数
	 * @param goodsStockDAO 商品库存管理模块DAO组件
	 */
	@Autowired
	public SubmitOrderStockUpdaterFactory(
			GoodsStockDAO goodsStockDAO) {
		super(goodsStockDAO);
	}
	
	/**
	 * 获取要更新库存的商品sku id的集合
	 */
	@Override
	protected List<Long> getGoodsSkuIds(T parameter) throws Exception {
		OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
		
		List<Long> goodsSkuIds = new ArrayList<Long>();
		
		List<OrderItemDTO> orderItemDTOs = orderInfoDTO.getOrderItems();
		for(OrderItemDTO orderItemDTO : orderItemDTOs) {
			goodsSkuIds.add(orderItemDTO.getGoodsSkuId());
		}
		
		return goodsSkuIds;
	}

	/**
	 * 创建商品库存更新组件
	 * @param goodsStockDOs 商品库存DO对象集合
	 * @param parameter 订单DTO对象
	 * @return 商品库存更新组件
	 */
	@Override
	protected StockUpdater create(List<GoodsStockDO> goodsStockDOs,
			T parameter) throws Exception {
		OrderInfoDTO orderInfoDTO = (OrderInfoDTO) parameter;
		Map<Long, OrderItemDTO> orderItemDTOMap = new HashMap<Long, OrderItemDTO>(CollectionSize.DEFAULT);
		for(OrderItemDTO orderItemDTO : orderInfoDTO.getOrderItems()) {
			orderItemDTOMap.put(orderItemDTO.getGoodsSkuId(), orderItemDTO);
		}
		
		return new SubmitOrderStockUpdater(goodsStockDOs, goodsStockDAO, orderItemDTOMap);
	} 

}
