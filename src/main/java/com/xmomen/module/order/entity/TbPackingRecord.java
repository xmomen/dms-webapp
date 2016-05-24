package com.xmomen.module.order.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_packing_record")
public class TbPackingRecord extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 商品条码
     */
    private String upc;

    /**
     * 订单商品ID
     */
    private Integer orderItemId;

    /**
     * 扫描时间
     */
    private Date scanTime;

    /**
     * 装箱ID
     */
    private Integer packingId;

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

    @Column(name = "UPC")
    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
        if(upc == null){
              removeValidField("upc");
              return;
        }
        addValidField("upc");
    }

    @Column(name = "ORDER_ITEM_ID")
    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
        if(orderItemId == null){
              removeValidField("orderItemId");
              return;
        }
        addValidField("orderItemId");
    }

    @Column(name = "SCAN_TIME")
    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
        if(scanTime == null){
              removeValidField("scanTime");
              return;
        }
        addValidField("scanTime");
    }

    @Column(name = "PACKING_ID")
    public Integer getPackingId() {
        return packingId;
    }

    public void setPackingId(Integer packingId) {
        this.packingId = packingId;
        if(packingId == null){
              removeValidField("packingId");
              return;
        }
        addValidField("packingId");
    }
}