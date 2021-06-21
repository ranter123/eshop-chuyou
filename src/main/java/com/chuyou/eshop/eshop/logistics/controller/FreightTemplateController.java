package com.chuyou.eshop.eshop.logistics.controller;

import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import com.chuyou.eshop.eshop.logistics.domain.FreightTemplateDTO;
import com.chuyou.eshop.eshop.logistics.domain.FreightTemplateQuery;
import com.chuyou.eshop.eshop.logistics.domain.FreightTemplateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 运费模板管理controller组件
 * @author zhonghuashishan
 *
 */
@RestController
@RequestMapping("/logistics/freightTemplate")
public class FreightTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(
			FreightTemplateController.class);
	
	/**
	 * 运费模板管理service组件
	 */
	@Autowired
	private FreightTemplateService freightTemplateService;
	
	/**
	 * 新增运费模板
	 * @param freightTemplate 运费模板
	 */
	@PostMapping("/")
	public Boolean save(@RequestBody FreightTemplateVO freightTemplate) {
		try {
			freightTemplateService.save(freightTemplate.clone(FreightTemplateDTO.class));
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
	/**
	 * 分页查询运费模板
	 * @param query 运费模板查询条件
	 * @return 运费模板
	 */
	@GetMapping("/")
	public List<FreightTemplateVO> listByPage(FreightTemplateQuery query) {
		try {
			return ObjectUtils.convertList(
					freightTemplateService.listByPage(query), 
					FreightTemplateVO.class);
		} catch (Exception e) {
			logger.error("error", e); 
			return null;
		}
	}
	
	/**
	 * 根据id查询运费模板
	 * @param query 运费模板查询条件
	 * @return 运费模板
	 */
	@GetMapping("/{id}")
	public FreightTemplateVO getById(@PathVariable("id") Long id) {
		try {
			return freightTemplateService.getById(id).clone(FreightTemplateVO.class);
		} catch (Exception e) {
			logger.error("error", e);
			return null;
		}
	}
	
	/**
	 * 更新运费模板
	 * @param freightTemplate 运费模板
	 */
	@PutMapping("/{id}")
	public Boolean update(@RequestBody FreightTemplateVO freightTemplate) {
		try {
			freightTemplateService.update(freightTemplate.clone(FreightTemplateDTO.class));  
			return true;
		} catch (Exception e) {
			logger.error("error", e); 
			return false;
		}
	}
	
}
