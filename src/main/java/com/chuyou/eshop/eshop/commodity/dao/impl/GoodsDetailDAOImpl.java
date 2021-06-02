package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.GoodsDetailDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailDO;
import com.chuyou.eshop.eshop.commodity.mapper.GoodsDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Description: 商品详情管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 19:48
 */
@Repository
public class GoodsDetailDAOImpl implements GoodsDetailDAO {

    /**
     * 商品详情管理mapper组件
     */
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    /**
     * 根据商品详情id查询商品详情
     * @param goodsId 商品id
     * @return 商品详情
     */
    @Override
    public GoodsDetailDO getByGoodsId(Long goodsId) {
        return goodsDetailMapper.getByGoodsId(goodsId);
    }

    /**
     * 新增商品详情
     * @param goodsDetail 商品详情
     * @return 商品详情id
     */
    @Override
    public Long save(GoodsDetailDO goodsDetail) {
        goodsDetailMapper.save(goodsDetail);
        return goodsDetail.getId();
    }

    /**
     * 更新商品详情
     * @param goodsDetail 商品详情
     */
    @Override
    public void update(GoodsDetailDO goodsDetail) {
        goodsDetailMapper.update(goodsDetail);
    }

    /**
     * 删除商品详情
     * @param id 商品详情id
     */
    @Override
    public void remove(Long id) {
        goodsDetailMapper.remove(id);
    }
}
