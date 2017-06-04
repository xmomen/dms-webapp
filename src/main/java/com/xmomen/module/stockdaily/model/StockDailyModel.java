package com.xmomen.module.stockdaily.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.stockdaily.entity.StockDaily;
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
 * @date 2017-5-26 21:56:23
 */
@ExcelTarget(value = "StockDailyModel")
public
@Data
class StockDailyModel implements Serializable {

    /**
     * 主键
     */
    @NotBlank(message = "主键为必填项")
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /**  */
    @Excel(name = "")
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String stockId;
    /**  */
    @Excel(name = "")
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String itemId;
    /**
     * 昨日库存
     */
    @Excel(name = "昨日库存")
    @Range(max = 999999999, min = -999999999, message = "昨日库存数值范围[999999999,-999999999]")
    private Integer oldStockNum;
    /**
     * 入库库存
     */
    @Excel(name = "入库库存")
    @NotBlank(message = "入库库存为必填项")
    @Range(max = 999999999, min = -999999999, message = "入库库存数值范围[999999999,-999999999]")
    private Integer inNum;
    /**
     * 取消退货入库数
     */
    @Excel(name = "取消退货入库数")
    @NotBlank(message = "取消退货入库数为必填项")
    @Range(max = 999999999, min = -999999999, message = "取消退货入库数数值范围[999999999,-999999999]")
    private Integer returnInNum;
    /**
     * 出库库存
     */
    @Excel(name = "出库库存")
    @NotBlank(message = "出库库存为必填项")
    @Range(max = 999999999, min = -999999999, message = "出库库存数值范围[999999999,-999999999]")
    private Integer outNum;
    /**
     * 破损数
     */
    @Excel(name = "破损数")
    @NotBlank(message = "破损数为必填项")
    @Range(max = 999999999, min = -999999999, message = "破损数数值范围[999999999,-999999999]")
    private Integer damagedNum;
    /**
     * 核销数
     */
    @Excel(name = "核销数")
    @NotBlank(message = "核销数为必填项")
    @Range(max = 999999999, min = -999999999, message = "核销数数值范围[999999999,-999999999]")
    private Integer verificationNum;
    /**
     * 现库存
     */
    @Excel(name = "现库存")
    @NotBlank(message = "现库存为必填项")
    @Range(max = 999999999, min = -999999999, message = "现库存数值范围[999999999,-999999999]")
    private Integer newStockNum;
    /**
     * 快照时间
     */
    @Excel(name = "快照时间")
    @NotBlank(message = "快照时间为必填项")
    private Date dailyDate;
    /**
     * 插入时间
     */
    @Excel(name = "插入时间")
    @NotBlank(message = "插入时间为必填项")
    private Date insertDate;

    private String itemName;

    private String itemCode;

    /**
     * Get StockDaily Entity Object
     *
     * @return
     */
    @JsonIgnore
    public StockDaily getEntity() {
        StockDaily stockDaily = new StockDaily();
        BeanUtils.copyProperties(this, stockDaily);
        return stockDaily;
    }


}
