package com.chuyou.eshop.eshop.customer.mapper;

import com.chuyou.eshop.eshop.customer.domain.ReturnGoodsWorksheetDO;
import com.chuyou.eshop.eshop.customer.domain.ReturnGoodsWorksheetQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: 退货工单管理mapper组件
 * @Author: jiangchuyou@banggood.com
 * @Date: 2021/6/5 14:44
 */
@Mapper
public interface ReturnGoodsWorksheetMapper {

    /**
     * 新增退货工单
     * @param worksheet 退货工单
     */
    @Insert("INSERT INTO customer_return_goods_worksheet("
            + "order_info_id,"
            + "order_no,"
            + "status,"
            + "return_goods_reason,"
            + "return_goods_remark,"
            + "return_goods_logistics_code,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{orderInfoId},"
            + "#{orderNo},"
            + "#{status},"
            + "#{returnGoodsReason},"
            + "#{returnGoodsRemark},"
            + "#{returnGoodsLogisticsCode},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    void save(ReturnGoodsWorksheetDO worksheet);

    /**
     * 分页查询退货工单
     * @param query 查询条件
     * @return 退货工单
     */
    @Select("<script>"

            + "SELECT "
            + "a.id,"
            + "a.order_info_id,"
            + "a.order_no,"
            + "a.status,"
            + "a.return_goods_reason,"
            + "a.return_goods_logistics_code,"
            + "a.gmt_create,"
            + "a.gmt_modified "
            + "FROM customer_return_goods_worksheet a,"
            + "("
            + "SELECT id "
            + "FROM customer_return_goods_worksheet "
            + "WHERE 1=1 "

            + "<if test='orderNo != null'>"
            + "AND order_no like '${orderNo}%' "
            + "</if>"

            + "<if test='status != null'>"
            + "AND status=#{status} "
            + "</if>"

            + "<if test='returnGoodsReason != null'>"
            + "AND return_goods_reason=#{returnGoodsReason} "
            + "</if>"

            + "<if test='returnGoodsLogisticsCode != null'>"
            + "AND return_goods_logistics_code like '${returnGoodsLogisticsCode}%' "
            + "</if>"

            + "LIMIT #{offset},#{size} "
            + ") b "
            + "WHERE a.id=b.id"

            + "</script>")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "order_info_id", property = "orderInfoId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "status", property = "status"),
            @Result(column = "return_goods_reason", property = "returnGoodsReason"),
            @Result(column = "return_goods_logistics_code", property = "returnGoodsLogisticsCode"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<ReturnGoodsWorksheetDO> listByPage(ReturnGoodsWorksheetQuery query);

    /**
     * 根据id查询退货工单
     * @param id 退货工单id
     * @return 退货工单
     */
    @Select("SELECT "
            + "id,"
            + "order_info_id,"
            + "order_no,"
            + "status,"
            + "return_goods_reason,"
            + "return_goods_remark,"
            + "return_goods_logistics_code,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM customer_return_goods_worksheet "
            + "WHERE id=#{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "order_info_id", property = "orderInfoId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "status", property = "status"),
            @Result(column = "return_goods_reason", property = "returnGoodsReason"),
            @Result(column = "return_goods_remark", property = "returnGoodsRemark"),
            @Result(column = "return_goods_logistics_code", property = "returnGoodsLogisticsCode"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    ReturnGoodsWorksheetDO getById(@Param("id") Long id);

    /**
     * 根据订单id查询退货工单
     * @param orderInfoId 订单id
     * @return 退货工单
     */
    @Select("SELECT "
            + "id,"
            + "order_info_id,"
            + "order_no,"
            + "status,"
            + "return_goods_reason,"
            + "return_goods_remark,"
            + "return_goods_logistics_code,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM customer_return_goods_worksheet "
            + "WHERE order_info_id=#{orderInfoId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "order_info_id", property = "orderInfoId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "status", property = "status"),
            @Result(column = "return_goods_reason", property = "returnGoodsReason"),
            @Result(column = "return_goods_remark", property = "returnGoodsRemark"),
            @Result(column = "return_goods_logistics_code", property = "returnGoodsLogisticsCode"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    ReturnGoodsWorksheetDO getByOrderInfoId(@Param("orderInfoId") Long orderInfoId);

    /**
     * 更新退货工单的状态
     * @param worksheet 退货工单
     */
    @Update("UPDATE customer_return_goods_worksheet SET "
            + "status=#{status},"
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void updateStatus(ReturnGoodsWorksheetDO worksheet);

    /**
     * 更新退货工单的退货物流单号
     * @param worksheet 退货工单
     */
    @Update("UPDATE customer_return_goods_worksheet SET "
            + "return_goods_logistics_code=#{returnGoodsLogisticsCode},"
            + "gmt_modified=#{gmtModified} "
            + "WHERE id=#{id}")
    void updateReturnGoodsLogisticsCode(ReturnGoodsWorksheetDO worksheet);
}
