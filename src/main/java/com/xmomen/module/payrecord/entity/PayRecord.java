package com.xmomen.module.payrecord.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_pay_record")
public class PayRecord extends BaseMybatisModel {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String tradeNo;

    /**
     * 
     */
    private Integer tradeType;

    /**
     * 
     */
    private BigDecimal totalFee;

    /**
     * 
     */
    private String openId;

    /**
     * 
     */
    private String transactionId;

    /**
     * 
     */
    private Date transactionTime;

    /**
     * 
     */
    private Date completeTime;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "TRADE_NO")
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        if(tradeNo == null){
              removeValidField("tradeNo");
              return;
        }
        addValidField("tradeNo");
    }

    @Column(name = "TRADE_TYPE")
    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
        if(tradeType == null){
              removeValidField("tradeType");
              return;
        }
        addValidField("tradeType");
    }

    @Column(name = "TOTAL_FEE")
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
        if(totalFee == null){
              removeValidField("totalFee");
              return;
        }
        addValidField("totalFee");
    }

    @Column(name = "OPEN_ID")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
        if(openId == null){
              removeValidField("openId");
              return;
        }
        addValidField("openId");
    }

    @Column(name = "TRANSACTION_ID")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        if(transactionId == null){
              removeValidField("transactionId");
              return;
        }
        addValidField("transactionId");
    }

    @Column(name = "TRANSACTION_TIME")
    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
        if(transactionTime == null){
              removeValidField("transactionTime");
              return;
        }
        addValidField("transactionTime");
    }

    @Column(name = "COMPLETE_TIME")
    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
        if(completeTime == null){
              removeValidField("completeTime");
              return;
        }
        addValidField("completeTime");
    }
}