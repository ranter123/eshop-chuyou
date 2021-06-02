package com.chuyou.eshop.eshop.commodity.controller;

import com.chuyou.eshop.eshop.commodity.domain.GoodsDTO;
import com.chuyou.eshop.eshop.commodity.domain.GoodsQuery;
import com.chuyou.eshop.eshop.commodity.domain.GoodsVO;
import com.chuyou.eshop.eshop.commodity.service.GoodsService;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 商品管理controller组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/1 17:12
 */
@RestController
@RequestMapping("/commodity/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    /**
     * 商品管理service组件
     */
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询商品
     * @param goodsQuery 查询条件
     * @return 商品
     */
    @GetMapping("/")
    public List<GoodsVO> listByPage(@RequestBody GoodsQuery goodsQuery) {
        try{
            return ObjectUtils.convertList(goodsService.listByPage(goodsQuery), GoodsVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 根据id查询商品
     * @param id 商品id
     * @return 商品
     */
    @GetMapping("/{id}")
    public GoodsVO getById(@PathVariable("id") Long id) {
        try {
            return goodsService.getById(id).clone(GoodsVO.class);
        } catch (Exception e) {
            logger.error("error", e);
            return new GoodsVO();
        }
    }

    /**
     * 新增商品
     * @param goods 商品
     * @return
     */
    @PostMapping("/")
    public Long save(@RequestBody GoodsVO goods){
        try {
            return goodsService.save(goods.clone(GoodsDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * 更新商品
     * @param goods 商品
     * @return
     */
    @PutMapping("/")
    public Boolean update(@RequestBody GoodsVO goods) {
        try {
            return goodsService.update(goods.clone(GoodsDTO.class));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 审核商品
     * @param goodsId 商品id
     * @param approveResult 审核参数
     * @return 处理结果
     */
    @PutMapping("/approve/{goodsId}")
    public Boolean approve(@PathVariable("goodsId") Long goodsId, Integer approveResult) {
        try {
            return goodsService.approve(goodsId, approveResult);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 商品上架
     * @param goodsId 商品id
     * @return
     */
    @PutMapping("/putOnShelves/{goodsId}")
    public Boolean putOnShelves(@PathVariable("goodsId") Long goodsId) {
        try {
            return goodsService.putOnShelves(goodsId);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 商品下架
     * @param goodsId 商品id
     * @return 处理结果
     */
    @PutMapping("/pullOfShelves/{goodsId}")
    public Boolean pullOfShelves(@PathVariable("goodsId") Long goodsId) {
        try {
            return goodsService.pullOffShelves(goodsId);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除商品
     * @param goodsId 商品id
     * @return 处理结果
     */
    @DeleteMapping("/{goodsId}")
    public Boolean remove(@PathVariable("goodsId") Long goodsId) {
        try {
            return goodsService.remove(goodsId);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
