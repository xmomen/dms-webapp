package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/21.
 */
public @Data
class PackingOrderQuery implements Serializable {

    private Integer orderId;
    private String orderNo;
    private Integer orderItemId;
    private String keyword;
    private String itemCode;
    private String[] orderNos;
}
