package com.xmomen.module.wb.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.member.entity.MemberAddress;

import lombok.Data;

public @Data class PcMemberAddressModel implements Serializable {

	@Length(max = 32, message = "字符长度限制[0,32]")
    private String id;
    /** 客户ID */
    @Range(max = 999999999, min = -999999999, message = "客户ID数值范围[999999999,-999999999]")
    private Integer cdMemberId;
    /**  */
    @Range(max = 999999999, min = -999999999, message = "数值范围[999999999,-999999999]")
    private Integer province;
    /** 城市 */
    @Range(max = 999999999, min = -999999999, message = "城市数值范围[999999999,-999999999]")
    private Integer city;
    /** 区域 */
    @Range(max = 999999999, min = -999999999, message = "区域数值范围[999999999,-999999999]")
    private Integer area;
    /** 详细地址 */
    @NotBlank(message = "详细地址为必填项")
    @Length(max = 256, message = "详细地址字符长度限制[0,256]")
    private String address;
    /** 完整地址 */
    @NotBlank(message = "完整地址为必填项")
    @Length(max = 256, message = "完整地址字符长度限制[0,256]")
    private String fullAddress;
    /**  */
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String name;
    /**  */
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String mobile;
    /** 是否默认地址 */
    private Boolean isDefault;

    /**
     * Get MemberAddress Entity Object
     * @return
     */
    @JsonIgnore
    public MemberAddress getEntity(){
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(this, memberAddress);
        return memberAddress;
    }
}
