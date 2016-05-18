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
@Table(name = "tb_packing")
public class TbPacking extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 装箱码
     */
    private String packingNo;

    /**
     * 装箱时间
     */
    private Date packingTime;

    /**
     * 装箱库内状态：0-在库，1-出库
     */
    private Integer packingStatus;

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

    @Column(name = "PACKING_NO")
    public String getPackingNo() {
        return packingNo;
    }

    public void setPackingNo(String packingNo) {
        this.packingNo = packingNo;
        if(packingNo == null){
              removeValidField("packingNo");
              return;
        }
        addValidField("packingNo");
    }

    @Column(name = "PACKING_TIME")
    public Date getPackingTime() {
        return packingTime;
    }

    public void setPackingTime(Date packingTime) {
        this.packingTime = packingTime;
        if(packingTime == null){
              removeValidField("packingTime");
              return;
        }
        addValidField("packingTime");
    }

    @Column(name = "PACKING_STATUS")
    public Integer getPackingStatus() {
        return packingStatus;
    }

    public void setPackingStatus(Integer packingStatus) {
        this.packingStatus = packingStatus;
        if(packingStatus == null){
              removeValidField("packingStatus");
              return;
        }
        addValidField("packingStatus");
    }
}