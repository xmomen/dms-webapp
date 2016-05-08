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
@Table(name = "tb_purchase")
public class TbPurchase extends BaseMybatisModel {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 采购单编号
     */
    private String purchaseCode;

    /**
     * 产品代码
     */
    private String itemCode;

    /**
     * 采购总数量
     */
    private BigDecimal total;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 采购人员ID
     */
    private Integer buyerId;

    /**
     * 采购总重量
     */
    private BigDecimal totalWeight;

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

    @Column(name = "PURCHASE_CODE")
    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
        if(purchaseCode == null){
              removeValidField("purchaseCode");
              return;
        }
        addValidField("purchaseCode");
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

    @Column(name = "TOTAL")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
        if(total == null){
              removeValidField("total");
              return;
        }
        addValidField("total");
    }

    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        if(createDate == null){
              removeValidField("createDate");
              return;
        }
        addValidField("createDate");
    }

    @Column(name = "BUYER_ID")
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
        if(buyerId == null){
              removeValidField("buyerId");
              return;
        }
        addValidField("buyerId");
    }

    @Column(name = "TOTAL_WEIGHT")
    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
        if(totalWeight == null){
              removeValidField("totalWeight");
              return;
        }
        addValidField("totalWeight");
    }
}