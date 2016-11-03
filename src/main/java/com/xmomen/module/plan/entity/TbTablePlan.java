package com.xmomen.module.plan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

@Entity
@Table(name = "tb_table_plan")
public class TbTablePlan extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 餐桌计划
     */
    private Integer cdPlanId;

    /**
     * 审核状态（0-未审核，1-审核通过）
     */
    private Integer auditStatus;

    /**
     * 是否暂停 0-不暂停，1-暂停
     */
    private Integer isStop;

    /**
     * 
     */
    private Integer cdMemberId;

    /**
     * 客户编号
     */
    private String memberCode;

    /**
     * 卡号
     */
    private String couponNumber;

    /**
     * 手机号
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
     * 总配送次数
     */
    private Integer totalSendValue;

    /**
     * 已配送次数
     */
    private Integer sendValue;

    /**
     * 上次配送时间
     */
    private Date lastSendDate;

    /**
     * 生效时间
     */
    private Date beginTime;

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

    @Column(name = "CD_PLAN_ID")
    public Integer getCdPlanId() {
        return cdPlanId;
    }

    public void setCdPlanId(Integer cdPlanId) {
        this.cdPlanId = cdPlanId;
        if(cdPlanId == null){
              removeValidField("cdPlanId");
              return;
        }
        addValidField("cdPlanId");
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

    @Column(name = "IS_STOP")
    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
        if(isStop == null){
              removeValidField("isStop");
              return;
        }
        addValidField("isStop");
    }

    @Column(name = "CD_MEMBER_ID")
    public Integer getCdMemberId() {
        return cdMemberId;
    }

    public void setCdMemberId(Integer cdMemberId) {
        this.cdMemberId = cdMemberId;
        if(cdMemberId == null){
              removeValidField("cdMemberId");
              return;
        }
        addValidField("cdMemberId");
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

    @Column(name = "COUPON_NUMBER")
    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
        if(couponNumber == null){
              removeValidField("couponNumber");
              return;
        }
        addValidField("couponNumber");
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

    @Column(name = "TOTAL_SEND_VALUE")
    public Integer getTotalSendValue() {
        return totalSendValue;
    }

    public void setTotalSendValue(Integer totalSendValue) {
        this.totalSendValue = totalSendValue;
        if(totalSendValue == null){
              removeValidField("totalSendValue");
              return;
        }
        addValidField("totalSendValue");
    }

    @Column(name = "SEND_VALUE")
    public Integer getSendValue() {
        return sendValue;
    }

    public void setSendValue(Integer sendValue) {
        this.sendValue = sendValue;
        if(sendValue == null){
              removeValidField("sendValue");
              return;
        }
        addValidField("sendValue");
    }

    @Column(name = "LAST_SEND_DATE")
    public Date getLastSendDate() {
        return lastSendDate;
    }

    public void setLastSendDate(Date lastSendDate) {
        this.lastSendDate = lastSendDate;
        if(lastSendDate == null){
              removeValidField("lastSendDate");
              return;
        }
        addValidField("lastSendDate");
    }

    @Column(name = "BEGIN_TIME")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
        if(beginTime == null){
              removeValidField("beginTime");
              return;
        }
        addValidField("beginTime");
    }
}