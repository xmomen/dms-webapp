package com.xmomen.module.resource.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-4-10 23:26:20
 */
public
@Data
class ResourceQuery implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 包含主键集
     */
    private String[] ids;
    /**
     * 排除主键集
     */
    private String[] excludeIds;

    private String entityType;

    private String entityId;

}
