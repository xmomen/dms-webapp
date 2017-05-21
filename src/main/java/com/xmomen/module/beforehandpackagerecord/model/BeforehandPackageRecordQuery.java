package com.xmomen.module.beforehandpackagerecord.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-18 23:36:38
 */
public
@Data
class BeforehandPackageRecordQuery implements Serializable {
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

    private Integer insertUserId;

}
