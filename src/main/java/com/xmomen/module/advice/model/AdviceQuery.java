package com.xmomen.module.advice.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-5-14 20:05:05
 * @version 1.0.0
 */
public @Data class AdviceQuery implements Serializable {
    /** 主键 */
    private String id;
    /** 包含主键集 */
    private String[] ids;
    /** 排除主键集 */
    private String[] excludeIds;

}
