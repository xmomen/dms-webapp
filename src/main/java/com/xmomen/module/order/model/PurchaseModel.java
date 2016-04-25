package com.xmomen.module.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jeng on 2016/4/25.
 */
public class PurchaseModel implements Serializable {

    private String purchaseCode;
    private String itemName;
    private String itemCode;
    private String itemUnit;
    private BigDecimal totalItemQty;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
}
