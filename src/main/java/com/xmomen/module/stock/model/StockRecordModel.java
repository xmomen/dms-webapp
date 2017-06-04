package com.xmomen.module.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.stock.entity.StockRecord;
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
 * @date 2017-5-13 12:48:23
 */
@ExcelTarget(value = "StockRecordModel")
public
@Data
class StockRecordModel implements Serializable {

    /**
     * 主键
     */
    @NotBlank(message = "主键为必填项")
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    private String id;
    /**
     * 库存id
     */
    @Excel(name = "库存id")
    @NotBlank(message = "库存id为必填项")
    @Length(max = 32, message = "库存id字符长度限制[0,32]")
    private String stockId;
    /**
     * 变更数量
     */
    @Excel(name = "变更数量")
    @NotBlank(message = "变更数量为必填项")
    @Range(max = 999999999, min = -999999999, message = "变更数量数值范围[999999999,-999999999]")
    private Integer changeNum;
    /**
     * 1-增加 2-减少
     */
    @Excel(name = "1-增加 2-减少")
    @NotBlank(message = "1-增加 2-减少为必填项")
    @Range(max = 999999999, min = -999999999, message = "1-增加 2-减少数值范围[999999999,-999999999]")
    private Integer changType;
    /**
     * 如果是订单变更，关联订单ID
     */
    @Excel(name = "如果是订单变更，关联订单ID")
    @Range(max = 999999999, min = -999999999, message = "如果是订单变更，关联订单ID数值范围[999999999,-999999999]")
    private Integer refOrderId;
    /**
     * 备注(记录变更）
     */
    @Excel(name = "备注(记录变更）")
    @Length(max = 128, message = "备注(记录变更）字符长度限制[0,128]")
    private String remark;
    /**
     * 创建人
     */
    @Excel(name = "创建人")
    @NotBlank(message = "创建人为必填项")
    @Range(max = 999999999, min = -999999999, message = "创建人数值范围[999999999,-999999999]")
    private Integer insertUserId;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    @NotBlank(message = "创建时间为必填项")
    private Date insertDate;
    /**
     * 更新人
     */
    @Excel(name = "更新人")
    @Range(max = 999999999, min = -999999999, message = "更新人数值范围[999999999,-999999999]")
    private Integer updateUserId;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    private Date updateDate;

    private String orderNo;
    private String itemName;
    private String itemCode;

    /**
     * Get StockRecord Entity Object
     *
     * @return
     */
    @JsonIgnore
    public StockRecord getEntity() {
        StockRecord stockRecord = new StockRecord();
        BeanUtils.copyProperties(this, stockRecord);
        return stockRecord;
    }


}
