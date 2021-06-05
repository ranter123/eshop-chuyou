package com.chuyou.eshop.eshop.commodity.service;

import com.chuyou.eshop.eshop.commodity.domain.GoodsPictureDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: 商品图片管理service接口
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/4 15:54
 */
public interface GoodsPictureService {

    /**
     * 根据商品id查询商品图片id
     * @param goodsId 商品id
     * @return 商品图片id
     * @throws Exception
     */
    List<Long> listIdsByGoodsId(Long goodsId) throws Exception;

    /**
     * 根据id查询商品图片
     * @param id 商品图片id
     * @return 商品图片
     * @throws Exception
     */
    GoodsPictureDTO getById(Long id) throws Exception;

    /**
     * 批量新增商品图片
     * @param goodsId 商品id
     * @param pictures 商品图片
     * @throws Exception
     */
    void batchSave(Long goodsId, MultipartFile[] pictures) throws Exception;

    /**
     * 根据商品id删除图片
     * @param goodsId 商品id
     * @throws Exception
     */
    void batchRemoveByGoodsId(Long goodsId) throws Exception;
}
