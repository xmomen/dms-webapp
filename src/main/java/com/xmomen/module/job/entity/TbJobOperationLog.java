package com.xmomen.module.job.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_job_operation_log")
public class TbJobOperationLog extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 工作ID
     */
    private Integer jobId;

    /**
     * 操作人
     */
    private Integer jobUser;

    /**
     * 操作时间
     */
    private Date jobTime;

    /**
     * 操作的条码
     */
    private String barCode;

    /**
     * 操作的商品条码
     */
    private String itemCode;

    @Column(name = "id")
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

    @Column(name = "job_id")
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
        if(jobId == null){
              removeValidField("jobId");
              return;
        }
        addValidField("jobId");
    }

    @Column(name = "job_user")
    public Integer getJobUser() {
        return jobUser;
    }

    public void setJobUser(Integer jobUser) {
        this.jobUser = jobUser;
        if(jobUser == null){
              removeValidField("jobUser");
              return;
        }
        addValidField("jobUser");
    }

    @Column(name = "job_time")
    public Date getJobTime() {
        return jobTime;
    }

    public void setJobTime(Date jobTime) {
        this.jobTime = jobTime;
        if(jobTime == null){
              removeValidField("jobTime");
              return;
        }
        addValidField("jobTime");
    }

    @Column(name = "bar_code")
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
        if(barCode == null){
              removeValidField("barCode");
              return;
        }
        addValidField("barCode");
    }

    @Column(name = "item_code")
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
        if(itemCode == null){
              removeValidField("itemCode");
              return;
        }
        addValidField("itemCode");
    }
}