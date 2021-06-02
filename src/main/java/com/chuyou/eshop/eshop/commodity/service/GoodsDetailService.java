package com.chuyou.eshop.eshop.commodity.service;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailDTO;

/**
 * @Description: 商品详情管理service接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 19:41
 */
public interface GoodsDetailService {

    /**
     * 根据商品id查询商品详情
     * @param goodsId 商品id
     * @return 商品详情
     * @throws Exception
     */
    GoodsDetailDTO getByGoodsId(Long goodsId) throws Exception;

    /**
     * 新增商品详情
     * @param goodsDetail 商品详情
     * @return 商品详情id
     * @throws Exception
     */
    Long save(GoodsDetailDTO goodsDetail) throws Exception;

    /**
     * 更新商品详情
     * @param goodsDetail 商品详情
     * @throws Exception
     */
    void update(GoodsDetailDTO goodsDetail) throws Exception;
}
