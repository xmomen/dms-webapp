package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tanxinzheng on 16/8/17.
 */
public @Data class ReturnOrder implements Serializable {

    private String orderNo;

    private List<Item> itemList;

    public static @Data class Item implements Serializable {
        private String itemCode;
        private Integer itemNumber;
    }
}
