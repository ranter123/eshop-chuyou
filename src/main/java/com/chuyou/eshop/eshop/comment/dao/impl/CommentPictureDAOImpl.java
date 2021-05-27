package com.chuyou.eshop.eshop.comment.dao.impl;

import com.chuyou.eshop.eshop.comment.dao.CommentPictureDAO;
import com.chuyou.eshop.eshop.comment.domain.CommentPictureDO;
import com.chuyou.eshop.eshop.comment.mapper.CommentPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ranter
 * @Date: 2021/4/7 10:15 下午
 * @Description: 评论嗮图管理模块DAO组件
 */
@Repository
public class CommentPictureDAOImpl implements CommentPictureDAO {

    @Autowired
    private CommentPictureMapper commentPictureMapper;

    /**
     * 根据评论信息id查询图片
     * @param commentId 评论信息id
     * @return 评论图片
     */
    @Override
    public List<CommentPictureDO> listByCommentId(Long commentId) {
        return commentPictureMapper.listByCommentId(commentId);
    }

    /**
     * 新增评论嗮图
     * @param commentPictureDO 评论晒图DO对象
     * @throws Exception
     * @return 评论晒图id
     */
    @Override
    public Long saveCommentPicture(CommentPictureDO commentPictureDO) throws Exception {
        commentPictureMapper.saveCommentPicture(commentPictureDO);
        return commentPictureDO.getId();
    }

    /**
     * 根据ID查询图片
     * @param id 评论图片ID
     * @return 评论图片
     * @throws Exception
     */
    @Override
    public CommentPictureDO getById(Long id) throws Exception {
        return commentPictureMapper.getById(id);
    }
}
