package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 16/4/6.
 */
public @Data
class PackingModel implements Serializable {

    /**
     * 订单类型
     */
    private Integer orderType;
}
