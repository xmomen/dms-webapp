package com.xmomen.module.order.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeng on 16/5/25.
 */
public @Data
class PackingTask implements Serializable {

    /**
     * 订单号集合
     */
    @NotNull
    @NotEmpty
    private List<String> orderNos;
    /**
     * 装箱任务负责人ID
     */
    @NotNull
    private Integer packingTaskUserId;

}
