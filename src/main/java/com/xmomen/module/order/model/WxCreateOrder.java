package com.xmomen.module.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.xmomen.module.order.model.CreateOrder.OrderItem;

import lombok.Data;

public @Data class WxCreateOrder implements Serializable {
	/**
     * 1-卡，2-劵，3-常规
     */
    @NotNull
    private Integer orderType = 0;
    
    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 1-微信订单，2-商城订单，3-客服下单，4-采摘订单
     */
    private Integer orderSource = 1;

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
    private Integer paymentMode;

    /**
     * 附加付款方式
     */
    private Integer otherPaymentMode;

    /**
     * 支付关系编号（卡号/券号）
     */
    private String paymentRelationNo;

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
     * 操作人
     */
    @NotNull
    private Integer createUserId;

    private Date appointmentTime;
    /**
     * 折扣金额
     */
    private BigDecimal discountPrice;
    /**
     * 订单总金额（劵直接取,卡、常规订单后台在计算金额）
     */
    private BigDecimal totalPrice;
    
    /**
     * 券的物品直接从后台获取
     */
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
}
