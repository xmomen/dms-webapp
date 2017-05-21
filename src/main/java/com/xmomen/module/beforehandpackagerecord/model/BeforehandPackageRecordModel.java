package com.xmomen.module.beforehandpackagerecord.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecord;
import lombok.Data;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import java.io.Serializable;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-18 23:36:38
 */
@ExcelTarget(value = "BeforehandPackageRecordModel")
public
@Data
class BeforehandPackageRecordModel implements Serializable {

    /**  */
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String id;
    /**
     * 包装商品
     */
    @Excel(name = "包装商品")
    @NotBlank(message = "包装商品为必填项")
    @Range(max = 999999999, min = -999999999, message = "包装商品数值范围[999999999,-999999999]")
    private Integer cdItemId;
    /**
     * 包装商品数
     */
    @Excel(name = "包装商品数")
    @NotBlank(message = "包装商品数为必填项")
    @Range(max = 999999999, min = -999999999, message = "包装商品数数值范围[999999999,-999999999]")
    private Integer packageNum;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    @NotBlank(message = "创建时间为必填项")
    private Date insertDate;
    /**
     * 创建人
     */
    @Excel(name = "创建人")
    @NotBlank(message = "创建人为必填项")
    @Range(max = 999999999, min = -999999999, message = "创建人数值范围[999999999,-999999999]")
    private Integer insertUserId;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    private Date updateDate;
    /**
     * 更新人
     */
    @Excel(name = "更新人")
    @Range(max = 999999999, min = -999999999, message = "更新人数值范围[999999999,-999999999]")
    private Integer updateUserId;

    private String itemCode;

    private String itemName;

    private String packageUserName;

    /**
     * Get BeforehandPackageRecord Entity Object
     *
     * @return
     */
    @JsonIgnore
    public BeforehandPackageRecord getEntity() {
        BeforehandPackageRecord beforehandPackageRecord = new BeforehandPackageRecord();
        BeanUtils.copyProperties(this, beforehandPackageRecord);
        return beforehandPackageRecord;
    }


}
