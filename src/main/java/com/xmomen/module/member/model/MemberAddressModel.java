package com.xmomen.module.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.member.entity.MemberAddress;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Integer;
import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-3-28 12:53:37
 * @version 1.0.0
 */
@ExcelTarget(value = "MemberAddressModel")
public @Data class MemberAddressModel implements Serializable {

    /**  */
//    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String id;
    /** 客户ID */
    @Excel(name = "客户ID")
    @NotNull(message = "客户ID为必填项")
    @Range(max = 999999999, min = -999999999, message = "客户ID数值范围[999999999,-999999999]")
    private Integer cdMemberId;
    /**  */
    @Excel(name = "")
    @Range(max = 999999999, min = -999999999, message = "数值范围[999999999,-999999999]")
    private Integer province;
    /** 城市 */
    @Excel(name = "城市")
    @Range(max = 999999999, min = -999999999, message = "城市数值范围[999999999,-999999999]")
    private Integer city;
    /** 区域 */
    @Excel(name = "区域")
    @Range(max = 999999999, min = -999999999, message = "区域数值范围[999999999,-999999999]")
    private Integer area;
    /** 详细地址 */
    @Excel(name = "详细地址")
    @NotBlank(message = "详细地址为必填项")
    @Length(max = 256, message = "详细地址字符长度限制[0,256]")
    private String address;
    /** 完整地址 */
    @Excel(name = "完整地址")
    @NotBlank(message = "完整地址为必填项")
    @Length(max = 256, message = "完整地址字符长度限制[0,256]")
    private String fullAddress;
    /**  */
    @Excel(name = "")
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String name;
    /**  */
    @Excel(name = "")
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String mobile;
    /** 是否默认地址 */
    @Excel(name = "是否默认地址")
//    @Range(max = 999999999, min = -999999999, message = "是否默认地址数值范围[999999999,-999999999]")
    //@NotBlank(message = "是否默认地址为必填项")
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
