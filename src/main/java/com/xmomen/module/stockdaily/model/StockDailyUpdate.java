package com.xmomen.module.stockdaily.model;

import lombok.Data;
import com.xmomen.module.stockdaily.entity.StockDaily;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
    import java.lang.Integer;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-5-26 21:56:22
 * @version 1.0.0
 */
public @Data class StockDailyUpdate implements Serializable {

    /** 主键 */
    private String id;
    /**  */
    private String stockId;
    /**  */
    private String itemId;
    /** 昨日库存 */
    private Integer oldStockNum;
    /** 入库库存 */
    private Integer inNum;
    /** 取消退货入库数 */
    private Integer returnInNum;
    /** 出库库存 */
    private Integer outNum;
    /** 破损数 */
    private Integer damagedNum;
    /** 核销数 */
    private Integer verificationNum;
    /** 现库存 */
    private Integer newStockNum;
    /** 快照时间 */
    private Date dailyDate;
    /** 插入时间 */
    private Date insertDate;


    public StockDaily getEntity(){
        StockDaily stockDaily = new StockDaily();
        BeanUtils.copyProperties(this, stockDaily);
        return stockDaily;
    }
}
