package com.chuyou.eshop.eshop.comment.dao;

import com.chuyou.eshop.eshop.comment.domain.CommentAggregateDO;

/**
 * @Author: ranter
 * @Date: 2021/4/7 10:16 下午
 * @Description: 评论统计信息管理模块DAO组件
 */
public interface CommentAggregateDAO {

    /**
     * 根据商品ID查询评论统计信息
     * @param goodsId 商品ID
     * @return 评论统计信息
     */
    CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId) throws Exception;

    /**
     * 新增评论统计信息
     * @param commentAggregateDO 评论统计信息DO对象
     * @throws Exception
     */
    void saveCommentAggregate(CommentAggregateDO commentAggregateDO) throws Exception;

    /**
     * 更新评论统计信息
     * @param commentAggregateDO 评论统计DO对象
     * @throws Exception
     */
    void updateCommentAggregate(CommentAggregateDO commentAggregateDO) throws Exception;
}
