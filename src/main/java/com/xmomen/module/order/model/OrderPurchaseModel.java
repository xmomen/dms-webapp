package com.xmomen.module.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jeng on 2016/4/25.
 */
public class OrderPurchaseModel implements Serializable {

    private String orderNo;
    private Integer itemId;
    private String itemCode;
    private String itemUnit;
    private BigDecimal totalItemQty;
    private BigDecimal totalWeight;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public BigDecimal getTotalItemQty() {
        return totalItemQty;
    }

    public void setTotalItemQty(BigDecimal totalItemQty) {
        this.totalItemQty = totalItemQty;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }
}
