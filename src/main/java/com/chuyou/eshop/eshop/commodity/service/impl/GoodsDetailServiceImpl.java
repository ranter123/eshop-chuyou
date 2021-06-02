package com.chuyou.eshop.eshop.commodity.service.impl;

import com.chuyou.eshop.eshop.commodity.dao.GoodsDetailDAO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailDO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailDTO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsDetailVO;
import com.chuyou.eshop.eshop.commodity.service.GoodsDetailService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 商品详情管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/2 19:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsDetailServiceImpl implements GoodsDetailService {

    /**
     * 商品详情管理DAO组件
     */
    @Autowired
    private GoodsDetailDAO goodsDetailDAO;

    /**
     * 根据商品id查询商品详情
     * @param goodsId 商品id
     * @return 商品详情
     * @throws Exception
     */
    @Override
    public GoodsDetailDTO getByGoodsId(Long goodsId) throws Exception {
        return goodsDetailDAO.getByGoodsId(goodsId).clone(GoodsDetailDTO.class);
    }

    /**
     * 新增商品详情
     * @param goodsDetail 商品详情
     * @return 商品详情id
     * @throws Exception
     */
    @Override
    public Long save(GoodsDetailDTO goodsDetail) throws Exception {
        goodsDetail.setGmtCreate(DateUtils.getCurrentTime());
        goodsDetail.setGmtModified(DateUtils.getCurrentTime());
        return goodsDetailDAO.save(goodsDetail.clone(GoodsDetailDO.class));
    }

    /**
     * 更新商品详情
     * @param goodsDetail 商品详情
     * @throws Exception
     */
    @Override
    public void update(GoodsDetailDTO goodsDetail) throws Exception {
        goodsDetail.setGmtModified(DateUtils.getCurrentTime());
        goodsDetailDAO.update(goodsDetail.clone(GoodsDetailDO.class));
    }
}
