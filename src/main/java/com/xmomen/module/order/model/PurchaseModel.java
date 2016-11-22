package com.xmomen.module.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Created by Jeng on 2016/4/25.
 */
public @Data
class PurchaseModel implements Serializable {
	
	private int purchaseId;
	@Excel(name="采购单号")
    private String purchaseCode;
    private Integer purchaseStatus;
    private String purchaseStatusDesc;
    private int cdItemId;
    @Excel(name="商品名称")
    private String itemName;
    private String itemCode;
    private String itemUnit;
    @Excel(name="采购总数")
    private BigDecimal totalItemQty;
    private BigDecimal totalWeight;
    private int distributeValue;
    private int packageValue;
    private int packingValue;
    private String sellUnit;
    private String spec;
    private Date createDate;
}
