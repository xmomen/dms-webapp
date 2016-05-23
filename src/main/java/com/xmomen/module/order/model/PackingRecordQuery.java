package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 2016/5/21.
 */
public @Data
class PackingRecordQuery implements Serializable {
    /**
     * ID
     */
    private Integer id;

    private String keyword;

    private Integer orderItemId;

}
