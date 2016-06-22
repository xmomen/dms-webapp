package com.xmomen.module.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/13.
 */
public @Data
class ItemQuery implements Serializable{

    private Integer id;
    private String keyword;
    private Integer sellStatus;
    private Integer companyId;
    private Integer itemType;
    private String[] itemCodes;
    private Integer[] ids;
    private Integer[] excludeIds;

}
