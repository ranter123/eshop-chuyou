package com.chuyou.eshop.eshop.commodity.state;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;
import org.springframework.stereotype.Component;

/**
 * @Description: 默认商品状态
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 11:05
 */
@Component
public class DefaultGoodsState implements GoodsState{

    /**
     * 执行商品流转到当前状态的业务逻辑
     * @param goods 商品
     */
    @Override
    public void doTransition(GoodsDTO goods) throws Exception {

    }

    /**
     * 判断当前商品能否执行编辑操作
     * @param goods 商品
     * @return 能否执行编辑操作
     * @throws Exception
     */
    @Override
    public Boolean canEdit(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断当前商品能否执行审核操作
     * @param goods 商品
     * @return 能否执行审核操作
     * @throws Exception
     */
    @Override
    public Boolean canApprove(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断当前商品能否执行上架操作
     * @param goods 商品
     * @return 能否执行上架操作
     * @throws Exception
     */
    @Override
    public Boolean canPutOnShelves(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断当前商品能否执行下架操作
     * @param goods 商品
     * @return 能否执行下架操作
     * @throws Exception
     */
    @Override
    public Boolean canPullOffShelves(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断当前商品能否执行删除操作
     * @param goods 商品
     * @return 能否执行删除操作
     * @throws Exception
     */
    @Override
    public Boolean canRemove(GoodsDTO goods) throws Exception {
        return false;
    }
}
