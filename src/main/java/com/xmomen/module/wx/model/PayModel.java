package com.xmomen.module.wx.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

public
@Data
class PayModel {

    /**
     * 订单编号，如卡号和订单号
     */
    String outTradeNo;
    /**
     * 订单金额，单位为元
     */
    @NotNull
    Double totalFee;
    /**
     * 微信用户openId
     */
    String openId;
    /**
     * 1 为支付
     * 2 为充值
     */
    @NotNull
    Integer type;
    
    @NotNull
    Integer memberId;
}
