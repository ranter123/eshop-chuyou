package com.chuyou.eshop.eshop.common.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: 叶子节点json表达式
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/8 19:43
 */
public class LeftJsonExpression implements JsonExpression {

    /**
     * 当前json节点名称
     */
    private String jsonNodeName;

    public LeftJsonExpression(String jsonNodeName) {
        this.jsonNodeName = jsonNodeName;
    }

    /**
     * 叶子节点json表达式
     * @param context json上下文
     * @return
     */
    @Override
    public Object interpret(JsonExpressionContext context) {
        JSONObject currentJsonNode = context.getCurrentNodeJson();
        if (currentJsonNode == null) {
            return context.getTargetJson().get(jsonNodeName);
        } else {
            return currentJsonNode.get(jsonNodeName);
        }
    }
}
