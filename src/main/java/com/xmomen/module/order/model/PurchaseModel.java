package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jeng on 2016/4/25.
 */
public @Data
class PurchaseModel implements Serializable {
	
	private int purchaseId;
    private String purchaseCode;
    private Integer purchaseStatus;
    private String purchaseStatusDesc;
    private int cdItemId;
    private String itemName;
    private String itemCode;
    private String itemUnit;
    private BigDecimal totalItemQty;
    private BigDecimal totalWeight;
    private int distributeValue;
    private int packageValue;
    private int packingValue;
}
