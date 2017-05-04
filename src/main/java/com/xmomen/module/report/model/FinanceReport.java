package com.xmomen.module.report.model;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 物流报表实体类
 */
public
@Data
class FinanceReport implements Serializable {
    private int cdCompanyId;
    @Excel(name = "客户经理")
    private String managerName;
    @Excel(name = "单位/个人")
    private String companyName;
    private int cdUserId;
    //总张数
    @Excel(name = "总张数（预付款）", type = 10)
    private int beforeCouponCount;
    //总金额
    @Excel(name = "总金额（预付款）", type = 10)
    private double beforeTotalValue;
    //充值金额
    @Excel(name = "充值金额（预付款）", type = 10)
    private double beforeTotalRechargeValue;
    //已付
    @Excel(name = "已付（预付款）", type = 10)
    private double beforeTotalYesValue;
    // 未付
    @Excel(name = "未付（预付款）", type = 10)
    private double beforeTotalNoValue;
    // 余额
    @Excel(name = "余额（预付款）", type = 10)
    private double beforeUserTotalValue;
    // 回馈总金额
    @Excel(name = "回馈总金额（预付款）", type = 10)
    private double beforeGiftTotalValue;
    // 回馈剩余金额
    @Excel(name = "回馈剩余金额（预付款）", type = 10)
    private double beforeGiftUserTotalValue;

    // 总张数
    @Excel(name = "总张数（后付款）", type = 10)
    private int afterCouponCount;
    // 总金额
    @Excel(name = "总金额（后付款）", type = 10)
    private double afterTotalValue;
    // 充值金额
    @Excel(name = "充值金额（后付款）", type = 10)
    private double afterTotalRechargeValue;
    // 已付
    @Excel(name = "已付（后付款）", type = 10)
    private double afterTotalYesValue;
    // 未付
    @Excel(name = "未付（后付款）", type = 10)
    private double afterTotalNoValue;
    // 余额
    @Excel(name = "余额（后付款）", type = 10)
    private double afterUserTotalValue;
    // 回馈总金额（后付款）
    @Excel(name = "回馈总金额（后付款）", type = 10)
    private double afterGiftTotalValue;
    // 回馈剩余金额（后付款）
    @Excel(name = "回馈剩余金额（后付款）", type = 10)
    private double after_gift_user_total_value;
    //总单数
    @Excel(name = "总单数", type = 10)
    private int totalOrder;
    // 订单总金额
    @Excel(name = "订单总金额", type = 10)
    private double totalOrderAmount;
    //优惠总金额
    @Excel(name = "优惠总金额", type = 10)
    private double youhuiAmount;
    //回馈总金额
    @Excel(name = "回馈总金额", type = 10)
    private double giftOrderTotal;
    @Excel(name = "客户经理代收", type = 10)
    private double managerAmount;
    @Excel(name = "POS", type = 10)
    private double posAmount;
    @Excel(name = "转账", type = 10)
    private double zhuanAmount;
    @Excel(name = "物流代收", type = 10)
    private double expressAmount;
}
