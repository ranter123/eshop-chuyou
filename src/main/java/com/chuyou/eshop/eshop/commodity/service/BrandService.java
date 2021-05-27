package com.chuyou.eshop.eshop.commodity.service;

import com.chuyou.eshop.eshop.commodity.domain.BrandDTO;
import com.chuyou.eshop.eshop.commodity.domain.BrandQuery;
import com.chuyou.eshop.eshop.common.util.AbstractObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/29 10:48 下午
 * @Description: 品牌管理service组件
 */
public interface BrandService {


    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌
     * @throws Exception
     */
    List<BrandDTO> listByPage(BrandQuery query) throws Exception;

    /**
     * 根据品牌id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    BrandDTO getById(Long id) throws Exception;

    /**
     * 新增品牌
     * @param brand 品牌
     * @param logoFile logo文件
     * @param authVoucherFile 认证文件
     */
    void save(BrandDTO brand, MultipartFile logoFile, MultipartFile authVoucherFile) throws Exception;

    /**
     * 编辑品牌
     * @param brand 品牌
     * @throws Exception
     */
    void update(BrandDTO brand) throws Exception;

    /**
     * 更新品牌logo图片
     * @param id 图片ID
     * @param logoFile 图片文件
     */
    void updateLogoPicture(Long id, MultipartFile logoFile) throws Exception;

    /**
     * 更新品牌的授权书图片
     * @param id 品牌ID
     * @param authVoucherFile 品牌授权书图片
     */
    void updateAuthVoucherPicture(Long id, MultipartFile authVoucherFile) throws Exception;

    /**
     * 删除品牌
     * @param id 品牌ID
     */
    void remove(Long id);
}
