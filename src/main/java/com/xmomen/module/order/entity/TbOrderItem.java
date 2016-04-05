package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.math.BigDecimal;
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
     * 订单ID
     */
    private Integer tbOrderId;

    /**
     * 订单编号
     */
    private String orderNo;

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
    private Integer itemQty;

    /**
     * 商品单位
     */
    private String itemUnit;

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

    @Column(name = "TB_ORDER_ID")
    public Integer getTbOrderId() {
        return tbOrderId;
    }

    public void setTbOrderId(Integer tbOrderId) {
        this.tbOrderId = tbOrderId;
        if(tbOrderId == null){
              removeValidField("tbOrderId");
              return;
        }
        addValidField("tbOrderId");
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
    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
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
}