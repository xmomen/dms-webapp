package com.xmomen.module.base.entity;

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
@Table(name = "cd_item")
public class CdItem extends BaseMybatisModel {
    /**
     * 
     */
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

    @Column(name = "ITEM_CODE")
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
        if(itemCode == null){
              removeValidField("itemCode");
              return;
        }
        addValidField("itemCode");
    }

    @Column(name = "CD_CATEGORY_ID")
    public Integer getCdCategoryId() {
        return cdCategoryId;
    }

    public void setCdCategoryId(Integer cdCategoryId) {
        this.cdCategoryId = cdCategoryId;
        if(cdCategoryId == null){
              removeValidField("cdCategoryId");
              return;
        }
        addValidField("cdCategoryId");
    }

    @Column(name = "ITEM_NAME")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        if(itemName == null){
              removeValidField("itemName");
              return;
        }
        addValidField("itemName");
    }

    @Column(name = "ITEM_DESCRIBE")
    public String getItemDescribe() {
        return itemDescribe;
    }

    public void setItemDescribe(String itemDescribe) {
        this.itemDescribe = itemDescribe;
        if(itemDescribe == null){
              removeValidField("itemDescribe");
              return;
        }
        addValidField("itemDescribe");
    }

    @Column(name = "ITEM_TYPE")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
        if(itemType == null){
              removeValidField("itemType");
              return;
        }
        addValidField("itemType");
    }

    @Column(name = "YIELDLY")
    public String getYieldly() {
        return yieldly;
    }

    public void setYieldly(String yieldly) {
        this.yieldly = yieldly;
        if(yieldly == null){
              removeValidField("yieldly");
              return;
        }
        addValidField("yieldly");
    }

    @Column(name = "SPEC")
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
        if(spec == null){
              removeValidField("spec");
              return;
        }
        addValidField("spec");
    }

    @Column(name = "BASE_PRICE")
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        if(basePrice == null){
              removeValidField("basePrice");
              return;
        }
        addValidField("basePrice");
    }

    @Column(name = "MEMBER_PRICE")
    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
        if(memberPrice == null){
              removeValidField("memberPrice");
              return;
        }
        addValidField("memberPrice");
    }

    @Column(name = "PRICING_MANNER")
    public String getPricingManner() {
        return pricingManner;
    }

    public void setPricingManner(String pricingManner) {
        this.pricingManner = pricingManner;
        if(pricingManner == null){
              removeValidField("pricingManner");
              return;
        }
        addValidField("pricingManner");
    }

    @Column(name = "SELL_STATUS")
    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
        if(sellStatus == null){
              removeValidField("sellStatus");
              return;
        }
        addValidField("sellStatus");
    }

    @Column(name = "SELL_UNIT")
    public String getSellUnit() {
        return sellUnit;
    }

    public void setSellUnit(String sellUnit) {
        this.sellUnit = sellUnit;
        if(sellUnit == null){
              removeValidField("sellUnit");
              return;
        }
        addValidField("sellUnit");
    }

    @Column(name = "SELL_PRICE")
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
        if(sellPrice == null){
              removeValidField("sellPrice");
              return;
        }
        addValidField("sellPrice");
    }

    @Column(name = "IS_COMBINATION")
    public Integer getIsCombination() {
        return isCombination;
    }

    public void setIsCombination(Integer isCombination) {
        this.isCombination = isCombination;
        if(isCombination == null){
              removeValidField("isCombination");
              return;
        }
        addValidField("isCombination");
    }

    @Column(name = "PARENT_ITEM_ID")
    public Integer getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(Integer parentItemId) {
        this.parentItemId = parentItemId;
        if(parentItemId == null){
              removeValidField("parentItemId");
              return;
        }
        addValidField("parentItemId");
    }

    @Column(name = "IS_AUDIT")
    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
        if(isAudit == null){
              removeValidField("isAudit");
              return;
        }
        addValidField("isAudit");
    }

    @Column(name = "CREATE_DATE_TIME")
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        if(createDateTime == null){
              removeValidField("createDateTime");
              return;
        }
        addValidField("createDateTime");
    }

    @Column(name = "CREATE_USER_CODE")
    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
        if(createUserCode == null){
              removeValidField("createUserCode");
              return;
        }
        addValidField("createUserCode");
    }
}