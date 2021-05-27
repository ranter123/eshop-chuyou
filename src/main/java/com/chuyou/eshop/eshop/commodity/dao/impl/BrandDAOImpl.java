package com.chuyou.eshop.eshop.commodity.dao.impl;

import com.chuyou.eshop.eshop.commodity.dao.BrandDAO;
import com.chuyou.eshop.eshop.commodity.domain.BrandDO;
import com.chuyou.eshop.eshop.commodity.domain.BrandQuery;
import com.chuyou.eshop.eshop.commodity.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/29 10:54 下午
 * @Description: 品牌管理DAO组件
 */
@Repository
public class BrandDAOImpl implements BrandDAO {

    /**
     * 品牌管理mapper组件
     */
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询品牌
     * @param query 查询条件
     * @return 品牌
     */
    @Override
    public List<BrandDO> listByPage(BrandQuery query) {
        return brandMapper.listByPage(query);
    }

    /**
     * 根据品牌id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    @Override
    public BrandDO getById(Long id) {
        return brandMapper.getById(id);
    }

    /**
     * 新增品牌
     * @param brand 品牌
     */
    @Override
    public void save(BrandDO brand) {
        brandMapper.save(brand);
    }

    /**
     * 更新品牌
     * @param brand 品牌
     */
    @Override
    public void update(BrandDO brand) {
        brandMapper.update(brand);
    }

    /**
     * 更新品牌logo
     * @param brand 品牌
     */
    @Override
    public void updateLogoPath(BrandDO brand) {
        brandMapper.updateLogoPath(brand);
    }

    /**
     * 更新品牌授权认证书
     * @param brand 品牌
     */
    @Override
    public void updateAuthcherPath(BrandDO brand) {
        brandMapper.updateAuthVoucherPath(brand);
    }

    /**
     * 删除品牌
     * @param id 品牌ID
     */
    @Override
    public void remove(Long id) {
        brandMapper.remove(id);
    }
}
