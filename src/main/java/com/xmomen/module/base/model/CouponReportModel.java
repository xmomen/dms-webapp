package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;

import lombok.Data;

/**
 */
public class CouponReportModel implements Serializable {

    /**
     * 卡或者劵,1-卡，2-劵
     */
	@Excel(name="卡劵",replace={"卡_1","劵_2"})
    private Integer couponType;
	
	@Excel(name="类型")
    private String categoryName;
	private Integer couponCategoryId;
    /**
     * 卡描述
     */
	@Excel(name="卡描述")
    private String couponDesc;

    /**
     * 卡号
     */
	@Excel(name="卡号")
    private String couponNumber;

    /**
     * 卡值：初始金额,初始次数
     */
	@Excel(name="初始金额")
    private BigDecimal couponValue;
    
    /**
     * 密码
     */
	@Excel(name="密码")
    private String couponPassword;

    /**
     * 是否预付款 1-预付款,2-后付款
     */
	@Excel(name="是否预付款",replace={"预付款_1","后付款_2"})
    private Integer paymentType;

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
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

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getCouponCategoryId() {
		return couponCategoryId;
	}

	public void setCouponCategoryId(Integer couponCategoryId) {
		this.couponCategoryId = couponCategoryId;
	}
	
}
