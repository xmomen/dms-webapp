package com.xmomen.module.payrecord.model;

import lombok.Data;
import com.xmomen.module.payrecord.entity.PayRecord;
import org.springframework.beans.BeanUtils;

    import java.math.BigDecimal;
    import java.lang.String;
    import java.lang.Integer;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-4-26 22:45:12
 * @version 1.0.0
 */
public @Data class PayRecordUpdate implements Serializable {

    /**  */
    private String id;
    /**  */
    private String tradeNo;
    /**  */
    private Integer tradeType;
    /**  */
    private BigDecimal totalFee;
    /**  */
    private String openId;
    /**  */
    private String transactionId;
    /**  */
    private Date transactionTime;
    /**  */
    private Date completeTime;


    public PayRecord getEntity(){
        PayRecord payRecord = new PayRecord();
        BeanUtils.copyProperties(this, payRecord);
        return payRecord;
    }
}
