package com.xmomen.module.job.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class JobOperationLogModel implements Serializable{
	private Integer id;
	  /**
     * 操作的条码
     */
    private String barCode;
    /**
     * 操作的商品条码
     */
    private String itemCode;
    
    private String itemName;
    
    /**
     * 采摘人
     */
    private String caizaiUser;

    /**
     * 检测人
     */
    private String jianceUser;
    
    
    /**
     * 农残率
     */
    private String nongCanLv; 
}
