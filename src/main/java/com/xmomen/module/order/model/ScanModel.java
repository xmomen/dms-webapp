package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 16/8/8.
 */
@Data
public class ScanModel implements Serializable {

    private String orderNo;
    private Integer taskStatus;
    /**
     *
     */
    private Integer id;

    /**
     * 商品条码
     */
    private String upc;

    /**
     * 订单商品ID
     */
    private Integer orderItemId;

    /**
     * 扫描时间
     */
    private Date scanTime;

    /**
     * 装箱ID
     */
    private Integer packingId;

}
