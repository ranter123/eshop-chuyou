package com.chuyou.eshop.eshop.comment.dao.impl;

import com.chuyou.eshop.eshop.comment.dao.CommentAggregateDAO;
import com.chuyou.eshop.eshop.comment.domain.CommentAggregateDO;
import com.chuyou.eshop.eshop.comment.mapper.CommentAggregateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: ranter
 * @Date: 2021/4/7 10:18 下午
 * @Description: 评论统计模块DAO组件
 */
@Repository
public class CommentAggregateDAOImpl implements CommentAggregateDAO {

    @Autowired
    private CommentAggregateMapper commentAggregateMapper;

    /**
     * 根据商品ID查询评论统计信息
     * @param goodsId 商品ID
     * @return 评论统计信息
     */
    @Override
    public CommentAggregateDO getCommentAggregateByGoodsId(Long goodsId) throws Exception {
        return commentAggregateMapper.getCommentAggregateByGoodsId(goodsId);
    }

    /**
     * 新增评论统计信息
     * @param commentAggregateDO 评论统计信息DO对象
     * @throws Exception
     */
    @Override
    public void saveCommentAggregate(CommentAggregateDO commentAggregateDO) throws Exception {
        commentAggregateMapper.saveCommentAggregate(commentAggregateDO);
    }

    /**
     * 更新评论统计信息
     * @param commentAggregateDO
     * @throws Exception
     */
    @Override
    public void updateCommentAggregate(CommentAggregateDO commentAggregateDO) throws Exception {
        commentAggregateMapper.updateCommentAggregate(commentAggregateDO);
    }
}
