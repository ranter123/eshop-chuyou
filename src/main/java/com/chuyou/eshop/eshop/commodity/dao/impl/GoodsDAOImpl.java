package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.GoodsDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsQuery;
import com.chuyou.eshop.eshop.commodity.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 商品管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 17:38
 */
@Service
public class GoodsDAOImpl implements GoodsDAO {

    /**
     * 商品管理mapper组件
     */
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 根据类目id查询商品属性
     * @param categoryId 类目id
     * @return 商品属性
     * @throws Exception
     */
    @Override
    public Long countByCategoryId(Long categoryId) throws Exception {
        return goodsMapper.countByCategoryId(categoryId);
    }

    /**
     * 根据品牌id查询商品属性
     * @param brandId 品牌id
     * @return 商品数量
     * @throws Exception
     */
    @Override
    public Long countByBrandId(Long brandId) throws Exception {
        return goodsMapper.countByBrandId(brandId);
    }

    /**
     * 分页查询商品
     * @param query 查询条件
     * @return 商品
     * @throws Exception
     */
    @Override
    public List<GoodsDO> listByPage(GoodsQuery query) throws Exception {
        return goodsMapper.listByPage(query);
    }

    /**
     * 根据id查询商品
     * @param id 商品id
     * @return 商品
     * @throws Exception
     */
    @Override
    public GoodsDO getById(Long id) throws Exception {
        return goodsMapper.getById(id);
    }

    /**
     * 新增商品
     * @param goods 商品
     * @return 商品id
     * @throws Exception
     */
    @Override
    public Long save(GoodsDO goods) throws Exception {
        goodsMapper.save(goods);
        return goods.getId();
    }

    /**
     * 更新商品
     * @param goods 商品
     */
    @Override
    public void update(GoodsDO goods) {
        goodsMapper.update(goods);
    }

    /**
     * 更新商品状态
     * @param goods 商品
     */
    @Override
    public void updateStatus(GoodsDO goods) {
        goodsMapper.updateStatus(goods);
    }

    /**
     * 删除商品
     * @param id 商品id
     */
    @Override
    public void remove(Long id) {
        goodsMapper.remove(id);
    }
}
