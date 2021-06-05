package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.GoodsPictureDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsPictureDO;
import com.chuyou.eshop.eshop.commodity.mapper.GoodsPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: 商品图片DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/4 15:59
 */
public class GoodsPictureDAOImpl implements GoodsPictureDAO {

    /**
     * 商品图片管理mapper组件
     */
    @Autowired
    private GoodsPictureMapper goodsPictureMapper;

    /**
     * 根据商品id查询商品图片id
     * @param goodsId 商品id
     * @return 商品图片id
     */
    @Override
    public List<Long> listIdsByGoodsId(Long goodsId) {
        return goodsPictureMapper.listIdsByGoodsId(goodsId);
    }

    /**
     * 根据id查询商品图片
     * @param id 商品图片id
     * @return 商品图片
     */
    @Override
    public List<GoodsPictureDO> listByGoodsId(Long goodsId) {
        return goodsPictureMapper.listByGoodsId(goodsId);
    }

    /**
     * 根据id查询商品图片
     * @param id 商品图片id
     * @return 商品图片
     */
    @Override
    public GoodsPictureDO getById(Long id) {
        return goodsPictureMapper.getById(id);
    }

    /**
     * 新增商品图片
     * @param picture 图片
     */
    @Override
    public void save(GoodsPictureDO picture) {
        goodsPictureMapper.save(picture);
    }

    /**
     * 根据商品id删除图片
     * @param goodsId 商品id
     */
    @Override
    public void removeByGoodsId(Long goodsId) {
        goodsPictureMapper.removeByGoodsId(goodsId);
    }
}
