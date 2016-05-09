package com.xmomen.module.base.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UpdatePlan implements Serializable {
	/**
     * 主键
     */
    private Integer id;

    /**
     * 计划名称
     */
    @NotBlank
    private String planName;

    /**
     * 计划创建人
     */
    private String createUser;

    /**
     * 计划创建时间
     */
    private Date createTime;

    /**
     * 配送频率
     */
    @NotNull
    private Integer deliveryType;

    /**
     * 配送时间(一周的星期几）
     */
    @NotBlank
    private String deliveryTime;

    /**
     * 配送的次数
     */
    @NotNull
    private Integer deliverCount;
    
    private List<PlanItemModel> planItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getDeliverCount() {
		return deliverCount;
	}

	public void setDeliverCount(Integer deliverCount) {
		this.deliverCount = deliverCount;
	}

	public List<PlanItemModel> getPlanItems() {
		return planItems;
	}

	public void setPlanItems(List<PlanItemModel> planItems) {
		this.planItems = planItems;
	}
}
