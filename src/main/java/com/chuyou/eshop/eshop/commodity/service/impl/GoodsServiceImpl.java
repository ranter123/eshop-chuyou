package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.constant.GoodsStatus;
import com.chuyou.eshop.eshop.commodity.dao.GoodsDAO;
import com.chuyou.eshop.eshop.commodity.dao.GoodsDetailDAO;
import com.chuyou.eshop.eshop.commodity.domain.*;
import com.chuyou.eshop.eshop.commodity.service.GoodsService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 商品服务service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 19:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    /**
     * 商品管理DAO组件
     */
    @Autowired
    private GoodsDAO goodsDAO;

    /**
     * 商品状态管理器
     */
    @Autowired
    private GoodsStateManager goodsStateManager;

    /**
     * 商品图片管理DAO组件
     */
    @Autowired
    private GoodsPictureDAO goodsPictureDAO;

    /**
     * 商品详情管理DAO组件
     */
    @Autowired
    private GoodsDetailDAO goodsDetailDAO;

    /**
     * 商品详情图片管理DAO组件
     */
    @Autowired
    private GoodsDetailPictureDAO goodsDetailPictureDAO;

    /**
     * 商品属性值管理DAO组件
     */
    @Autowired
    private GoodsPropertyValueDAO goodsPropertyValueDAO;

    /**
     * 商品sku管理DAO组件
     */
    @Autowired
    private GoodsSkuDAO goodsSkuDAO;

    /**
     * 商品sku属性值管理DAO组件
     */
    private GoodsSkuSalePropertyValueDAO goodsSkuSalePropertyValueDAO;

    /**
     * 分页查询商品
     * @param query 查询条件
     * @return 商品
     * @throws Exception
     */
    @Override
    public List<GoodsDTO> listByPage(GoodsQuery query) throws Exception {
        return ObjectUtils.convertList(goodsDAO.listByPage(query), GoodsDTO.class);
    }

    /**
     * 根据id查询商品
     * @param id 商品id
     * @return 商品
     * @throws Exception
     */
    @Override
    public GoodsDTO getById(Long id) throws Exception {
        return goodsDAO.getById(id).clone(GoodsDTO.class);
    }

    /**
     * 新增商品
     * @param goods 商品
     * @return 商品id
     * @throws Exception
     */
    @Override
    public Long save(GoodsDTO goods) throws Exception {
        goods.setStatus(GoodsStatus.UNKNOWN);
        goods.setGmtCreate(DateUtils.getCurrentTime());
        goods.setGmtModified(DateUtils.getCurrentTime());
        Long goodsId = goodsDAO.save(goods.clone(GoodsDO.class));
        goods.setId(goodsId);
        goodsStateManager.create(goods);
        return goodsId;
    }

    /**
     * 更新商品
     * @param goods 商品
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean update(GoodsDTO goods) throws Exception {
        if (!goodsStateManager.canEdit(goods)) {
            return false;
        }
        goodsDAO.update(goods.clone(GoodsDTO.class));
        goodsStateManager.edit(goods);
        return true;
    }

    /**
     * 审核商品
     * @param goodsId 商品id
     * @param approveResult 审核结果
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean approve(Long goodsId, Integer approveResult) throws Exception {
        GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
        if (!goodsStateManager.canApprove(goods)) {
            return false;
        }
        goodsStateManager.approve(goods, approveResult);
        return true;
    }

    /**
     * 执行上架操作
     * @param goodsId 商品id
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean putOnShelves(Long goodsId) throws Exception {
        GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
        if (!goodsStateManager.canPutOnShelves(goods)) {
            return false;
        }
        goodsStateManager.putOnShelves(goods);
        return true;
    }

    /**
     * 执行下架操作
     * @param goodsId 商品id
     * @return 处理结果
     * @throws Exception
     */
    @Override
    public Boolean pullOffShelves(Long goodsId) throws Exception {
        GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);
        if (!goodsStateManager.canPullOffShelves(goods)) {
            return false;
        }
        goodsStateManager.pullOffShelves(goods);
        return true;
    }

    /**
     * 执行删除操作
     * @param goodsId 商品id
     * @return 执行结果
     * @throws Exception
     */
    @Override
    public Boolean remove(Long goodsId) throws Exception {
        GoodsDTO goods = goodsDAO.getById(goodsId).clone(GoodsDTO.class);

        if (!goodsStateManager.canRemove(goods)) {
            return false;
        }
        goodsPictureDAO.removeByGoodsId(goodsId);

        GoodsDetailDO goodsDetail = goodsDetailDAO.getByGoodsId(goodsId);
        goodsDetailPictureDAO.removeByGoodsDetailId(goodsDetail.getId());
        goodsDetailDAO.remove(goodsDetail.getId());

        goodsPropertyValueDAO.removeByGoodsId(goodsId);
        List<GoodsSkuDO> goodsSkus = goodsSkuDAO.listByGoodsId(goodsId);
        for (GoodsSkuDO goodsSku : goodsSkus) {
            goodsSkuSalePropertyValueDAO.removeByGoodsSkuId(goodsSku.getId());
        }
        goodsSkuDAO.removeByGoodsId(goodsId);
        goodsDAO.remove(goodsId);
        return true;
    }
}
