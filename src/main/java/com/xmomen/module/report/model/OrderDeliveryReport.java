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
public @Data
class OrderDeliveryReport implements Serializable {

    @Excel(name = "下单日期" , exportFormat="yyyy-MM-dd", importFormat = "yyyy-MM-dd")
    private Date createTime;
    @Excel(name = "订单号",width = 20)
    private String orderNo;
    @Excel(name="快递商")
    private String expressName;
    @Excel(name = "收货人姓名",width = 15)
    private String consigneeName;
    @Excel(name = "收货人电话",width = 15)
    private String consigneePhone;
    @Excel(name = "收货人地址",width = 30)
    private String consigneeAddress;
    @Excel(name = "金额")
    private BigDecimal totalAmount;
    @Excel(name = "付款方式")
    private String paymentModeDesc;
    @Excel(name = "付款金额")
    private BigDecimal payAmount;
    @Excel(name = "附加付款方式")
    private String otherPaymentModeDesc;
    @Excel(name = "附加付款金额")
    private BigDecimal otherPayAmount;
    @Excel(name = "送货日期" , exportFormat="yyyy-MM-dd", importFormat = "yyyy-MM-dd")
    private Date appointmentTime;
    @Excel(name = "备注",width = 30)
    private String remark;
    private String orderStatusDesc;
    private int totalBoxNum;
    private int expressScanBoxNum;
}
