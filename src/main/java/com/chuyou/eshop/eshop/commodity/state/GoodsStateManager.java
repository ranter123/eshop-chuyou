package com.chuyou.eshop.eshop.commodity.state;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;

/**
 * @Description: 商品状态管理组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 11:20
 */
public interface GoodsStateManager {

    /**
     * 创建一个商品
     * @param goods 商品
     * @throws Exception
     */
    void create(GoodsDTO goods) throws Exception;

    /**
     * 当前这个商品能否执行编辑
     * @param goods 商品
     * @return 能否执行编辑
     * @throws Exception
     */
    Boolean canEdit(GoodsDTO goods) throws Exception;

    /**
     * 执行商品编辑操作
     * @param goods 商品
     * @throws Exception
     */
    void edit(GoodsDTO goods) throws Exception;

    /**
     * 判断当前商品能否执行审核操作
     * @param goods 商品
     * @return 能否执行审核操作
     * @throws Exception
     */
    Boolean canApprove(GoodsDTO goods) throws Exception;

    /**
     * 执行审核操作
     * @param goods 商品
     * @param approveResult 审核操作
     * @throws Exception
     */
    void approve(GoodsDTO goods, Integer approveResult) throws Exception;

    /**
     * 判断能否执行上架操作
     * @param goods 商品
     * @return 能否执行上架操作
     * @throws Exception
     */
    Boolean canPutOnShelves(GoodsDTO goods) throws Exception;

    /**
     * 执行上架操作
     * @param goods 商品
     * @throws Exception
     */
    void putOnShelves(GoodsDTO goods) throws Exception;

    /**
     * 能否执行下架操作
     * @param goods 商品
     * @return 能否执行下架操作
     * @throws Exception
     */
    Boolean canPullOffShelves(GoodsDTO goods) throws Exception;

    /**
     * 执行下架操作
     * @param goods 商品
     * @throws Exception
     */
    void pullOffShelves(GoodsDTO goods) throws Exception;

    /**
     * 判断能否执行删除操作
     * @param goods 商品
     * @return 能否执行删除操作
     * @throws Exception
     */
    Boolean canRemove(GoodsDTO goods) throws Exception;
}
