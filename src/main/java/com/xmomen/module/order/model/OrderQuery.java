package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/19.
 */
public @Data
class OrderQuery implements Serializable {

    private Integer id;
    private String orderNo;
    private String keyword;
    private Integer orderStatus;
    private Integer packingTaskStatus;
    private Integer packingTaskUserId;
    private Integer createUserId;
}
