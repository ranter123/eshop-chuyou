package com.chuyou.eshop.eshop.logistics.dao.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.logistics.dao.FreightTemplateDAO;
import com.chuyou.eshop.eshop.logistics.domain.FreightTemplateDO;
import com.chuyou.eshop.eshop.logistics.domain.FreightTemplateQuery;
import com.chuyou.eshop.eshop.logistics.mapper.FreightTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 运费模板管理DAO组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 19:33
 */
@Repository
public class FreightTemplateDAOImpl implements FreightTemplateDAO {

    /**
     * 运费模板管理mapper组件
     */
    @Autowired
    private FreightTemplateMapper freightTemplateMapper;

    /**
     * 新增运费模板
     * @param freightTemplate 运费模板
     */
    @Override
    public void save(FreightTemplateDO freightTemplate) throws Exception {
        freightTemplate.setGmtCreate(DateUtils.getCurrentTime());
        freightTemplate.setGmtModified(DateUtils.getCurrentTime());
        freightTemplateMapper.save(freightTemplate);
    }

    /**
     * 分页查询运费模板
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    @Override
    public List<FreightTemplateDO> listByPage(FreightTemplateQuery query) throws Exception {
        return freightTemplateMapper.listByPage(query);
    }

    /**
     * 根据id查询运费模板
     * @param query 运费模板查询条件
     * @return 运费模板
     */
    @Override
    public FreightTemplateDO getById(Long id) throws Exception {
        return freightTemplateMapper.getById(id);
    }

    /**
     * 更新运费模板
     * @param freightTemplate 运费模板
     */
    @Override
    public void update(FreightTemplateDO freightTemplate) throws Exception {
        freightTemplateMapper.update(freightTemplate);
    }


}
