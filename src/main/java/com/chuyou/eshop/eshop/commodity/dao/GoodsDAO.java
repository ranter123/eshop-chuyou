package com.chuyou.eshop.eshop.commodity.dao;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsQuery;

import java.util.List;

/**
 * @Description: 商品管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 17:28
 */
public interface GoodsDAO {

    /**
     * 根据类目id查询商品属性
     * @param categoryId 类目id
     * @return 商品属性
     * @throws Exception
     */
    Long countByCategoryId(Long categoryId) throws Exception;

    /**
     * 根据品牌id查询商品属性
     * @param brandId 品牌id
     * @return 商品数量
     * @throws Exception
     */
    Long countByBrandId(Long brandId) throws Exception;

    /**
     * 分页查询商品
     * @param query 查询条件
     * @return 商品
     * @throws Exception
     */
    List<GoodsDO> listByPage(GoodsQuery query) throws Exception;

    /**
     * 根据id查询商品
     * @param id 商品id
     * @return 商品
     * @throws Exception
     */
    GoodsDO getById(Long id) throws Exception;

    /**
     * 新增商品
     * @param goods 商品
     * @return 商品id
     * @throws Exception
     */
    Long save(GoodsDO goods) throws Exception;

    /**
     * 更新商品
     * @param goods 商品
     */
    void update(GoodsDO goods);

    /**
     * 更新商品状态
     * @param goods 商品
     */
    void updateStatus(GoodsDO goods);

    /**
     * 删除商品
     * @param id 商品id
     */
    void remove(Long id);
}
