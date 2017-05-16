package com.xmomen.module.advice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.advice.entity.Advice;
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
 * @date 2017-5-14 20:05:05
 */
@ExcelTarget(value = "AdviceModel")
public
@Data
class AdviceModel implements Serializable {

    /**
     * 主键
     */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /**
     * 标题
     */
    @Excel(name = "标题")
    @NotBlank(message = "标题为必填项")
    @Length(max = 128, message = "标题字符长度限制[0,128]")
    private String title;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private Date insertDate;
    /**
     * 创建人
     */
    @Excel(name = "创建人")
    @Range(max = 999999999, min = -999999999, message = "创建人数值范围[999999999,-999999999]")
    private Integer insertUserId;

    private String insertUser;
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

    private String updateUser;
    /**
     * 内容
     */
    @Excel(name = "内容")
    @Length(max = 65535, message = "内容字符长度限制[0,65,535]")
    private String content;

    /**
     * Get Advice Entity Object
     *
     * @return
     */
    @JsonIgnore
    public Advice getEntity() {
        Advice advice = new Advice();
        BeanUtils.copyProperties(this, advice);
        return advice;
    }


}
