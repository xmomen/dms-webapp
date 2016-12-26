package com.xmomen.module.receipt.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 */
public @Data
class ReturnOrderQuery implements Serializable {
    private Integer id;
    private String orderNo;
    private String returnTimeStart;
    private String returnTimeEnd;
    private String keyword;
}
