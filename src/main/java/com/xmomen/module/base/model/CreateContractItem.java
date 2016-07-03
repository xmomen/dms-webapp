package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

public @Data class CreateContractItem implements Serializable{
	private Integer addType;//添加方式
    private Integer cdCategoryId;
    private String itemCode;
    /**
     * 1-固定价格，2-固定金额
     */
    private Integer contractType;

    /**
     * 产品合同
     */
    private Integer cdContractId;

    /**
     * 当合同适用范围为1的时候，每种产品要设定对应的合同价
     */
    private Integer cdItemId;
    
    /**
     * 合同折扣
     */
    private BigDecimal discount;
    /**
     * 合同价格
     */
    private BigDecimal contractValue;

}