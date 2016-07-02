package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

public @Data class CreateContract implements Serializable {
	/**
     * 合同名称
     */
    private String contractName;
	/**
     * 合同编号
     */
    private String contractCode;
	/**
     * 合同价的客户
     */
    private Integer cdMemberId;

    /**
     * 合同价的单位
     */
    private Integer cdCompanyId;

    /**
     * 1-部分产品，2-全部产品
     */
    private Integer scope;

    /**
     * 仅当适用范围为全部产品时适用
     */
    private BigDecimal contractPrice;

    /**
     * 合同开始时间
     */
    private Date beginTime;

    /**
     * 合同结束时间
     */
    private Date endTime;

    /**
     * 0-未审核，1-审核
     */
    private Integer isAuditor;
    

    /**
     * 合同明细
     */
    private List<CreateContractItem> contractItemList;

}
