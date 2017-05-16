package com.xmomen.module.stock.model;

import lombok.Data;
import com.xmomen.module.stock.entity.Stock;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
    import java.lang.Integer;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-5-13 12:49:20
 * @version 1.0.0
 */
public @Data class StockUpdate implements Serializable {

    /** 主键 */
    private String id;
    /** 商品ID */
    private Integer itemId;
    /** 库存数 */
    private Integer stockNum;
    /** 预警数量 */
    private Integer warningNum;
    /** 创建时间 */
    private Date insertDate;
    /** 创建人 */
    private Integer insertUserId;
    /** 更新时间 */
    private Date updateDate;
    /** 更新人 */
    private Integer updateUserId;


    public Stock getEntity(){
        Stock stock = new Stock();
        BeanUtils.copyProperties(this, stock);
        return stock;
    }
}
