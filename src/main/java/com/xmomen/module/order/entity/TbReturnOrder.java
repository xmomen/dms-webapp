package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_return_order")
public class TbReturnOrder extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 退货时间
     */
    private Date returnTime;

    /**
     * 订单退货状态(10-申请退货，11-退货中，14-退货运输中，15-退货完成）
     */
    private Integer returnStatus;

    /**
     * 审核状态（0-不同意，1-同意）
     */
    private Integer auditStatus;

    /**
     * 审核时间
     */
    private Date auditDate;

    /**
     * 审核人
     */
    private Integer auditUserId;

    /**
     * 拿货时间
     */
    private Date takeGoodsDate;

    /**
     * 拿货人
     */
    private Integer takeGoodsUserId;

    /**
     * 拿货人手机号
     */
    private String takeGoodsPhone;

    /**
     * 退款金额
     */
    private BigDecimal returnTotalAmount;

    @Column(name = "ID")
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

    @Column(name = "ORDER_NO")
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

    @Column(name = "RETURN_TIME")
    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
        if(returnTime == null){
              removeValidField("returnTime");
              return;
        }
        addValidField("returnTime");
    }

    @Column(name = "RETURN_STATUS")
    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
        if(returnStatus == null){
              removeValidField("returnStatus");
              return;
        }
        addValidField("returnStatus");
    }

    @Column(name = "AUDIT_STATUS")
    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
        if(auditStatus == null){
              removeValidField("auditStatus");
              return;
        }
        addValidField("auditStatus");
    }

    @Column(name = "AUDIT_DATE")
    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
        if(auditDate == null){
              removeValidField("auditDate");
              return;
        }
        addValidField("auditDate");
    }

    @Column(name = "AUDIT_USER_ID")
    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
        if(auditUserId == null){
              removeValidField("auditUserId");
              return;
        }
        addValidField("auditUserId");
    }

    @Column(name = "TAKE_GOODS_DATE")
    public Date getTakeGoodsDate() {
        return takeGoodsDate;
    }

    public void setTakeGoodsDate(Date takeGoodsDate) {
        this.takeGoodsDate = takeGoodsDate;
        if(takeGoodsDate == null){
              removeValidField("takeGoodsDate");
              return;
        }
        addValidField("takeGoodsDate");
    }

    @Column(name = "TAKE_GOODS_USER_ID")
    public Integer getTakeGoodsUserId() {
        return takeGoodsUserId;
    }

    public void setTakeGoodsUserId(Integer takeGoodsUserId) {
        this.takeGoodsUserId = takeGoodsUserId;
        if(takeGoodsUserId == null){
              removeValidField("takeGoodsUserId");
              return;
        }
        addValidField("takeGoodsUserId");
    }

    @Column(name = "TAKE_GOODS_PHONE")
    public String getTakeGoodsPhone() {
        return takeGoodsPhone;
    }

    public void setTakeGoodsPhone(String takeGoodsPhone) {
        this.takeGoodsPhone = takeGoodsPhone;
        if(takeGoodsPhone == null){
              removeValidField("takeGoodsPhone");
              return;
        }
        addValidField("takeGoodsPhone");
    }

    @Column(name = "RETURN_TOTAL_AMOUNT")
    public BigDecimal getReturnTotalAmount() {
        return returnTotalAmount;
    }

    public void setReturnTotalAmount(BigDecimal returnTotalAmount) {
        this.returnTotalAmount = returnTotalAmount;
        if(returnTotalAmount == null){
              removeValidField("returnTotalAmount");
              return;
        }
        addValidField("returnTotalAmount");
    }
}