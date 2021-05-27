package com.chuyou.eshop.eshop.comment.service.impl;

import com.chuyou.eshop.eshop.comment.constant.CommentType;
import com.chuyou.eshop.eshop.comment.constant.ShowPictures;
import com.chuyou.eshop.eshop.comment.dao.CommentAggregateDAO;
import com.chuyou.eshop.eshop.comment.domain.CommentAggregateDO;
import com.chuyou.eshop.eshop.comment.domain.CommentAggregateDTO;
import com.chuyou.eshop.eshop.comment.domain.CommentInfoDTO;
import com.chuyou.eshop.eshop.comment.service.CommentAggregateService;
import com.chuyou.eshop.eshop.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

/**
 * @Author: ranter
 * @Date: 2021/4/7 9:16 下午
 * @Description: 评论信息统计模块Service组件
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentAggregateServiceImpl implements CommentAggregateService {

    /**
     * 评论统计信息管理模块DAO组件
     */
    @Autowired
    private CommentAggregateDAO commentAggregateDAO;

    /**
     * 更新评论统计信息
     * @param commentInfoDTO 评论信息
     * @throws Exception
     * @return 处理结果
     */
    @Override
    public CommentAggregateDTO refreshCommentAggregate(CommentInfoDTO commentInfoDTO) throws Exception {
        CommentAggregateDO commentAggregateDO = commentAggregateDAO.getCommentAggregateByGoodsId(
                commentInfoDTO.getGoodsId());
        if (commentAggregateDO == null) {
            commentAggregateDO = saveCommentAggregate(commentInfoDTO);
        } else {
            updateCommentAggregate(commentInfoDTO, commentAggregateDO);
        }
        return commentAggregateDO.clone(CommentAggregateDTO.class);
    }

    /**
     * 根据商品ID查询评论统计信息
     * @param goodsId 商品ID
     * @return 评论统计信息
     * @throws Exception
     */
    @Override
    public CommentAggregateDTO getCommentAggregateByGoodsId(Long goodsId) throws Exception {
        return commentAggregateDAO.getCommentAggregateByGoodsId(goodsId).clone(CommentAggregateDTO.class);
    }

    /**
     * 更新评论统计信息
     * @param commentInfoDTO 评论信息
     * @param commentAggregateDO 评论统计信息
     */
    private void updateCommentAggregate(CommentInfoDTO commentInfoDTO,
                                        CommentAggregateDO commentAggregateDO) throws Exception {
        commentAggregateDO.setTotalCommentCount(commentAggregateDO.getTotalCommentCount() + 1L);

        if (commentInfoDTO.getCommentType().equals(CommentType.GOOD_COMMENT)) {
            commentAggregateDO.setGoodCommentCount(commentAggregateDO.getGoodCommentCount() + 1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
            commentAggregateDO.setMediumCommentCount(commentAggregateDO.getMediumCommentCount() + 1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.BAD_COMMENT)) {
            commentAggregateDO.setBadCommentCount(commentAggregateDO.getBadCommentCount() + 1L);
        }

        Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(
                (double)commentAggregateDO.getGoodCommentCount() / (double)commentAggregateDO.getTotalCommentCount()
        ));
        commentAggregateDO.setGoodCommentRate(goodCommentRate);

        if (ShowPictures.YES.equals(commentInfoDTO.getShowPictures())) {
            commentAggregateDO.setShowPicturesCommentCount(commentAggregateDO.getShowPicturesCommentCount() + 1L);
        }

        commentAggregateDO.setGmtModified(DateUtils.getCurrentTime());
        commentAggregateDAO.updateCommentAggregate(commentAggregateDO);
    }

    /**
     * 新增评论统计信息
     * @param commentInfoDTO 评论信息
     * @return
     */
    private CommentAggregateDO saveCommentAggregate(CommentInfoDTO commentInfoDTO) throws Exception {
        CommentAggregateDO commentAggregateDO = new CommentAggregateDO();
        commentAggregateDO.setGoodsId(commentInfoDTO.getGoodsId());
        commentAggregateDO.setTotalCommentCount(1L);
        commentAggregateDO.setGoodCommentCount(0L);
        commentAggregateDO.setMediumCommentCount(0L);
        commentAggregateDO.setBadCommentCount(0L);

        if (commentInfoDTO.getCommentType().equals(CommentType.GOOD_COMMENT)) {
            commentAggregateDO.setGoodCommentCount(1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.MEDIUM_COMMENT)) {
            commentAggregateDO.setMediumCommentCount(1L);
        } else if (commentInfoDTO.getCommentType().equals(CommentType.BAD_COMMENT)) {
            commentAggregateDO.setBadCommentCount(1L);
        }

        Double goodCommentRate = Double.valueOf(new DecimalFormat("#.00").format(
                (double)commentAggregateDO.getGoodCommentCount() / (double)commentAggregateDO.getTotalCommentCount()
        ));
        commentAggregateDO.setGoodCommentRate(goodCommentRate);

        if (ShowPictures.YES.equals(commentInfoDTO.getShowPictures())) {
            commentAggregateDO.setShowPicturesCommentCount(1L);
        }

        commentAggregateDO.setGmtCreate(DateUtils.getCurrentTime());
        commentAggregateDO.setGmtModified(DateUtils.getCurrentTime());

        commentAggregateDAO.saveCommentAggregate(commentAggregateDO);
        return commentAggregateDO;
    }
}
