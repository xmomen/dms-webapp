package com.xmomen.module.base.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeng on 2016/3/30.
 */
public class CouponModel implements Serializable {

    /**
     *
     */
    private Integer id;

    /**
     * 卡或者劵,1-卡，2-劵
     */
    private Integer couponType;

    private String couponTypeDesc;

    /**
     * 卡描述
     */
    private String couponDesc;

    /**
     * 卡号
     */
    private String couponNumber;

    /**
     * 卡值：可用金额,可用次数
     */
    private BigDecimal couponValue;

    /**
     * 密码
     */
    private String couponPassword;

    /**
     * 有效开始时间
     */
    private Date beginTime;

    /**
     * 有效结束时间
     */
    private Date endTime;

    /**
     * 0-未使用，1-已使用
     */
    private Integer isUsed;

    private String isUsedDesc;

    /**
     * 0-无效，1-有效
     */
    private Integer isUseful;

    private String isUsefulDesc;

    /**
     * 0-非赠送，1-赠送
     */
    private Integer isGift;

    /**
     *
     */
    private String isGiftDesc;
    /**
     *
     */
    private String notes;
}
