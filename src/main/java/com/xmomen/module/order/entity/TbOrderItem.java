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
@Table(name = "tb_order_item")
public class TbOrderItem extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 
     */
    private Integer itemId;

    /**
     * 商品编码
     */
    private String itemCode;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品单价
     */
    private BigDecimal itemPrice;

    /**
     * 商品数量
     */
    private BigDecimal itemQty;

    /**
     * 商品单位
     */
    private String itemUnit;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private Integer updateUserId;

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

    @Column(name = "ITEM_ID")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
        if(itemId == null){
              removeValidField("itemId");
              return;
        }
        addValidField("itemId");
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

    @Column(name = "ITEM_PRICE")
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
        if(itemPrice == null){
              removeValidField("itemPrice");
              return;
        }
        addValidField("itemPrice");
    }

    @Column(name = "ITEM_QTY")
    public BigDecimal getItemQty() {
        return itemQty;
    }

    public void setItemQty(BigDecimal itemQty) {
        this.itemQty = itemQty;
        if(itemQty == null){
              removeValidField("itemQty");
              return;
        }
        addValidField("itemQty");
    }

    @Column(name = "ITEM_UNIT")
    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
        if(itemUnit == null){
              removeValidField("itemUnit");
              return;
        }
        addValidField("itemUnit");
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

    @Column(name = "UPDATE_DATE")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        if(updateDate == null){
              removeValidField("updateDate");
              return;
        }
        addValidField("updateDate");
    }

    @Column(name = "UPDATE_USER_ID")
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
        if(updateUserId == null){
              removeValidField("updateUserId");
              return;
        }
        addValidField("updateUserId");
    }
}