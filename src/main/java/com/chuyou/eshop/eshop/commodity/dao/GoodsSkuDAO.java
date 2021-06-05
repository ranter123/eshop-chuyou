package com.chuyou.eshop.eshop.commodity.dao;

import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuDO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsSkuQuery;

import java.util.List;

/**
 * @Description: 商品sku管理DAO接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 9:56
 */
public interface GoodsSkuDAO {

    /**
     * 根据商品id查询商品sku
     * @param goodsId 商品id
     * @return 商品sku
     */
    List<GoodsSkuDO> listByGoodsId(Long goodsId);

    /**
     * 新增商品sku
     * @param goodsSku
     * @return 商品sku id
     */
    Long save(GoodsSkuDO goodsSku);

    /**
     * 根据商品id删除sku
     * @param goodsId 商品id
     */
    void removeByGoodsId(Long goodsId);

    /**
     * 根据id查询商品sku
     * @param id 商品sku id
     * @return 商品sku
     */
    GoodsSkuDO getById(Long id);

    /**
     * 分页查询商品sku
     * @param query 查询条件
     * @return 商品sku
     */
    List<GoodsSkuDO> listByPage(GoodsSkuQuery query);

}
