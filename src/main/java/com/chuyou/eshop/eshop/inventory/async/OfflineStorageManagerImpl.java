package com.chuyou.eshop.eshop.inventory.async;

import com.alibaba.fastjson.JSONObject;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.inventory.dao.StockUpdateMessageDAO;
import com.chuyou.eshop.eshop.inventory.domain.StockUpdateMessageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 离线存储管理组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/21 14:58
 */
@Component
public class OfflineStorageManagerImpl implements OfflineStorageManager{

    /**
     * 库存更新消息管理模块DAO组件
     */
    @Autowired
    private StockUpdateMessageDAO stockUpdateMessageDAO;

    /**
     * 是否触发离线存储的标识
     */
    private Boolean offline = false;

    /**
     * 离线存储更新库存消息
     * @param message 库存更新消息
     * @throws Exception
     */
    @Override
    public void store(StockUpdateMessage message) throws Exception {
        StockUpdateMessageDO stockUpdateMessageDO = createStockUpdateMessageDO(message);
        stockUpdateMessageDAO.save(stockUpdateMessageDO);
    }

    /**
     * 创建库存更新消息DO对象
     * @param message 库存更新消息
     * @return 库存更新消息DO对象
     */
    private StockUpdateMessageDO createStockUpdateMessageDO(StockUpdateMessage message) throws Exception {
        StockUpdateMessageDO stockUpdateMessageDO = new StockUpdateMessageDO();
        stockUpdateMessageDO.setMessageId(message.getId());
        stockUpdateMessageDO.setOperation(message.getOperation());
        stockUpdateMessageDO.setParameter(JSONObject.toJSONString(message.getParameter()));
        stockUpdateMessageDO.setParamterClazz(message.getParameter().getClass().getName());
        stockUpdateMessageDO.setGmtCreate(DateUtils.getCurrentTime());
        stockUpdateMessageDO.setGmtModified(DateUtils.getCurrentTime());
        return stockUpdateMessageDO;
    }

    /**
     * 获取离线存储标识
     * @return 离线存储标识
     * @throws Exception
     */
    @Override
    public Boolean getOffline() throws Exception {
        return offline;
    }

    /**
     * 设置离线存储标识
     * @param offline 离线存储标识
     * @throws Exception
     */
    @Override
    public void setOffline(Boolean offline) throws Exception {
        this.offline = offline;
    }

    /**
     * 获取迭代器
     * @return 离线存储迭代器
     * @throws Exception
     */
    @Override
    public OfflineStorageIterator iterator() throws Exception {
        return new OfflineStorageIteratorImpl();
    }

    /**
     * 批量删除库存更新消息
     * @param stockUpdateMessages 库存更新消息
     * @throws Exception
     */
    @Override
    public void removeByBatch(List<StockUpdateMessage> stockUpdateMessages) throws Exception {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < stockUpdateMessages.size(); i ++) {
            builder.append(stockUpdateMessages.get(i).getId());
            if (i < stockUpdateMessages.size() - 1) {
                builder.append(",");
            }
        }
        stockUpdateMessageDAO.removeByBatch(builder.toString());
    }


    /**
     * 离线数据迭代器
     */
    public class OfflineStorageIteratorImpl implements OfflineStorageIterator {

        /**
         * 判断是否还有下一批库存更新消息
         * @return 是否还有下一批库存更新消息
         * @throws Exception
         */
        @Override
        public Boolean hasNext() throws Exception {
            return stockUpdateMessageDAO.count().equals(0L) ? false : true;
        }

        /**
         * 获取下一批库存更新消息
         * @return 下一批库存更新消息
         * @throws Exception
         */
        @Override
        public List<StockUpdateMessage> next() throws Exception {
            List<StockUpdateMessage> stockUpdateMessages = new ArrayList<>();
            List<StockUpdateMessageDO> stockUpdateMessageDOs = stockUpdateMessageDAO.listByBatch();
            for (StockUpdateMessageDO stockUpdateMessageDO : stockUpdateMessageDOs) {
                StockUpdateMessage stockUpdateMessage = new StockUpdateMessage();
                stockUpdateMessage.setId(stockUpdateMessageDO.getMessageId());
                stockUpdateMessage.setOperation(stockUpdateMessageDO.getOperation());
                stockUpdateMessage.setParameter(JSONObject.parseObject(stockUpdateMessageDO.getParameter(),
                        Class.forName(stockUpdateMessageDO.getParamterClazz())));
                stockUpdateMessages.add(stockUpdateMessage);
            }
            return stockUpdateMessages;
        }
    }
}
