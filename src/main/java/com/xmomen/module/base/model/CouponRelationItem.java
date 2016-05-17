package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jeng on 2016/5/16.
 */
public class CouponRelationItem implements Serializable{
    private Integer itemId;
    private BigDecimal itemNumber;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(BigDecimal itemNumber) {
        this.itemNumber = itemNumber;
    }
}
