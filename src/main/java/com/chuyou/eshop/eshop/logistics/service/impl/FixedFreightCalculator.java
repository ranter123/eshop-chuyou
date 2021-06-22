package com.chuyou.eshop.eshop.logistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chuyou.eshop.eshop.common.json.JsonExtractor;
import com.chuyou.eshop.eshop.logistics.domain.FreightTemplateDTO;
import com.chuyou.eshop.eshop.order.domain.OrderInfoDTO;
import com.chuyou.eshop.eshop.order.domain.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 固定运费计算器
 * @author zhonghuashishan
 *
 */
@Component
public class FixedFreightCalculator implements FreightCalculator {
	
	@Autowired
	private JsonExtractor jsonExtractor;

	/**
	 * 计算订单条目的运费
	 * @param freightTemplate 运费模板
	 * @param order 订单
	 * @param orderItem 订单条目
	 * @return 运费
	 * @throws Exception
	 */
	@Override
	public Double calculate(FreightTemplateDTO freightTemplate,
							OrderInfoDTO order, OrderItemDTO orderItem) throws Exception {
		JSONObject rule = JSONObject.parseObject(freightTemplate.getRule());
		return jsonExtractor.getDouble(rule, "fixed_freight"); 
	}
	
}
