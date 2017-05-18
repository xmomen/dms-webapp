package com.xmomen.module.stock.model;

import com.xmomen.framework.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/5/17.
 */
@Data
public class StockChange extends BaseModel implements Serializable{

    private String stockId;
    private Integer type;//1-入库，2-破损，3-核销
    private Integer number;
    private Integer actionBy;

}
