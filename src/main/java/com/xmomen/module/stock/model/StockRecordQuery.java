package com.xmomen.module.stock.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-13 12:48:23
 */
public
@Data
class StockRecordQuery implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 包含主键集
     */
    private String[] ids;
    /**
     * 排除主键集
     */
    private String[] excludeIds;

    private String stockId;
    private Integer changeType;

    private String dailyDate;
}
