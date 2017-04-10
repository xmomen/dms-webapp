package com.xmomen.module.member.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-4-10 23:05:38
 * @version 1.0.0
 */
public @Data class MemberAddressQuery implements Serializable {
    /** 主键 */
    private String id;
    /** 包含主键集 */
    private String[] ids;
    /** 排除主键集 */
    private String[] excludeIds;

}
