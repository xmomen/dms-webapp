package com.xmomen.module.base.model;

import com.xmomen.framework.mybatis.page.Page;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeng on 2016/3/30.
 */
public class CouponModel implements Serializable {

    /**
     *
     */
    private Integer id;

    /**
     * 卡或者劵,1-卡，2-劵
     */
    private Integer couponType;

    private String couponTypeDesc;
    
    private Integer couponCategory;

	private Integer memberId;
    
    private String categoryName;

    /**
     * 卡描述
     */
    private String couponDesc;

    /**
     * 卡号
     */
    private String couponNumber;

	/**
	 * 可用金额/可用次数
	 */
	private BigDecimal userPrice;

    /**
     * 卡值：初始金额,初始次数
     */
    private BigDecimal couponValue;

    /**
     * 密码
     */
    private String couponPassword;

    /**
     * 有效开始时间
     */
    private Date beginTime;

    /**
     * 有效结束时间
     */
    private Date endTime;

    /**
     * 0-未使用，1-已使用
     */
    private Integer isUsed;

    private String isUsedDesc;

    /**
     * 0-无效，1-有效
     */
    private Integer isUseful;
    /**
     * 0-无效，1-有效
     */
    private Integer isSend;
    /**
     * 发放单位
     */
    private String companyName;
    
    /**
     * 发放客户经理
     */
    private String managerName;
    
    private String consignmentName;
    
    private String consignmentPhone;
    
    private String consignmentAddress;
    
    private String receivedPrice;
    public Integer getIsSend() {
		return isSend;
	}
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	private String isUsefulDesc;

    /**
     * 0-非赠送，1-赠送
     */
    private Integer isGift;

    /**
     *
     */
    private String isGiftDesc;
    /**
     *
     */
    private String notes;

	private List<CouponRelationItem> relationItemList;

	public List<CouponRelationItem> getRelationItemList() {
		return relationItemList;
	}

	public void setRelationItemList(List<CouponRelationItem> relationItemList) {
		this.relationItemList = relationItemList;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public String getCouponTypeDesc() {
		return couponTypeDesc;
	}
	public void setCouponTypeDesc(String couponTypeDesc) {
		this.couponTypeDesc = couponTypeDesc;
	}
	public Integer getCouponCategory() {
		return couponCategory;
	}
	public void setCouponCategory(Integer couponCategory) {
		this.couponCategory = couponCategory;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCouponDesc() {
		return couponDesc;
	}
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	public String getCouponNumber() {
		return couponNumber;
	}
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
	public BigDecimal getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(BigDecimal couponValue) {
		this.couponValue = couponValue;
	}
	public String getCouponPassword() {
		return couponPassword;
	}
	public void setCouponPassword(String couponPassword) {
		this.couponPassword = couponPassword;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getIsUsed() {
		return isUsed;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	public String getIsUsedDesc() {
		return isUsedDesc;
	}
	public void setIsUsedDesc(String isUsedDesc) {
		this.isUsedDesc = isUsedDesc;
	}
	public Integer getIsUseful() {
		return isUseful;
	}
	public void setIsUseful(Integer isUseful) {
		this.isUseful = isUseful;
	}
	public String getIsUsefulDesc() {
		return isUsefulDesc;
	}
	public void setIsUsefulDesc(String isUsefulDesc) {
		this.isUsefulDesc = isUsefulDesc;
	}
	public Integer getIsGift() {
		return isGift;
	}
	public void setIsGift(Integer isGift) {
		this.isGift = isGift;
	}
	public String getIsGiftDesc() {
		return isGiftDesc;
	}
	public void setIsGiftDesc(String isGiftDesc) {
		this.isGiftDesc = isGiftDesc;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getConsignmentName() {
		return consignmentName;
	}
	public void setConsignmentName(String consignmentName) {
		this.consignmentName = consignmentName;
	}
	public String getConsignmentPhone() {
		return consignmentPhone;
	}
	public void setConsignmentPhone(String consignmentPhone) {
		this.consignmentPhone = consignmentPhone;
	}
	public String getConsignmentAddress() {
		return consignmentAddress;
	}
	public void setConsignmentAddress(String consignmentAddress) {
		this.consignmentAddress = consignmentAddress;
	}
	public String getReceivedPrice() {
		return receivedPrice;
	}
	public void setReceivedPrice(String receivedPrice) {
		this.receivedPrice = receivedPrice;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(BigDecimal userPrice) {
		this.userPrice = userPrice;
	}
}
