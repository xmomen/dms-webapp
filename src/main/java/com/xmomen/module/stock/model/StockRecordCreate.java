package com.xmomen.module.stock.model;

import lombok.Data;
import com.xmomen.module.stock.entity.StockRecord;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
    import java.lang.Integer;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-5-13 12:48:23
 * @version 1.0.0
 */
public @Data class StockRecordCreate implements Serializable {

    /** 主键 */
    private String id;
    /** 库存id */
    private String stockId;
    /** 变更数量 */
    private Integer changeNum;
    /** 1-增加 2-减少 */
    private Integer changType;
    /** 如果是订单变更，关联订单ID */
    private Integer refOrderId;
    /** 备注(记录变更） */
    private String remark;
    /** 创建人 */
    private Integer insertUserId;
    /** 创建时间 */
    private Date insertDate;
    /** 更新人 */
    private Integer updateUserId;
    /** 更新时间 */
    private Date updateDate;

    public StockRecord getEntity(){
        StockRecord stockRecord = new StockRecord();
        BeanUtils.copyProperties(this, stockRecord);
        return stockRecord;
    }
}
