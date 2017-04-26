package com.xmomen.module.payrecord.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.payrecord.entity.PayRecord;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-4-26 22:45:12
 * @version 1.0.0
 */
@ExcelTarget(value = "PayRecordModel")
public @Data class PayRecordModel implements Serializable {

    /**  */
    @NotBlank(message = "为必填项")
    @Length(max = 80, message = "字符长度限制[0,80]")
    private String id;
    /**  */
    @Excel(name = "")
    @Length(max = 80, message = "字符长度限制[0,80]")
    private String tradeNo;
    /**  */
    @Excel(name = "")
    @NotBlank(message = "为必填项")
    @Range(max = 999999999, min = -999999999, message = "数值范围[999999999,-999999999]")
    private Integer tradeType;
    /**  */
    @Excel(name = "")
    @NotBlank(message = "为必填项")
    @DecimalMax(value = "9999999999.99", message = "数值范围[-9999999999.99,9999999999.99]")
    @DecimalMin(value = "-9999999999.99", message = "数值范围[-9999999999.99,9999999999.99]")
    private BigDecimal totalFee;
    /**  */
    @Excel(name = "")
    @Length(max = 100, message = "字符长度限制[0,100]")
    private String openId;
    /**  */
    @Excel(name = "")
    @Length(max = 100, message = "字符长度限制[0,100]")
    private String transactionId;
    /**  */
    @Excel(name = "")
    private Date transactionTime;
    /**  */
    @Excel(name = "")
    private Date completeTime;

    /**
    * Get PayRecord Entity Object
    * @return
    */
    @JsonIgnore
    public PayRecord getEntity(){
        PayRecord payRecord = new PayRecord();
        BeanUtils.copyProperties(this, payRecord);
        return payRecord;
    }


}
