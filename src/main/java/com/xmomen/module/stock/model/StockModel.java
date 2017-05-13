package com.xmomen.module.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.stock.entity.Stock;
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
 * @date 2017-5-13 12:49:20
 */
@ExcelTarget(value = "StockModel")
public
@Data
class StockModel implements Serializable {

    /**
     * 主键
     */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /**
     * 商品ID
     */
    @Excel(name = "商品ID")
    @NotNull(message = "商品ID为必填项")
    @Range(max = 999999999, min = -999999999, message = "商品ID数值范围[999999999,-999999999]")
    private Integer itemId;
    private String itemName;
    private String itemCode;
    /**
     * 库存数
     */
    @Excel(name = "库存数")
    @NotNull(message = "库存数为必填项")
    @Range(max = 999999999, min = -999999999, message = "库存数数值范围[999999999,-999999999]")
    private Integer stockNum;
    /**
     * 预警数量
     */
    @Excel(name = "预警数量")
    @Range(max = 999999999, min = -999999999, message = "预警数量数值范围[999999999,-999999999]")
    private Integer warningNum;
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

    /**
     * Get Stock Entity Object
     *
     * @return
     */
    @JsonIgnore
    public Stock getEntity() {
        Stock stock = new Stock();
        BeanUtils.copyProperties(this, stock);
        return stock;
    }


}
