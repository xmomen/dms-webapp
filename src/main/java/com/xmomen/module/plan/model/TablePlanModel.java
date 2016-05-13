package com.xmomen.module.plan.model;

import java.io.Serializable;

public class TablePlanModel implements Serializable{
	 /**
     * 
     */
    private Integer id;

    /**
     * 餐桌计划
     */
    private Integer cdPlanId;
    private String planName;

    /**
     * 审核状态（0-未审核，1-审核通过）
     */
    private Integer auditStatus;
    
    private Integer isStop;

    /**
     * 客户编号
     */
    private String memberCode;

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
     * 单位
     */
    private String companyName;
    /**
     * 所属客服经理
     */
    private String managerName;
    
    /**
     * 总配送次数
     */
    private Integer totalSendValue;

    /**
     * 已配送次数
     */
    private Integer sendValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCdPlanId() {
		return cdPlanId;
	}

	public void setCdPlanId(Integer cdPlanId) {
		this.cdPlanId = cdPlanId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getIsStop() {
		return isStop;
	}

	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Integer getTotalSendValue() {
		return totalSendValue;
	}

	public void setTotalSendValue(Integer totalSendValue) {
		this.totalSendValue = totalSendValue;
	}

	public Integer getSendValue() {
		return sendValue;
	}

	public void setSendValue(Integer sendValue) {
		this.sendValue = sendValue;
	}
}
