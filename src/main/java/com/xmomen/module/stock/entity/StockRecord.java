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
@Table(name = "cd_stock_record")
public class StockRecord extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 库存id
     */
    private String stockId;

    /**
     * 变更数量
     */
    private Integer changeNum;

    /**
     * 1-增加 2-减少
     */
    private Integer changType;

    /**
     * 如果是订单变更，关联订单ID
     */
    private Integer refOrderId;

    /**
     * 备注(记录变更）
     */
    private String remark;

    /**
     * 创建人
     */
    private Integer insertUserId;

    /**
     * 创建时间
     */
    private Date insertDate;

    /**
     * 更新人
     */
    private Integer updateUserId;

    /**
     * 更新时间
     */
    private Date updateDate;

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

    @Column(name = "change_num")
    public Integer getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Integer changeNum) {
        this.changeNum = changeNum;
        if(changeNum == null){
              removeValidField("changeNum");
              return;
        }
        addValidField("changeNum");
    }

    @Column(name = "chang_type")
    public Integer getChangType() {
        return changType;
    }

    public void setChangType(Integer changType) {
        this.changType = changType;
        if(changType == null){
              removeValidField("changType");
              return;
        }
        addValidField("changType");
    }

    @Column(name = "ref_order_id")
    public Integer getRefOrderId() {
        return refOrderId;
    }

    public void setRefOrderId(Integer refOrderId) {
        this.refOrderId = refOrderId;
        if(refOrderId == null){
              removeValidField("refOrderId");
              return;
        }
        addValidField("refOrderId");
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        if(remark == null){
              removeValidField("remark");
              return;
        }
        addValidField("remark");
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
}