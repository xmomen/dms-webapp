package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tanxinzheng on 16/6/27.
 */
public @Data
class RefundOrder implements Serializable{

    private String orderNo;
    private BigDecimal amount;
    private String remark;

}
