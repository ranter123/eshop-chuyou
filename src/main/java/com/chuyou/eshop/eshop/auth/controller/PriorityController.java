package com.chuyou.eshop.eshop.auth.controller;

import com.chuyou.eshop.eshop.auth.domain.PriorityDTO;
import com.chuyou.eshop.eshop.auth.domain.PriorityVO;
import com.chuyou.eshop.eshop.auth.service.PriorityService;
import com.chuyou.eshop.eshop.auth.service.impl.Priority;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 8:24 上午
 * @Description: 权限管理controller组件
 */
@RestController
@RequestMapping("/auth/priority")
public class PriorityController {

    private static final Logger logger = LoggerFactory.getLogger(PriorityController.class);

    /**
     * 权限管理模块的service组件
     */
    @Autowired
    private PriorityService priorityService;

    /**
     * 查询根权限
     * @return 根权限集合
     */
    @GetMapping("/root")
    public List<PriorityVO> listRootPriorities() {
        try {
            List<PriorityDTO> priorityDTOs = priorityService.listRootPriorities();
            if (priorityDTOs == null) {
                priorityDTOs = new ArrayList<PriorityDTO>();
            }

            List<PriorityVO> priorityVOs = new ArrayList<>(priorityDTOs.size());
            for (PriorityDTO priorityDTO : priorityDTOs) {
                priorityVOs.add(convertPriorityDTO2VO(priorityDTO));
            }
            return priorityVOs;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new ArrayList<PriorityVO>();
    }

    /**
     * 将权限DTO对象转换为VO对象
     * @param priorityDTO 权限DTO
     * @return VO对象
     */
    private PriorityVO convertPriorityDTO2VO(PriorityDTO priorityDTO) throws Exception {
        PriorityVO priorityVO = priorityDTO.clone(PriorityVO.class);
        priorityVO.setGmtCreate(DateUtils.formatDateTime(priorityDTO.getGmtCreate()));
        priorityVO.setGmtModified(DateUtils.formatDateTime(priorityDTO.getGmtModified()));
        return priorityVO;
    }

    /**
     * 根据父权限id查询子权限
     * @return
     */
    @GetMapping("/child/{parentId}")
    public List<PriorityVO> listChildPriorities(@PathVariable("parentId") Long parentId) {
        try {
            List<PriorityDTO> priorityDTOs = priorityService.listChildPriorities(parentId);
            if (priorityDTOs == null) {
                priorityDTOs = new ArrayList<PriorityDTO>();
            }

            List<PriorityVO> priorityVOs = new ArrayList<>(priorityDTOs.size());
            for (PriorityDTO priorityDTO : priorityDTOs) {
                priorityVOs.add(convertPriorityDTO2VO(priorityDTO));
            }
            return priorityVOs;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new ArrayList<PriorityVO>();
    }

    /**
     * 根据id查询权限
     * @param id 权限ID
     * @return 权限
     */
    @GetMapping("/{id}")
    public PriorityVO getPriorityById(@PathVariable("id") Long id) {
        try {
            PriorityDTO priorityDTO = priorityService.getPriorityById(id);
            if (priorityDTO == null) {
                priorityDTO = new PriorityDTO();
            }
            return convertPriorityDTO2VO(priorityDTO);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return new PriorityVO();
    }

    /**
     * 查询账号被授权的权限数
     * @param accountId 账号ID
     * @return 权限树
     */
    @GetMapping("/authorized/tree/{accountId}")
    public List<Priority> listAuthorizedTree(@PathVariable("accountId") Long accountId) {
        try {
            return priorityService.listAuthorizedByAccountId(accountId);
        } catch (Exception e) {
            logger.error("error", e);
            return new ArrayList<Priority>();
        }
    }

    /**
     * 新增权限
     * @param priorityVO 权限VO对象
     * @return 返回结果
     */
    @PostMapping("/")
    public Boolean savePriority(@RequestBody PriorityVO priorityVO) {
        try {
            priorityService.savePriority(convertPriorityVO2DTO(priorityVO));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 将权限VO对象转换为DTO对象
     * @param priorityVO 权限VO对象
     * @return
     */
    private PriorityDTO convertPriorityVO2DTO(PriorityVO priorityVO) throws Exception {
        PriorityDTO priorityDTO = priorityVO.clone(PriorityDTO.class);
        if (priorityVO.getGmtCreate() != null) {
            priorityDTO.setGmtCreate(DateUtils.parseDateTime(priorityVO.getGmtCreate()));
        }
        if (priorityDTO.getGmtModified() != null) {
            priorityDTO.setGmtModified(DateUtils.parseDateTime(priorityVO.getGmtModified()));
        }
        return priorityDTO;
    }

    /**
     * 更新权限
     * @param priorityVO 权限DO对象
     * @return 返回结果
     */
    @PutMapping("/{id}")
    public Boolean updatePriority(@RequestBody PriorityVO priorityVO) {
        try {
            priorityService.updatePriority(convertPriorityVO2DTO(priorityVO));
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 删除权限
     * @param id 权限ID
     * @return 返回结果
     */
    @DeleteMapping("/{id}")
    public Boolean removePriority(@PathVariable("id") Long id) {
        try {
            priorityService.removePriority(id);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }
}
