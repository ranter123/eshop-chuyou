package com.chuyou.eshop.eshop.common.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: json组合表达式
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/8 19:43
 */
public class CompositionJsonExpression implements JsonExpression{

    /**
     * 当前节点名称
     */
    private String jsonNodeName;

    /**
     * json字表达式
     */
    private JsonExpression childJsonExpression;

    /**
     * 组合节点json表达式
     * @param context json上下文
     * @return
     */
    @Override
    public Object interpret(JsonExpressionContext context) {
        JSONObject currentNodeJson = context.getCurrentNodeJson();
        if (currentNodeJson == null) {
            currentNodeJson = context.getTargetJson();
            context.setCurrentNodeJson(currentNodeJson.getJSONObject(jsonNodeName));
        } else {
            context.setCurrentNodeJson(currentNodeJson.getJSONObject(jsonNodeName));
        }
        return childJsonExpression.interpret(context);
    }

    public String getJsonNodeName() {
        return jsonNodeName;
    }

    public void setJsonNodeName(String jsonNodeName) {
        this.jsonNodeName = jsonNodeName;
    }

    public JsonExpression getChildJsonExpression() {
        return childJsonExpression;
    }

    public void setChildJsonExpression(JsonExpression childJsonExpression) {
        this.childJsonExpression = childJsonExpression;
    }

    public CompositionJsonExpression(String jsonNodeName, JsonExpression childJsonExpression) {
        this.jsonNodeName = jsonNodeName;
        this.childJsonExpression = childJsonExpression;
    }

    public CompositionJsonExpression(String jsonNodeName) {
        this.jsonNodeName = jsonNodeName;
    }

    public CompositionJsonExpression(JsonExpression childJsonExpression) {
        this.childJsonExpression = childJsonExpression;
    }
}
