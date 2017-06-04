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
    private double payAmount;
    private String otherPaymentMode;
    private double otherPayAmount;

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
    @Excel(name = "总金额")
    private double totalAmount;
    @Excel(name = "折扣金额")
    private double discountPrice;
    @Excel(name = "客户经理代收", width = 15, type = 10)
    private double managerAmount;
    @Excel(name = "物流代收", type = 10)
    private double expressAmount;
    @Excel(name = "卡类扣款", type = 10)
    private double couponAmount;
    @Excel(name = "券类扣款", type = 10)
    private double quanAmount;
    @Excel(name = "刷POS", type = 10)
    private double posAmount;
    @Excel(name = "转账", type = 10)
    private double zhuanZhanAmount;
    @Excel(name = "其它", type = 10)
    private double otherAmount;
    @Excel(name = "回馈", type = 10)
    private double huiKuiAmount;
    @Excel(name = "退货金额", type = 10)
    private double returnTotalAmount;
}
