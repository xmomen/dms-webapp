package com.xmomen.module.stockdaily.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-26 21:56:22
 */
public
@Data
class StockDailyQuery implements Serializable {
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

    private String dailyDateStart;

    private String dailyDateEnd;

    private String keyword;

}
