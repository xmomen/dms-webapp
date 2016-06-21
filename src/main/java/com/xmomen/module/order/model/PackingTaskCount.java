package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 16/6/13.
 */
public @Data
class PackingTaskCount implements Serializable {

    private Integer actorId;
    private String actorName;
    private Integer totalCount;
    private Integer readyCount;
    private Integer executingCount;
    private Integer finishedCount;

}
