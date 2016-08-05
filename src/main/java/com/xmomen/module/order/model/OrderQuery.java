package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 2016/5/19.
 */
public @Data
class OrderQuery implements Serializable {

    private Integer id;
    private String orderNo;
    private String[] orderNos;
    private String keyword;
    private Integer orderStatus;
    private Integer packingTaskStatus;
    private Integer packingTaskUserId;
    private Date packingTaskCreateTimeStart;
    private Date packingTaskCreateTimeEnd;
    private Integer createUserId;
    private boolean isHasPackingTaskUserId;
}
