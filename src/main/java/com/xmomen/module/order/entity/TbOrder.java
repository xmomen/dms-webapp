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
@Table(name = "tb_order")
public class TbOrder extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 1-卡，2-劵，3-常规，4-活动
     */
    private Integer orderType;

    /**
     * 1-微信订单，2-商城订单，3-客服下单，4-采摘订单
     */
    private Integer orderSource;

    /**
     * 1-预付款，2-后付款，3-免费
     */
    private Integer paymentMode;

    /**
     * 附加付款方式
     */
    private Integer otherPaymentMode;

    /**
     * 客户代码
     */
    private String memberCode;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 交易状态：0-待支付，1-已支付
     */
    private Integer payStatus;

    /**
     * 收货人手机
     */
    private String consigneePhone;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人地址
     */
    private String consigneeAddress;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 运送方式 1-快递，2-自提
     */
    private Integer transportMode;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 预约时间
     */
    private Date appointmentTime;

    /**
     * 订单创建人ID
     */
    private Integer createUserId;

    /**
     * 发运快递
     */
    private Integer despatchExpressId;

    /**
     * 快递员ID
     */
    private Integer expressMemberId;

    /**
     * 折扣金额
     */
    private BigDecimal discountPrice;

    /**
     * 客户经理
     */
    private Integer managerId;

    /**
     * 单位ID
     */
    private Integer companyId;

    /**
     * 批次号
     */
    private String batchNo;

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

    @Column(name = "ORDER_TYPE")
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
        if(orderType == null){
              removeValidField("orderType");
              return;
        }
        addValidField("orderType");
    }

    @Column(name = "ORDER_SOURCE")
    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
        if(orderSource == null){
              removeValidField("orderSource");
              return;
        }
        addValidField("orderSource");
    }

    @Column(name = "PAYMENT_MODE")
    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
        if(paymentMode == null){
              removeValidField("paymentMode");
              return;
        }
        addValidField("paymentMode");
    }

    @Column(name = "OTHER_PAYMENT_MODE")
    public Integer getOtherPaymentMode() {
        return otherPaymentMode;
    }

    public void setOtherPaymentMode(Integer otherPaymentMode) {
        this.otherPaymentMode = otherPaymentMode;
        if(otherPaymentMode == null){
              removeValidField("otherPaymentMode");
              return;
        }
        addValidField("otherPaymentMode");
    }

    @Column(name = "MEMBER_CODE")
    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
        if(memberCode == null){
              removeValidField("memberCode");
              return;
        }
        addValidField("memberCode");
    }

    @Column(name = "ORDER_STATUS")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        if(orderStatus == null){
              removeValidField("orderStatus");
              return;
        }
        addValidField("orderStatus");
    }

    @Column(name = "PAY_STATUS")
    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
        if(payStatus == null){
              removeValidField("payStatus");
              return;
        }
        addValidField("payStatus");
    }

    @Column(name = "CONSIGNEE_PHONE")
    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
        if(consigneePhone == null){
              removeValidField("consigneePhone");
              return;
        }
        addValidField("consigneePhone");
    }

    @Column(name = "CONSIGNEE_NAME")
    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
        if(consigneeName == null){
              removeValidField("consigneeName");
              return;
        }
        addValidField("consigneeName");
    }

    @Column(name = "CONSIGNEE_ADDRESS")
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
        if(consigneeAddress == null){
              removeValidField("consigneeAddress");
              return;
        }
        addValidField("consigneeAddress");
    }

    @Column(name = "POSTCODE")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        if(postcode == null){
              removeValidField("postcode");
              return;
        }
        addValidField("postcode");
    }

    @Column(name = "TRANSPORT_MODE")
    public Integer getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(Integer transportMode) {
        this.transportMode = transportMode;
        if(transportMode == null){
              removeValidField("transportMode");
              return;
        }
        addValidField("transportMode");
    }

    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        if(remark == null){
              removeValidField("remark");
              return;
        }
        addValidField("remark");
    }

    @Column(name = "TOTAL_AMOUNT")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        if(totalAmount == null){
              removeValidField("totalAmount");
              return;
        }
        addValidField("totalAmount");
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if(createTime == null){
              removeValidField("createTime");
              return;
        }
        addValidField("createTime");
    }

    @Column(name = "APPOINTMENT_TIME")
    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
        if(appointmentTime == null){
              removeValidField("appointmentTime");
              return;
        }
        addValidField("appointmentTime");
    }

    @Column(name = "CREATE_USER_ID")
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
        if(createUserId == null){
              removeValidField("createUserId");
              return;
        }
        addValidField("createUserId");
    }

    @Column(name = "DESPATCH_EXPRESS_ID")
    public Integer getDespatchExpressId() {
        return despatchExpressId;
    }

    public void setDespatchExpressId(Integer despatchExpressId) {
        this.despatchExpressId = despatchExpressId;
        if(despatchExpressId == null){
              removeValidField("despatchExpressId");
              return;
        }
        addValidField("despatchExpressId");
    }

    @Column(name = "EXPRESS_MEMBER_ID")
    public Integer getExpressMemberId() {
        return expressMemberId;
    }

    public void setExpressMemberId(Integer expressMemberId) {
        this.expressMemberId = expressMemberId;
        if(expressMemberId == null){
              removeValidField("expressMemberId");
              return;
        }
        addValidField("expressMemberId");
    }

    @Column(name = "DISCOUNT_PRICE")
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
        if(discountPrice == null){
              removeValidField("discountPrice");
              return;
        }
        addValidField("discountPrice");
    }

    @Column(name = "MANAGER_ID")
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
        if(managerId == null){
              removeValidField("managerId");
              return;
        }
        addValidField("managerId");
    }

    @Column(name = "COMPANY_ID")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
        if(companyId == null){
              removeValidField("companyId");
              return;
        }
        addValidField("companyId");
    }

    @Column(name = "BATCH_NO")
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
        if(batchNo == null){
              removeValidField("batchNo");
              return;
        }
        addValidField("batchNo");
    }
}