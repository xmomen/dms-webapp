package com.xmomen.module.receipt.model;

import java.util.Date;

import lombok.Data;


public @Data class ReceivingCodeRequestModel {
	/**
     * 主键
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 请求人
     */
    private String requestUser;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 请求人手机号
     */
    private String requestPhone;
    
    /**
     * 快递商名称
     */
    private String expressName;
    
    /**
     * 收货码
     */
    private String receivingCode;
}
