package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tanxinzheng on 16/6/27.
 */
public @Data
class RefundOrder implements Serializable{

	//订单号
    private String orderNo;
    //付款方式
    private Integer paymentMode;
    //其他付款方式
    private Integer otherPaymentMode;
    private String remark;

}
