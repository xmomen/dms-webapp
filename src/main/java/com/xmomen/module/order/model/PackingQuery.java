package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeng on 16/4/6.
 */
public @Data
class PackingQuery implements Serializable {

    private String orderNo;
    private String keyword;
    private String packingStatus;
    private String packingNo;

}
