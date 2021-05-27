package com.chuyou.eshop.eshop.auth.controller;

import com.chuyou.eshop.eshop.auth.domain.AccountDTO;
import com.chuyou.eshop.eshop.auth.domain.AccountQuery;
import com.chuyou.eshop.eshop.auth.domain.AccountVO;
import com.chuyou.eshop.eshop.auth.service.AccountService;
import com.chuyou.eshop.eshop.common.util.CloneDirection;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/1 7:25 下午
 * @Description: 账号管理controller组件
 */
@RestController
@RequestMapping("/auth/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * 账号管理service组件
     */
    @Autowired
    private AccountService accountService;

    /**
     * 分页查询条件
     * @param query 查询条件
     * @return 账号
     */
    @GetMapping("/")
    public List<AccountVO> listByPage(AccountQuery query) {
        try {
            List<AccountDTO> accounts = accountService.listByPage(query);
            List<AccountVO> resultAccounts = ObjectUtils.convertList(accounts, AccountVO.class);
            return resultAccounts;
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<AccountVO>();
        }
    }

    /**
     * 根据ID查询账号
     * @param id 角色ID
     * @return 角色
     */
    @GetMapping("/{id}")
    public AccountVO getById(@PathVariable("id") Long id) {
        try {
            AccountDTO account = accountService.getById(id);
            AccountVO resultAccount = account.clone(AccountVO.class);
            return resultAccount;
        } catch (Exception e) {
            logger.error("error", e);
            return new AccountVO();
        }
    }

    /**
     * 更新账号
     * @param accountVO 账号
     * @return 处理结果
     */
    @PutMapping("/{id}")
    public Boolean update(@RequestBody AccountVO accountVO) {
        try {
            AccountDTO accountDTO = accountVO.clone(AccountDTO.class, CloneDirection.FORWARD);
            accountService.update(accountDTO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 删除账号
     * @param id 账号ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Boolean update(@PathVariable("id") Long id) {
        try {
            accountService.remove(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 修改密码
     * @param accountVO 账号
     * @return 处理结果
     */
    @PutMapping("/password/{id}")
    public Boolean updatePassword(@RequestBody AccountVO accountVO) {
        try {
            accountService.updatePassword(accountVO.clone(AccountDTO.class));
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
