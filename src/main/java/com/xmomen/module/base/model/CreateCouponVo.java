package com.xmomen.module.base.model;

import java.io.Serializable;

import lombok.Data;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Created by Jeng on 2016/3/30.
 */
public @Data class CreateCouponVo implements Serializable {

    @Excel(name="客户关联号")
    private String couponType;
    @Excel(name="客户关联号")
    private String couponCategory;
    @Excel(name="卡号")
    private String couponNumber;
    @Excel(name="密码")
    private String couponPassword;
    @Excel(name="金额")
    private double couponValue;
}
