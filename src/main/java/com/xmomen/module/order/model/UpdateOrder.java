package com.xmomen.module.order.model;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeng on 16/4/5.
 */
public @Data class UpdateOrder implements Serializable {

    private String orderNo;

    @NotNull
    private Integer id;
    /**
     * 1-卡，2-劵，3-常规
     */
    @NotNull
    private Integer orderType;
    
    private String batchNo;

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
     * 是否显示金额
     */
    private Integer isShowAmount;

    /**
     * 操作人
     */
    private Integer createUserId;

    /**
     * 单位
     */
    private Integer companyId;
    /**
     * 客户经理
     */
    private Integer managerId;
    /**
     * 折扣金额
     */
    private BigDecimal discountPrice;
    /**
     * 订单总金额（劵直接取,卡、常规订单后台在计算金额）
     */
    private BigDecimal totalPrice;

    private Date appointmentTime;
    @NotNull
    @NotEmpty
    private List<OrderItem> orderItemList;

    public static class OrderItem implements Serializable {
        @NotNull
        private String itemCode;
        @NotNull
        private Integer orderItemId;
        @NotNull
        private BigDecimal itemQty;

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

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
