package com.xmomen.module.report.model;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tanxinzheng on 16/9/3.
 */
@ExcelTarget(value = "orderReport")
public @Data
class OrderReport implements Serializable {

    @Excel(name = "下单日期")
    private Date createDate;
    @Excel(name = "卡券号")
    private String cardNumber;
    @Excel(name = "单位")
    private String company;
    @Excel(name = "客户经理")
    private String manager;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "送货日期")
    private Date sendDate;
    @Excel(name = "收货人姓名")
    private String consigneeName;
    @Excel(name = "收货人电话")
    private String consigneePhone;
    @Excel(name = "收货人地址")
    private String consigneeAddress;
    @Excel(name = "金额")
    private BigDecimal amount;
    @Excel(name = "扣款方式")
    private String paymentMode;
    @Excel(name = "卡券扣款")
    private String cardPay;
    @Excel(name = "回馈")
    private String gift;
    @Excel(name = "pos")
    private String pos;
    @Excel(name = "物流代收")
    private String agent;

}
