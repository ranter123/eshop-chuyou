package com.chuyou.eshop.eshop.commodity.state;

import com.chuyou.eshop.eshop.commodity.constant.ApproveResult;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 商品状态管理组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 11:26
 */
@Component
public class GoodsManagerImpl implements GoodsStateManager{

    /**
     * 待审核状态
     */
    @Autowired
    private WaitForApproveGoodsState waitForApproveGoodsState;

    /**
     * 待上架状态
     */
    @Autowired
    private WaitForPutOnShelvesGoodsState waitForPutOnShelvesGoodsState;

    /**
     * 审核未通过状态
     */
    @Autowired
    private ApproveRejectGoodsState approveRejectGoodsState;

    /**
     * 已上架状态
     */
    @Autowired
    private PuttedOnShelvesGoodsState puttedOnShelvesGoodsState;

    /**
     * 商品状态工厂
     */
    @Autowired
    private GoodsStateFactory goodsStateFactory;

    /**
     * 创建一个商品
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public void create(GoodsDTO goods) throws Exception {
        waitForApproveGoodsState.doTransition(goods);
    }

    /**
     * 当前这个商品能否执行编辑
     * @param goods 商品
     * @return 能否执行编辑
     * @throws Exception
     */
    @Override
    public Boolean canEdit(GoodsDTO goods) throws Exception {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canEdit(goods);
    }

    /**
     * 执行商品编辑操作
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public void edit(GoodsDTO goods) throws Exception {
        waitForApproveGoodsState.doTransition(goods);
    }

    /**
     * 判断当前商品能否执行审核操作
     * @param goods 商品
     * @return 能否执行审核操作
     * @throws Exception
     */
    @Override
    public Boolean canApprove(GoodsDTO goods) throws Exception {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canApprove(goods);
    }

    /**
     * 执行审核操作
     * @param goods 商品
     * @param approveResult 审核操作
     * @throws Exception
     */
    @Override
    public void approve(GoodsDTO goods, Integer approveResult) throws Exception {
        if (ApproveResult.APPROVE_PASS.equals(approveResult)) {
            waitForPutOnShelvesGoodsState.doTransition(goods);
        } else if (ApproveResult.APPROVE_REJECT.equals(approveResult)){
            approveRejectGoodsState.doTransition(goods);
        }
    }

    /**
     * 判断能否执行上架操作
     * @param goods 商品
     * @return 能否执行上架操作
     * @throws Exception
     */
    @Override
    public Boolean canPutOnShelves(GoodsDTO goods) throws Exception {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canPutOnShelves(goods);
    }

    /**
     * 执行上架操作
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public void putOnShelves(GoodsDTO goods) throws Exception {
        puttedOnShelvesGoodsState.doTransition(goods);
    }

    /**
     * 能否执行下架操作
     * @param goods 商品
     * @return 能否执行下架操作
     * @throws Exception
     */
    @Override
    public Boolean canPullOffShelves(GoodsDTO goods) throws Exception {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canPullOffShelves(goods);
    }

    /**
     * 执行下架操作
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public void pullOffShelves(GoodsDTO goods) throws Exception {
        waitForPutOnShelvesGoodsState.doTransition(goods);
    }

    /**
     * 判断能否执行删除操作
     * @param goods 商品
     * @return 能否执行删除操作
     * @throws Exception
     */
    @Override
    public Boolean canRemove(GoodsDTO goods) throws Exception {
        GoodsState goodsState = goodsStateFactory.get(goods);
        return goodsState.canRemove(goods);
    }
}
