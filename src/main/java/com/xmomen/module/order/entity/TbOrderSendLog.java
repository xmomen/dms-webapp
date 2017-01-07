package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_order_send_log")
public class TbOrderSendLog extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 原快递员ID
     */
    private Integer oldExpressMemberId;

    /**
     * 新快递员
     */
    private Integer newExpressMemberId;

    /**
     * 插入时间
     */
    private Date insertDate;

    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
        if(orderId == null){
              removeValidField("orderId");
              return;
        }
        addValidField("orderId");
    }

    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        if(orderNo == null){
              removeValidField("orderNo");
              return;
        }
        addValidField("orderNo");
    }

    @Column(name = "old_express_member_id")
    public Integer getOldExpressMemberId() {
        return oldExpressMemberId;
    }

    public void setOldExpressMemberId(Integer oldExpressMemberId) {
        this.oldExpressMemberId = oldExpressMemberId;
        if(oldExpressMemberId == null){
              removeValidField("oldExpressMemberId");
              return;
        }
        addValidField("oldExpressMemberId");
    }

    @Column(name = "new_express_member_id")
    public Integer getNewExpressMemberId() {
        return newExpressMemberId;
    }

    public void setNewExpressMemberId(Integer newExpressMemberId) {
        this.newExpressMemberId = newExpressMemberId;
        if(newExpressMemberId == null){
              removeValidField("newExpressMemberId");
              return;
        }
        addValidField("newExpressMemberId");
    }

    @Column(name = "insert_date")
    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
        if(insertDate == null){
              removeValidField("insertDate");
              return;
        }
        addValidField("insertDate");
    }
}