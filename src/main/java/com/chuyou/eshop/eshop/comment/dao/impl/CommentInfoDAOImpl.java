package com.chuyou.eshop.eshop.comment.dao.impl;

import com.chuyou.eshop.eshop.comment.dao.CommentInfoDAO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoDO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoQuery;
import com.chuyou.eshop.eshop.comment.mapper.CommentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:09 下午
 * @Description: 评论管理模块DAO组件
 */
@Repository
public class CommentInfoDAOImpl implements CommentInfoDAO {

    private static final Logger logger = LoggerFactory.getLogger(CommentInfoDAOImpl.class);

    /**
     * 评论信息管理模块mapper组件
     */
    @Autowired
    private CommentInfoMapper commentInfoMapper;

    /**
     * 分页查询评论信息
     * @param query 评论信息查询条件
     * @return 评论信息
     */
    @Override
    public List<CommentInfoDO> listByPage(CommentInfoQuery query) {
        return commentInfoMapper.listByPage(query);
    }

    /**
     * 根据id查询评论信息
     * @param id 评论信息id
     * @return 评论信息
     */
    @Override
    public CommentInfoDO getById(Long id) {
        return commentInfoMapper.getById(id);
    }

    /**
     * 更新评论
     * @param comment 评论信息
     */
    @Override
    public void update(CommentInfoDO comment) {
        commentInfoMapper.update(comment);
    }

    /**
     * 删除评论
     * @param id 评论id
     */
    @Override
    public void remove(Long id) {
        commentInfoMapper.remove(id);
    }

    /**
     * 新增评论信息
     * @param commentInfoDO 评论信息DO对象
     * @throws Exception
     */
    @Override
    public Long saveCommentInfo(CommentInfoDO commentInfoDO) throws Exception {
        commentInfoMapper.saveCommentInfo(commentInfoDO);
        return commentInfoDO.getId();
    }
}
