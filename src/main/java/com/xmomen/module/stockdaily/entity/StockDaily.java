package com.xmomen.module.stockdaily.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_stock_daily")
public class StockDaily extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 
     */
    private String stockId;

    /**
     * 
     */
    private String itemId;

    /**
     * 昨日库存
     */
    private Integer oldStockNum;

    /**
     * 入库库存
     */
    private Integer inNum;

    /**
     * 取消退货入库数
     */
    private Integer returnInNum;

    /**
     * 出库库存
     */
    private Integer outNum;

    /**
     * 破损数
     */
    private Integer damagedNum;

    /**
     * 核销数
     */
    private Integer verificationNum;

    /**
     * 现库存
     */
    private Integer newStockNum;

    /**
     * 快照时间
     */
    private Date dailyDate;

    /**
     * 插入时间
     */
    private Date insertDate;

    @Column(name = "id")
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

    @Column(name = "stock_id")
    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
        if(stockId == null){
              removeValidField("stockId");
              return;
        }
        addValidField("stockId");
    }

    @Column(name = "item_id")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
        if(itemId == null){
              removeValidField("itemId");
              return;
        }
        addValidField("itemId");
    }

    @Column(name = "old_stock_num")
    public Integer getOldStockNum() {
        return oldStockNum;
    }

    public void setOldStockNum(Integer oldStockNum) {
        this.oldStockNum = oldStockNum;
        if(oldStockNum == null){
              removeValidField("oldStockNum");
              return;
        }
        addValidField("oldStockNum");
    }

    @Column(name = "in_num")
    public Integer getInNum() {
        return inNum;
    }

    public void setInNum(Integer inNum) {
        this.inNum = inNum;
        if(inNum == null){
              removeValidField("inNum");
              return;
        }
        addValidField("inNum");
    }

    @Column(name = "return_in_num")
    public Integer getReturnInNum() {
        return returnInNum;
    }

    public void setReturnInNum(Integer returnInNum) {
        this.returnInNum = returnInNum;
        if(returnInNum == null){
              removeValidField("returnInNum");
              return;
        }
        addValidField("returnInNum");
    }

    @Column(name = "out_num")
    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
        if(outNum == null){
              removeValidField("outNum");
              return;
        }
        addValidField("outNum");
    }

    @Column(name = "damaged_num")
    public Integer getDamagedNum() {
        return damagedNum;
    }

    public void setDamagedNum(Integer damagedNum) {
        this.damagedNum = damagedNum;
        if(damagedNum == null){
              removeValidField("damagedNum");
              return;
        }
        addValidField("damagedNum");
    }

    @Column(name = "verification_num")
    public Integer getVerificationNum() {
        return verificationNum;
    }

    public void setVerificationNum(Integer verificationNum) {
        this.verificationNum = verificationNum;
        if(verificationNum == null){
              removeValidField("verificationNum");
              return;
        }
        addValidField("verificationNum");
    }

    @Column(name = "new_stock_num")
    public Integer getNewStockNum() {
        return newStockNum;
    }

    public void setNewStockNum(Integer newStockNum) {
        this.newStockNum = newStockNum;
        if(newStockNum == null){
              removeValidField("newStockNum");
              return;
        }
        addValidField("newStockNum");
    }

    @Column(name = "daily_date")
    public Date getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
        if(dailyDate == null){
              removeValidField("dailyDate");
              return;
        }
        addValidField("dailyDate");
    }

    @Column(name = "insert_date")
    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
        if(insertDate == null){
              removeValidField("insertDate");
              return;
        }
        addValidField("insertDate");
    }
}