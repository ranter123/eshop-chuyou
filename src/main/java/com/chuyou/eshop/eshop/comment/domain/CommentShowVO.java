package com.chuyou.eshop.eshop.comment.domain;

import com.chuyou.eshop.eshop.common.util.AbstractObject;

import java.util.List;

/**
 * 评论前台展示VO
 * @author zhonghuashishan
 *
 */
public class CommentShowVO extends AbstractObject {

	/**
	 * 商品id
	 */
	private Long goodsId;
	/**
	 * 评论统计信息
	 */
	private CommentAggregateVO aggregate;
	/**
	 * 评论列表
	 */
	private List<CommentInfoVO> comments;
	
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public CommentAggregateVO getAggregate() {
		return aggregate;
	}
	public void setAggregate(CommentAggregateVO aggregate) {
		this.aggregate = aggregate;
	}
	public List<CommentInfoVO> getComments() {
		return comments;
	}
	public void setComments(List<CommentInfoVO> comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return "CommentShowVO [goodsId=" + goodsId + ", aggregate=" + aggregate + ", comments=" + comments + "]";
	}
	
}
