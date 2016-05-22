package com.xmomen.module.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jeng on 2016/4/25.
 */
public class PurchaseModel implements Serializable {
	
	private int purchaseId;
    private String purchaseCode;
    private int cdItemId;
    private String itemName;
    private String itemCode;
    private String itemUnit;
    private BigDecimal totalItemQty;
    private BigDecimal totalWeight;
    private int distributeValue;
    private int packageValue;
    private int packingValue;
    
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

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

	public int getDistributeValue() {
		return distributeValue;
	}

	public void setDistributeValue(int distributeValue) {
		this.distributeValue = distributeValue;
	}

	public int getPackageValue() {
		return packageValue;
	}

	public void setPackageValue(int packageValue) {
		this.packageValue = packageValue;
	}

	public int getPackingValue() {
		return packingValue;
	}

	public void setPackingValue(int packingValue) {
		this.packingValue = packingValue;
	}


	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getCdItemId() {
		return cdItemId;
	}

	public void setCdItemId(int cdItemId) {
		this.cdItemId = cdItemId;
	}
}
