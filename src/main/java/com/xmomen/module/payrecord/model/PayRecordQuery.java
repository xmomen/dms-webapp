package com.xmomen.module.payrecord.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-4-26 22:45:12
 * @version 1.0.0
 */
public @Data class PayRecordQuery implements Serializable {
    /** 主键 */
    private String id;
    /** 包含主键集 */
    private String[] ids;
    /** 排除主键集 */
    private String[] excludeIds;

}
