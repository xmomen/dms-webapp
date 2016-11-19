package com.xmomen.module.order.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by Jeng on 2016/4/25.
 */
public @Data class CreatePurchase implements Serializable {
    private Date orderDate;
    private Date appointmentTimeStart;
    private Date appointmentTimeEnd;
}
