package com.chuyou.eshop.eshop.common.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: json表达式上下文
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/8 19:39
 */
public class JsonExpressionContext {

    /**
     * 当前节点json
     */
    private JSONObject currentNodeJson;

    /**
     * 目标节点json
     */
    private JSONObject targetJson;

    public JsonExpressionContext(JSONObject targetJson) {
        this.targetJson = targetJson;
    }

    public JsonExpressionContext() {
    }

    public JsonExpressionContext(JSONObject currentNodeJson, JSONObject targetJson) {
        this.currentNodeJson = currentNodeJson;
        this.targetJson = targetJson;
    }

    public JSONObject getCurrentNodeJson() {
        return currentNodeJson;
    }

    public void setCurrentNodeJson(JSONObject currentNodeJson) {
        this.currentNodeJson = currentNodeJson;
    }

    public JSONObject getTargetJson() {
        return targetJson;
    }

    public void setTargetJson(JSONObject targetJson) {
        this.targetJson = targetJson;
    }
}
