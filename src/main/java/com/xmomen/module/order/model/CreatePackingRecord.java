package com.xmomen.module.order.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/21.
 */
public @Data class CreatePackingRecord implements Serializable {
    @NotNull
    private String upc;
    @NotNull
    private Integer orderItemId;
    private Integer packingId;
}
