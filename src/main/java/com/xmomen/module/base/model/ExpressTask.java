package com.xmomen.module.base.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 */
public @Data
class ExpressTask implements Serializable {

    /**
     * 订单号集合
     */
    @NotNull
    @NotEmpty
    private List<String> orderNos;
    /**
     * 快递商ID
     */
    @NotNull
    private Integer expressId;

}
