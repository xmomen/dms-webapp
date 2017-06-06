package com.xmomen.module.report.model;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 库存快照报表实体类
 */
public
@Data
class StockDailyReport implements Serializable {
    @Excel(name = "商品名称")
    private String itemName;
    @Excel(name = "商品编码")
    private String itemCode;
    /**
     * 昨日库存
     */
    @Excel(name = "历史库存")
    private Integer oldStockNum;
    /**
     * 入库库存
     */
    @Excel(name = "入库数")
    private Integer inNum;
    /**
     * 取消退货入库数
     */
    @Excel(name = "取消退货入库数")
    private Integer returnInNum;
    /**
     * 出库库存
     */
    @Excel(name = "出库数")
    private Integer outNum;
    /**
     * 破损数
     */
    @Excel(name = "破损数")
    private Integer damagedNum;
    /**
     * 核销数
     */
    @Excel(name = "核销数")
    private Integer verificationNum;
    /**
     * 现库存
     */
    @Excel(name = "结余库存")
    private Integer newStockNum;
}
