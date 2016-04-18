package com.xmomen.module.order.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jeng on 16/4/5.
 */
public class CreateOrder implements Serializable {

    /**
     * 1-卡，2-劵，3-常规
     */
    @NotNull
    private Integer orderType;

    /**
     * 1-微信订单，2-商城订单，3-客服下单，4-采摘订单
     */
    private Integer orderSource;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户代码
     */
    private String memberCode;

    /**
     * 客户联系方式
     */
    private String phone;

    /**
     * 付款方式
     */
    @NotNull
    private Integer paymentMode;

    /**
     * 支付关系编号（卡号/券号）
     */
    private String paymentRalationNo;

    /**
     * 收货人手机
     */
    @NotNull
    @NotBlank
    private String consigneePhone;

    /**
     * 收货人姓名
     */
    @NotNull
    @NotBlank
    private String consigneeName;

    /**
     * 收货人地址
     */
    @NotNull
    @NotBlank
    private String consigneeAddress;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 折扣
     */
    private Integer discount;

    /**
     * 是否显示金额
     */
    private Integer isShowAmount;

    /**
     * 操作人
     */
    private Integer createUserId;

    @NotNull
    @NotEmpty
    private List<OrderItem> orderItemList;

    public static class OrderItem implements Serializable {
        @NotNull
        private Integer orderItemId;
        @NotNull
        private BigDecimal itemQty;

        public Integer getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(Integer orderItemId) {
            this.orderItemId = orderItemId;
        }

        public BigDecimal getItemQty() {
            return itemQty;
        }

        public void setItemQty(BigDecimal itemQty) {
            this.itemQty = itemQty;
        }
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getIsShowAmount() {
        return isShowAmount;
    }

    public void setIsShowAmount(Integer isShowAmount) {
        this.isShowAmount = isShowAmount;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getPaymentRalationNo() {
        return paymentRalationNo;
    }

    public void setPaymentRalationNo(String paymentRalationNo) {
        this.paymentRalationNo = paymentRalationNo;
    }
}
