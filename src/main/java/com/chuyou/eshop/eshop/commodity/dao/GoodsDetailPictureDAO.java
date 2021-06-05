package com.chuyou.eshop.eshop.commodity.dao;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailPictureDO;

import java.util.List;

/**
 * @Description: 商品详情图片DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/4 16:06
 */
public interface GoodsDetailPictureDAO {

    /**
     * 根据id查询商品图片
     * @param id 商品图片id
     * @return 商品图片
     */
    GoodsDetailPictureDO getById(Long id);

    /**
     * 根据id查询商品图片
     * @param goodsDetailId 商品图片id
     * @return 商品图片
     */
    List<GoodsDetailPictureDO> listByGoodsDetailId(Long goodsDetailId);

    /**
     * 新增商品详情图片
     * @param picture 图片
     * @return 商品详情图片id
     */
    Long save(GoodsDetailPictureDO picture);

    /**
     * 根据商品id删除图片
     * @param goodsDetailId 商品id
     */
    void removeByGoodsDetailId(Long goodsDetailId);
}
