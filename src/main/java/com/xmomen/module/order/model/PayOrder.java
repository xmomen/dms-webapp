package com.xmomen.module.order.model;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/18.
 */
public class PayOrder implements Serializable {

    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
