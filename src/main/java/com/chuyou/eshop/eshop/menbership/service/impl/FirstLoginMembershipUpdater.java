package com.chuyou.eshop.eshop.menbership.service.impl;

import com.chuyou.eshop.eshop.common.util.CollectionSize;
import com.chuyou.eshop.eshop.menbership.constant.MemberLevel;
import com.chuyou.eshop.eshop.menbership.constant.UpdateMemberLevelResult;
import com.chuyou.eshop.eshop.menbership.constant.UpdateMemberPointResult;
import com.chuyou.eshop.eshop.menbership.dao.MemberLevelDAO;
import com.chuyou.eshop.eshop.menbership.dao.MemberLevelDetailDAO;
import com.chuyou.eshop.eshop.menbership.dao.MemberPointDAO;
import com.chuyou.eshop.eshop.menbership.dao.MemberPointDetailDAO;
import com.chuyou.eshop.eshop.menbership.domain.MemberLevelDO;
import com.chuyou.eshop.eshop.menbership.domain.MemberPointDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 每天第一次登录的会员信息更新组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/15 16:55
 */
@Component
public class FirstLoginMembershipUpdater extends AbstractMembershipUpdater<Object, Boolean> {

    /**
     * 每天第一次登录对成长值的累加值
     */
    private static final Long FIRST_LOGIN_GROWTH_VALUE_UPDATE = 5L;

    /**
     * 每天第一次登录对会员积分的累加值
     */
    private static final Long FIRST_LOGIN_MEMBER_POINT_UPDATE = 5L;

    /**
     * 会员等级管理DAO组件
     */
    @Autowired
    private MemberLevelDAO memberLevelDAO;

    /**
     * 会员积分管理DAO组件
     */
    @Autowired
    private MemberPointDAO memberPointDAO;

    @Autowired
    public FirstLoginMembershipUpdater(MemberLevelDetailDAO memberLevelDetailDAO, MemberPointDetailDAO memberPointDetailDAO) {
        super(memberLevelDetailDAO, memberPointDetailDAO);
    }

    /**
     * 首次登录-完成执行本次会员信息更新操作
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 返回值
     * @throws Exception
     */
    @Override
    protected Boolean finishExecute(Long userAccountId, Object parameter) throws Exception {
        return true;
    }


    /**
     * 首次登录-获取会员积分更新原因
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @param updateMemberPointResult 更新结果
     * @return 更新原因
     * @throws Exception
     */
    @Override
    protected String getMemberPointUpdateReason(Long userAccountId, Object parameter, Map<String, Object> updateMemberPointResult)
            throws Exception {
        return "用户每天第一次登录";
    }

    /**
     * 首次登录-获取会员积分更新原因
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @param updateMemberLevelResult 更新结果
     * @return 更新原因
     * @throws Exception
     */
    @Override
    protected String getMemberLevelUpdateReason(Long userAccountId, Object parameter, Map<String, Object> updateMemberLevelResult)
            throws Exception {
        return "用户每天第一次登录";
    }

    /**
     * 首次登录-更新会员积分
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 返回值
     */
    @Override
    protected Map<String, Object> updateMemberPoint(Long userAccountId, Object parameter) throws Exception{
        Map<String, Object> result = new HashMap<>(CollectionSize.DEFAULT);
        MemberPointDO memberPoint = memberPointDAO.getByUserAccountId(userAccountId);
        result.put(UpdateMemberPointResult.OLD_MEMBER_POINT, memberPoint.getPoint());
        memberPoint.setPoint(memberPoint.getPoint() + FIRST_LOGIN_MEMBER_POINT_UPDATE);
        memberPointDAO.update(memberPoint);
        result.put(UpdateMemberPointResult.UPDATED_MEMBER_POINT, FIRST_LOGIN_MEMBER_POINT_UPDATE);
        result.put(UpdateMemberPointResult.NEW_MEMBER_POINT, memberPoint.getPoint());
        return result;
    }

    /**
     * 首次登录-更新会员等级
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 返回值
     * @throws Exception
     */
    @Override
    protected Map<String, Object> updateMemberLevel(Long userAccountId, Object parameter) throws Exception {
        Map<String, Object> result = new HashMap<>(CollectionSize.DEFAULT);
        MemberLevelDO memberLevel = memberLevelDAO.getByUserAccountId(userAccountId);
        result.put(UpdateMemberLevelResult.OLD_GROWTH_VALUE, memberLevel.getGrowthValue());
        result.put(UpdateMemberLevelResult.OLD_MEMBER_LEVEL, memberLevel.getLevel());

        memberLevel.setGrowthValue(memberLevel.getGrowthValue() + FIRST_LOGIN_GROWTH_VALUE_UPDATE);
        memberLevel.setLevel(MemberLevel.get(memberLevel.getGrowthValue()));
        memberLevelDAO.update(memberLevel);

        result.put(UpdateMemberLevelResult.UPDATED_GROWTH_VALUE, FIRST_LOGIN_GROWTH_VALUE_UPDATE);
        result.put(UpdateMemberLevelResult.NEW_GROWTH_VALUE, memberLevel.getGrowthValue());
        result.put(UpdateMemberLevelResult.NEW_MEMBER_LEVEL, memberLevel.getLevel());
        return result;
    }

    /**
     * 首次登录-完成撤销会员信息更新操作
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 返回值
     */
    @Override
    protected Boolean finishUndo(Long userAccountId, Object parameter) throws Exception {
        return null;
    }

    /**
     * 首次登录-获取撤销会员积分更新原因
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @param updateMemberPointResult 撤销结果
     * @return 更新原因
     */
    @Override
    protected String getUndoMemberPointUpdateReason(Long userAccountId, Object parameter,
                                                    Map<String, Object> updateMemberPointResult) {
        return null;
    }

    /**
     * 首次登录-获取撤销会员等级更新原因
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @param updateMemberLevelResult 撤销结果
     * @return 更新原因
     * @throws Exception
     */
    @Override
    protected String getUndoMemberLevelUpdateReason(Long userAccountId, Object parameter,
                                                    Map<String, Object> updateMemberLevelResult) throws Exception {
        return null;
    }

    /**
     * 首次登录-撤销更新的会员积分
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 撤销结果
     */
    @Override
    protected Map<String, Object> undoUpdateMemberPoint(Long userAccountId, Object parameter) throws Exception {
        return null;
    }

    /**
     * 首次登录-撤销更新会员等级
     * @param userAccountId 用户账号id
     * @param parameter 参数
     * @return 撤销结果
     */
    @Override
    protected Map<String, Object> undoUpdateMemberLevel(Long userAccountId, Object parameter) {
        return null;
    }
}
