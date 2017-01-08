package com.xmomen.module.report.model;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tanxinzheng on 16/9/3.
 */
@ExcelTarget(value = "orderReport")
public
@Data
class ExpressReport implements Serializable {

    @Excel(name = "物流公司")
    private String expressName;
    @Excel(name = "订单号", width = 20)
    private String orderNo;
    @Excel(name = "总箱数")
    private int totalBoxNum;
    @Excel(name = "出库时间", width = 20, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date outDate;
    @Excel(name = "收货时间", width = 20, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date shouHuoDate;
    @Excel(name = "二次配送")
    private int isTwoSend;
    @Excel(name = "正常配送")
    private int isNormal;
    @Excel(name = "异常配送")
    private int isNoNormal;
    @Excel(name = "物流代收款", width = 15)
    private BigDecimal expressAmount;
    @Excel(name = "损失承担")
    private BigDecimal returnAmount;

    private int isReject;
}
