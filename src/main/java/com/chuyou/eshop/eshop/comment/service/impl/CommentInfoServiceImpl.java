package com.chuyou.eshop.eshop.comment.service.impl;

import com.chuyou.eshop.eshop.comment.constant.CommentInfoScore;
import com.chuyou.eshop.eshop.comment.constant.CommentStatus;
import com.chuyou.eshop.eshop.comment.constant.CommentType;
import com.chuyou.eshop.eshop.comment.constant.DefaultComment;
import com.chuyou.eshop.eshop.comment.dao.CommentInfoDAO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoDO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoDTO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoQuery;
import com.chuyou.eshop.eshop.comment.service.CommentInfoService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import com.chuyou.eshop.eshop.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:08 下午
 * @Description: 评论管理模块service组件
 */
@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Autowired
    private CommentInfoDAO commentInfoDAO;

    /**
     * 分页查询评论信息
     * @param query 评论查询条件
     * @return 评论信息
     */
    @Override
    public List<CommentInfoDTO> listByPage(CommentInfoQuery query) throws Exception {
        List<CommentInfoDO> comments = commentInfoDAO.listByPage(query);
        List<CommentInfoDTO> resultComments = ObjectUtils.convertList(comments, CommentInfoDTO.class);
        return resultComments;
    }

    /**
     * 根据id查询评论信息
     * @param id 评论信息id
     * @return 评论信息
     */
    @Override
    public CommentInfoDTO getById(Long id) throws Exception {
        CommentInfoDO comment = commentInfoDAO.getById(id);
        CommentInfoDTO resultComment = comment.clone(CommentInfoDTO.class);
        return resultComment;
    }

    /**
     * 更新评论
     * @param comment 评论信息
     */
    @Override
    public void update(CommentInfoDTO comment) throws Exception {
        comment.setGmtModified(DateUtils.getCurrentTime());
        CommentInfoDO targetComment = comment.clone(CommentInfoDO.class);
        commentInfoDAO.update(targetComment);
    }

    /**
     * 删除评论
     * @param id 评论id
     */
    @Override
    public void remove(Long id) {
        commentInfoDAO.remove(id);
    }

    /**
     * 新增手动发表的评论信息
     * @param commentInfoDTO 评论信息DTO对象
     * @throws Exception
     */
    @Override
    public void saveManualPublishedCommentInfo(CommentInfoDTO commentInfoDTO) throws Exception {
        // 计算评论总分数
        Integer totalScore = Math.round((commentInfoDTO.getGoodsScore()
                + commentInfoDTO.getCustomerServiceScore()
                + commentInfoDTO.getLogisticsScore()) / 3);
        commentInfoDTO.setTotalScore(totalScore);

        // 设置是否为默认评论
        commentInfoDTO.setDefaultComment(DefaultComment.NO);

        // 设置评论的状态
        commentInfoDTO.setCommentStatus(CommentStatus.APPROVING);

        // 设置评论的类型
        Integer commentType = 0;
        if (totalScore >= CommentInfoScore.FOUR) {
            commentType = CommentType.GOOD_COMMENT;
        } else if (Objects.equals(totalScore, CommentInfoScore.THREE)) {
            commentType = CommentType.MEDIUM_COMMENT;
        } else if (totalScore > CommentInfoScore.ZERO && totalScore <= CommentInfoScore.TWO) {
            commentType = CommentType.BAD_COMMENT;
        }

        commentInfoDTO.setCommentType(commentType);

        // 设置创建时间和修改时间
        commentInfoDTO.setGmtCreate(DateUtils.getCurrentTime());
        commentInfoDTO.setGmtModified(DateUtils.getCurrentTime());

        // 将评论信息保存到数据库中去
        CommentInfoDO commentInfoDO = commentInfoDTO.clone(CommentInfoDO.class);
        commentInfoDAO.saveCommentInfo(commentInfoDO);

        // 设置评论信息的id
        commentInfoDTO.setId(commentInfoDO.getId());
    }
}
