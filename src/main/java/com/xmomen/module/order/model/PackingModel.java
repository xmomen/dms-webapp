package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 16/4/6.
 */
public @Data
class PackingModel implements Serializable {

    private Integer id;
    private String orderNo;
    private String packingNo;
    private Date packingTime;
    private Integer packingStatus;
    private String packingStatusDesc;
}
