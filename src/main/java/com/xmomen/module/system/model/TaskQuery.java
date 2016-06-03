package com.xmomen.module.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/30.
 */
public @Data
class TaskQuery implements Serializable{

    private String keyword;
    private Integer[] taskStatus;
    private Integer[] executorIds;

}
