package com.xmomen.module.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeng on 2016/5/21.
 */
public @Data
class PackingRecordModel implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 商品条码
     */
    private String upc;

    /**
     * 扫描时间
     */
    private Date scanTime;

    /**
     * 装箱ID
     */
    private Integer packingId;

    /**
     * 装箱编号
     */
    private String packingNo;

    /**
     * 商品名称
     */
    private String itemName;


}
