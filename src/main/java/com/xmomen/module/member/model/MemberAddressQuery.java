package com.xmomen.module.member.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-3-23 14:57:22
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
