package com.xmomen.module.order.entity;

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
@Table(name = "tb_trade_record")
public class TbTradeRecord extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 交易时间
     */
    private Date createTime;

    /**
     * 交易类型：cash-现金，card-卡券
     */
    private String tradeType;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "AMOUNT")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        if(amount == null){
              removeValidField("amount");
              return;
        }
        addValidField("amount");
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

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if(createTime == null){
              removeValidField("createTime");
              return;
        }
        addValidField("createTime");
    }

    @Column(name = "TRADE_TYPE")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
        if(tradeType == null){
              removeValidField("tradeType");
              return;
        }
        addValidField("tradeType");
    }
}