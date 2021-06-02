package com.chuyou.eshop.eshop.commodity.service;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsQuery;

import java.util.List;

/**
 * @Description: 商品管理service组件接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 19:10
 */
public interface GoodsService {

    /**
     * 分页查询商品
     * @param query 查询条件
     * @return 商品
     * @throws Exception
     */
    List<GoodsDTO> listByPage(GoodsQuery query) throws Exception;

    /**
     * 根据id查询商品
     * @param id 商品id
     * @return 商品
     * @throws Exception
     */
    GoodsDTO getById(Long id) throws Exception;

    /**
     * 新增商品
     * @param goods 商品
     * @return 商品id
     * @throws Exception
     */
    Long save(GoodsDTO goods) throws Exception;

    /**
     * 更新商品
     * @param goods 商品
     * @return 处理结果
     * @throws Exception
     */
    Boolean update(GoodsDTO goods) throws Exception;

    /**
     * 审核商品
     * @param goodsId 商品id
     * @param approveResult 审核结果
     * @return 处理结果
     * @throws Exception
     */
    Boolean approve(Long goodsId, Integer approveResult) throws Exception;

    /**
     * 执行上架操作
     * @param goodsId 商品id
     * @return 处理结果
     * @throws Exception
     */
    Boolean putOnShelves(Long goodsId) throws Exception;

    /**
     * 执行下架操作
     * @param goodsId 商品id
     * @return 处理结果
     * @throws Exception
     */
    Boolean pullOffShelves(Long goodsId) throws Exception;

    /**
     * 执行删除操作
     * @param goodsId 商品id
     * @return 执行结果
     * @throws Exception
     */
    Boolean remove(Long goodsId) throws Exception;
}
