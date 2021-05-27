package com.chuyou.eshop.eshop.auth.service.impl;

import com.chuyou.eshop.eshop.auth.dao.PriorityDAO;
import com.chuyou.eshop.eshop.auth.domain.PriorityDO;
import com.chuyou.eshop.eshop.auth.domain.PriorityDTO;
import com.chuyou.eshop.eshop.auth.service.PriorityService;
import com.chuyou.eshop.eshop.common.bean.SpringApplicationContext;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 9:34 下午
 * @Description: 权限service组件
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PriorityServiceImpl implements PriorityService {


    @Autowired
    private PriorityDAO priorityDAO;

    @Autowired
    private SpringApplicationContext context;

    /**
     * 查询根权限
     * @return 根权限集合
     */
    @Override
    public List<PriorityDTO> listRootPriorities() throws IllegalAccessException, InstantiationException {
        List<PriorityDO> priorityDOs = priorityDAO.listRootPriorities();
        if (priorityDOs == null) {
            return null;
        }
        return ObjectUtils.convertList(priorityDOs, PriorityDTO.class);
    }

    /**
     * 根据父权限ID查询子权限
     * @param parentId 父权限ID
     * @return 子权限
     */
    @Override
    public List<PriorityDTO> listChildPriorities(Long parentId) throws IllegalAccessException, InstantiationException {
        List<PriorityDO> priorityDOs = priorityDAO.listChildPriorities(parentId);
        if (priorityDOs == null) {
            return null;
        }
        return ObjectUtils.convertList(priorityDOs, PriorityDTO.class);
    }

    /**
     * 根据ID查询权限
     * @param id 权限ID
     * @return 权限
     */
    @Override
    public PriorityDTO getPriorityById(Long id) throws InstantiationException, IllegalAccessException {
        PriorityDO priorityDO = priorityDAO.getPriorityById(id);
        if (priorityDO == null) {
            return null;
        }
        return priorityDO.clone(PriorityDTO.class);
    }

    /**
     * 查询账号被授权的权限树
     * @param accountId 账号ID
     * @return 权限树
     */
    @Override
    public List<Priority> listAuthorizedByAccountId(Long accountId) {
        return null;
    }

    @Override
    public Boolean existAuthorizedByCode(Long accountId, String coce) {
        return null;
    }

    /**
     * 新增权限
     * @param priorityDTO 权限
     */
    @Override
    public void savePriority(PriorityDTO priorityDTO) throws ParseException, InstantiationException, IllegalAccessException {
        priorityDTO.setGmtModified(DateUtils.getCurrentTime());
        priorityDTO.setGmtCreate(DateUtils.getCurrentTime());
        priorityDAO.savePriority(priorityDTO.clone(PriorityDO.class));
    }

    /**
     * 更新权限
     * @param priorityDTO 权限
     */
    @Override
    public void updatePriority(PriorityDTO priorityDTO) throws Exception {
        priorityDTO.setGmtModified(DateUtils.getCurrentTime());
        priorityDAO.updatePriority(priorityDTO.clone(PriorityDO.class));

        List<Long> accountIds = priorityDAO.listAccountIdsByPriorityId(priorityDTO.getId());
        for (Long accountId : accountIds) {

        }
    }

    @Override
    public Boolean removePriority(Long id) throws Exception{
        //根据ID查询权限
        Priority priority = priorityDAO.getPriorityById(id)
                .clone(Priority.class);

        //检查这个权限以及其下任何一个子权限，是否被角色或者账号给关联
        RelatedCheckPriorityOperation relatedCheckOperation = context.getBean(RelatedCheckPriorityOperation.class);
        Boolean relateCheckResult = priority.execute(relatedCheckOperation);

        if (relateCheckResult) {
            return false;
        }

        //递归删除当前权限以及其下所有的子权限
        RemovePriorityOperation removePriorityOperation = context.getBean(RemovePriorityOperation.class);
        priority.execute(removePriorityOperation);
        return true;
    }
}
