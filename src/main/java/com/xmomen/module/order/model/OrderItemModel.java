package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 16/4/6.
 */
public @Data class OrderItemModel implements Serializable {


    /**
     * 订单编码
     */
    private String orderNo;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品编号
     */
    private String itemCode;

    /**
     * 订单商品数量
     */
    private String itemQty;

    /**
     * 已装箱数量
     */
    private Integer packedQty;

}
