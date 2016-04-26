package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ActivityModel implements Serializable {
	private Integer id;
	/**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动开始时间
     */
    private Date activityBeginTime;

    /**
     * 活动结束时间
     */
    private Date activityEndTime;

    /**
     * 活动介绍
     */
    private String activityDescribe;

    /**
     * 参与活动的卡类型
     */
    private Integer activityType;
    
    private BigDecimal lowestPrice;
    
    private Integer couponCategory;
    /**
     * 活动下单时间
     */
    private Integer activityDay;

    /**
     * 是否启用（0-不启用，1-启用）
     */
    private Integer available;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getActivityBeginTime() {
		return activityBeginTime;
	}

	public void setActivityBeginTime(Date activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public String getActivityDescribe() {
		return activityDescribe;
	}

	public void setActivityDescribe(String activityDescribe) {
		this.activityDescribe = activityDescribe;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Integer getActivityDay() {
		return activityDay;
	}

	public void setActivityDay(Integer activityDay) {
		this.activityDay = activityDay;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public Integer getCouponCategory() {
		return couponCategory;
	}

	public void setCouponCategory(Integer couponCategory) {
		this.couponCategory = couponCategory;
	}

}
