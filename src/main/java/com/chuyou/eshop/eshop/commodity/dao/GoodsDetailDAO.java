package com.chuyou.eshop.eshop.commodity.dao;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailDO;

/**
 * @Description: 商品详情管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 19:46
 */
public interface GoodsDetailDAO {

    /**
     * 根据商品详情id查询商品详情
     * @param goodsId 商品id
     * @return 商品详情
     */
    GoodsDetailDO getByGoodsId(Long goodsId);

    /**
     * 新增商品详情
     * @param goodsDetail 商品详情
     * @return 商品详情id
     */
    Long save(GoodsDetailDO goodsDetail);

    /**
     * 更新商品详情
     * @param goodsDetail 商品详情
     */
    void update(GoodsDetailDO goodsDetail);

    /**
     * 删除商品详情
     * @param id 商品详情id
     */
    void remove(Long id);
}
