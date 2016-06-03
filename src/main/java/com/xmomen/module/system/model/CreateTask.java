package com.xmomen.module.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/31.
 */
public @Data
class CreateTask implements Serializable {

    private Integer taskHeadId;
    private Integer executorId;
}
