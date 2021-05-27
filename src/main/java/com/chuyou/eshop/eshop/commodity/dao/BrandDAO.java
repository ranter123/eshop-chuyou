package com.chuyou.eshop.eshop.commodity.dao;

import com.chuyou.eshop.eshop.commodity.domain.BrandDO;
import com.chuyou.eshop.eshop.commodity.domain.BrandQuery;
import com.chuyou.eshop.eshop.common.util.AbstractObject;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/29 10:53 下午
 * @Description: 品牌管理DAO组件接口
 */
public interface BrandDAO {

    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌
     */
    List<BrandDO> listByPage(BrandQuery query) throws Exception;

    /**
     * 根据品牌id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    BrandDO getById(Long id);

    /**
     * 新增品牌
     * @param brand 品牌
     */
    void save(BrandDO brand);

    /**
     * 更新品牌
     * @param brand 品牌
     */
    void update(BrandDO brand);

    /**
     * 更新品牌logo
     * @param brand 品牌
     */
    void updateLogoPath(BrandDO brand);

    /**
     * 更新品牌授权认证书
     * @param brand 品牌
     */
    void updateAuthcherPath(BrandDO brand);

    /**
     * 删除品牌
     * @param id 品牌ID
     */
    void remove(Long id);
}
