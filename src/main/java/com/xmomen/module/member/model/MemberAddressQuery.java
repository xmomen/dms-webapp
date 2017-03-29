package com.xmomen.module.member.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-3-29 0:27:52
 */
public
@Data
class MemberAddressQuery implements Serializable {
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

    private String cdMemberId;

}
