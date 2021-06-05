package com.chuyou.eshop.eshop.commodity.state;

import com.chuyou.eshop.eshop.commodity.constant.GoodsStatus;
import com.chuyou.eshop.eshop.commodity.dao.GoodsDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 已上架状态
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 11:12
 */
@Component
public class PuttedOnShelvesGoodsState implements GoodsState {

    /**
     * 商品管理DAO组件
     */
    @Autowired
    private GoodsDAO goodsDAO;

    /**
     * 执行商品流转到当前状态的业务逻辑
     * @param goods 商品
     */
    @Override
    public void doTransition(GoodsDTO goods) throws Exception {
        goods.setStatus(GoodsStatus.PUTTED_ON_SHELVES);
        goods.setGmtModified(DateUtils.getCurrentTime());
        goodsDAO.updateStatus(goods.clone(GoodsDO.class));
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
        return true;
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
