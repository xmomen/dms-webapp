package com.xmomen.module.member.model;

import lombok.Data;
import com.xmomen.module.member.entity.MemberAddress;
import org.springframework.beans.BeanUtils;

    import java.lang.Integer;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-3-28 12:53:37
 * @version 1.0.0
 */
public @Data class MemberAddressCreate implements Serializable {

    /**  */
    private String id;
    /** 客户ID */
    private Integer cdMemberId;
    /**  */
    private Integer province;
    /** 城市 */
    private Integer city;
    /** 区域 */
    private Integer area;
    /** 详细地址 */
    private String address;
    /** 完整地址 */
    private String fullAddress;
    /**  */
    private String name;
    /**  */
    private String mobile;
    /** 是否默认地址 */
    private Integer isDefalut;

    public MemberAddress getEntity(){
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(this, memberAddress);
        return memberAddress;
    }
}
