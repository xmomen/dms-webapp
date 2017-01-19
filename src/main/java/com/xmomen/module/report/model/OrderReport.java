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
public
@Data
class OrderReport implements Serializable {

    private String paymentMode;
    private BigDecimal payAmount;
    private String otherPaymentMode;
    private BigDecimal otherPayAmount;

    @Excel(name = "订单类型")
    private String orderType;
    @Excel(name = "订单号", width = 20)
    private String orderNo;
    @Excel(name = "客户姓名")
    private String consigneeName;
    @Excel(name = "电话", width = 15)
    private String consigneePhone;
    @Excel(name = "单位/个人")
    private String companyName;
    @Excel(name = "客户经理")
    private String managerName;
    @Excel(name = "物流公司")
    private String expressName;
    @Excel(name = "客户经理代收", width = 15)
    private BigDecimal managerAmount;
    @Excel(name = "物流代收")
    private BigDecimal expressAmount;
    @Excel(name = "卡类扣款")
    private BigDecimal couponAmount;
    @Excel(name = "券类扣款")
    private BigDecimal quanAmount;
    @Excel(name = "刷POS")
    private BigDecimal posAmount;
    @Excel(name = "转账")
    private BigDecimal zhuanZhanAmount;
    @Excel(name = "其它")
    private BigDecimal otherAmount;
    @Excel(name = "回馈")
    private BigDecimal huiKuiAmount;
    @Excel(name = "退货金额")
    private BigDecimal returnTotalAmount;
}
