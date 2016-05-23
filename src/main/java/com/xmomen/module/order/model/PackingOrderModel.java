package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jeng on 2016/5/21.
 */
public @Data
class PackingOrderModel implements Serializable {

    private String orderItemId;
    private String packingNo;
    private String itemName;
    private String packingStatusDesc;
    private BigDecimal itemQty;
    private BigDecimal packedItemQty;

}
