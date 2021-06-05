package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.GoodsDetailPictureDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailPictureDO;
import com.chuyou.eshop.eshop.commodity.mapper.GoodsDetailPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/4 16:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsDetailPictureDAOImpl implements GoodsDetailPictureDAO {

    /**
     * 商品详情图片管理mapper组件
     */
    @Autowired
    private GoodsDetailPictureMapper goodsDetailPictureMapper;

    /**
     * 根据id查询商品图片
     * @param id 商品图片id
     * @return 商品图片
     */
    @Override
    public GoodsDetailPictureDO getById(Long id) {
        return goodsDetailPictureMapper.getById(id);
    }

    /**
     * 根据商品详情id查询商品详情图片
     * @param goodsDetailId 商品详情id
     * @return 商品图片
     */
    @Override
    public List<GoodsDetailPictureDO> listByGoodsDetailId(Long goodsDetailId) {
        return goodsDetailPictureMapper.listByGoodsDetailId(goodsDetailId);
    }

    /**
     * 新增商品详情图片
     * @param picture 图片
     */
    @Override
    public Long save(GoodsDetailPictureDO picture) {
        goodsDetailPictureMapper.save(picture);
        return picture.getId();
    }

    /**
     * 根据商品id删除图片
     * @param goodsDetailId 商品详情id
     */
    @Override
    public void removeByGoodsDetailId(Long goodsDetailId) {
        goodsDetailPictureMapper.removeByGoodsDetailId(goodsDetailId);
    }

}
