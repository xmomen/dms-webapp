package com.xmomen.module.stock.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_stock")
public class Stock extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 商品ID
     */
    private Integer itemId;

    /**
     * 库存数
     */
    private Integer stockNum;

    /**
     * 预警数量
     */
    private Integer warningNum;

    /**
     * 创建时间
     */
    private Date insertDate;

    /**
     * 创建人
     */
    private Integer insertUserId;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private Integer updateUserId;

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

    @Column(name = "item_id")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
        if(itemId == null){
              removeValidField("itemId");
              return;
        }
        addValidField("itemId");
    }

    @Column(name = "stock_num")
    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
        if(stockNum == null){
              removeValidField("stockNum");
              return;
        }
        addValidField("stockNum");
    }

    @Column(name = "warning_num")
    public Integer getWarningNum() {
        return warningNum;
    }

    public void setWarningNum(Integer warningNum) {
        this.warningNum = warningNum;
        if(warningNum == null){
              removeValidField("warningNum");
              return;
        }
        addValidField("warningNum");
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

    @Column(name = "insert_user_id")
    public Integer getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(Integer insertUserId) {
        this.insertUserId = insertUserId;
        if(insertUserId == null){
              removeValidField("insertUserId");
              return;
        }
        addValidField("insertUserId");
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        if(updateDate == null){
              removeValidField("updateDate");
              return;
        }
        addValidField("updateDate");
    }

    @Column(name = "update_user_id")
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
        if(updateUserId == null){
              removeValidField("updateUserId");
              return;
        }
        addValidField("updateUserId");
    }
}