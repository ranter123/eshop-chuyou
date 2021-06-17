package com.chuyou.eshop.eshop.menbership.service.impl;

import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.menbership.dao.UserAccountDAO;
import com.chuyou.eshop.eshop.menbership.dao.UserDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.UserAccountDO;
import com.chuyou.eshop.eshop.menbership.domain.UserAccountDTO;
import com.chuyou.eshop.eshop.menbership.domain.UserDetailDO;
import com.chuyou.eshop.eshop.menbership.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 用户账号管理service组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 10:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAccountServiceImpl implements UserAccountService {

    /**
     * 用户账号管理DAO组件
     */
    @Autowired
    private UserAccountDAO userAccountDAO;
    /**
     * 用户详细信息管理DAO组件
     */
    @Autowired
    private UserDetailDAO userDetailDAO;

    /**
     * 新增用户账号
     * @param userAccount 用户账号
     */
    @Override
    public UserAccountDTO save(UserAccountDTO userAccount) throws Exception {
        userAccount.setGmtCreate(DateUtils.getCurrentTime());
        userAccount.setGmtModified(DateUtils.getCurrentTime());
        UserAccountDO resultUserAccount = userAccountDAO.save(
                userAccount.clone(UserAccountDO.class));

        UserDetailDO userDetail = new UserDetailDO();
        userDetail.setUserAccountId(resultUserAccount.getId());
        userDetail.setGmtCreate(DateUtils.getCurrentTime());
        userDetail.setGmtModified(DateUtils.getCurrentTime());
        userDetailDAO.save(userDetail);

        return resultUserAccount.clone(UserAccountDTO.class);
    }

    /**
     * 为登录来统计是否有对应的账号在
     * @param userAccount 用户账号
     * @return
     */
    @Override
    public UserAccountDTO getForLogin(UserAccountDTO userAccount) throws Exception {
        UserAccountDO resultUserAccount = userAccountDAO.getForLogin(
                userAccount.clone(UserAccountDO.class));
        return resultUserAccount.clone(UserAccountDTO.class);
    }

    /**
     * 根据id查询用户账号
     * @param id 用户账号id
     * @return 用户账号
     */
    @Override
    public UserAccountDTO getById(Long id) throws Exception {
        return userAccountDAO.getById(id).clone(UserAccountDTO.class);
    }

    /**
     * 更新密码
     * @param userAccount 用户账号
     */
    @Override
    public void updatePassword(UserAccountDO userAccount) throws Exception {
        userAccount.setGmtModified(DateUtils.getCurrentTime());
        userAccountDAO.updatePassword(userAccount);
    }

}
