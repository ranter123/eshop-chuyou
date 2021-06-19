package com.chuyou.eshop.eshop.inventory.dao.impl;

import com.chuyou.eshop.eshop.inventory.dao.GoodsStockDAO;
import com.chuyou.eshop.eshop.inventory.domain.GoodsStockDO;
import com.chuyou.eshop.eshop.inventory.mapper.GoodsStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商品库存管理模块的DAO组件
 * @author zhonghuashishan
 *
 */
@Repository
public class GoodsStockDAOImpl implements GoodsStockDAO {
	
	/**
	 * 商品库存管理模块的mapper组件
	 */
	@Autowired
	private GoodsStockMapper goodsStockMapper;
	
	/**
	 * 根据商品sku id查询商品库存
	 * @param goodsSkuId 商品sku id
	 * @return 商品库存
	 */
	@Override
	public GoodsStockDO getGoodsStockBySkuId(Long goodsSkuId) throws Exception {
		return goodsStockMapper.getGoodsStockBySkuId(goodsSkuId);
	}
	
	/**
	 * 新增商品库存
	 * @param goodsStockDO 商品库存DO对象
	 */
	@Override
	public void saveGoodsStock(GoodsStockDO goodsStockDO) throws Exception {
		goodsStockMapper.saveGoodsStock(goodsStockDO);
	}
	
	/**
	 * 更新商品库存
	 * @param goodsStockDO 商品库存DO对象
	 */
	@Override
	public void updateGoodsStock(GoodsStockDO goodsStockDO) throws Exception {
		goodsStockMapper.updateGoodsStock(goodsStockDO);
	}

}
