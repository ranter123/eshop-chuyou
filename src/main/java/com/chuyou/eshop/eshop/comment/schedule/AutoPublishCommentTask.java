package com.chuyou.eshop.eshop.comment.schedule;

import com.chuyou.eshop.eshop.comment.service.CommentAggregateService;
import com.chuyou.eshop.eshop.comment.service.CommentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: ranter
 * @Date: 2021/4/27 8:57 上午
 * @Description: 自动发表评论的调度任务
 */
@Component
public class AutoPublishCommentTask {

    private static final Logger logger = LoggerFactory.getLogger(AutoPublishCommentTask.class);

    /**
     * 评论信息管理模块的service组件
     */
    @Autowired
    private CommentInfoService commentInfoService;

    /**
     * 评论统计信息管理模块的service组件
     */
    @Autowired
    private CommentAggregateService commentAggregateService;

    /**
     * 每隔10分钟检查一次
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void execute() {
        try {

        } catch (Exception e) {

        }
    }
}
