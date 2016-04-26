package com.xmomen.module.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 2016/4/25.
 */
public class CreatePurchase implements Serializable {

    private Date orderDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
