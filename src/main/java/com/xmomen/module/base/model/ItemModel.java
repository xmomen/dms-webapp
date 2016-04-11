package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ItemModel implements Serializable {
	private Integer id;
	/**
     * 产品编号
     */
    private String itemCode;

    /**
     * 产品归属的类别
     */
    private Integer cdCategoryId;

    /**
     * 产品名称
     */
    private String itemName;

    /**
     * 产品描述
     */
    private String itemDescribe;

    /**
     * 产品类型
     */
    private String itemType;

    /**
     * 生产地
     */
    private String yieldly;

    /**
     * 产品规格
     */
    private String spec;

    /**
     * 基础价格
     */
    private BigDecimal basePrice;

    /**
     * 会员价格
     */
    private BigDecimal memberPrice;

    /**
     * 计价方式
     */
    private String pricingManner;

    /**
     * 0-下架 1-上架
     */
    private Integer sellStatus;

    /**
     * 销售单位
     */
    private String sellUnit;

    /**
     * 销售金额
     */
    private BigDecimal sellPrice;

    /**
     * 0-未组合，1-组合
     */
    private Integer isCombination;

    /**
     * 组合产品的父id
     */
    private Integer parentItemId;

    /**
     * 0-未审核，1-审核
     */
    private Integer isAudit;

    /**
     * 录入时间
     */
    private Date createDateTime;

    /**
     * 录入人
     */
    private String createUserCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Integer getCdCategoryId() {
		return cdCategoryId;
	}

	public void setCdCategoryId(Integer cdCategoryId) {
		this.cdCategoryId = cdCategoryId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescribe() {
		return itemDescribe;
	}

	public void setItemDescribe(String itemDescribe) {
		this.itemDescribe = itemDescribe;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getYieldly() {
		return yieldly;
	}

	public void setYieldly(String yieldly) {
		this.yieldly = yieldly;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public String getPricingManner() {
		return pricingManner;
	}

	public void setPricingManner(String pricingManner) {
		this.pricingManner = pricingManner;
	}

	public Integer getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(Integer sellStatus) {
		this.sellStatus = sellStatus;
	}

	public String getSellUnit() {
		return sellUnit;
	}

	public void setSellUnit(String sellUnit) {
		this.sellUnit = sellUnit;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Integer getIsCombination() {
		return isCombination;
	}

	public void setIsCombination(Integer isCombination) {
		this.isCombination = isCombination;
	}

	public Integer getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}
}
