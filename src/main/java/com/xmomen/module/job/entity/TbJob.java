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
@Table(name = "tb_job")
public class TbJob extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 采购编号
     */
    private String purchaseCode;

    /**
     * 采购明细ID
     */
    private Integer purchaseId;

    /**
     * 1-包装任务，2-装箱任务
     */
    private Integer jobType;

    /**
     * 0-未开始，1-包装中，2-包装完成
     */
    private Integer jobStatus;

    /**
     * 
     */
    private Integer jobUser;

    /**
     * 任务创建时间
     */
    private Date jobCreateTime;

    /**
     * 包装的产品
     */
    private Integer cdItemId;

    /**
     * 需要包装的份数
     */
    private Integer countValue;

    /**
     * 任务开始时间
     */
    private Date jobBeginTime;

    /**
     * 包装完成的份数
     */
    private Integer finishValue;

    /**
     * 
     */
    private Date finishTime;

    /**
     * 任务创建人
     */
    private Integer jobCreateUser;

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

    @Column(name = "PURCHASE_CODE")
    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
        if(purchaseCode == null){
              removeValidField("purchaseCode");
              return;
        }
        addValidField("purchaseCode");
    }

    @Column(name = "PURCHASE_ID")
    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
        if(purchaseId == null){
              removeValidField("purchaseId");
              return;
        }
        addValidField("purchaseId");
    }

    @Column(name = "JOB_TYPE")
    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
        if(jobType == null){
              removeValidField("jobType");
              return;
        }
        addValidField("jobType");
    }

    @Column(name = "JOB_STATUS")
    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
        if(jobStatus == null){
              removeValidField("jobStatus");
              return;
        }
        addValidField("jobStatus");
    }

    @Column(name = "JOB_USER")
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

    @Column(name = "JOB_CREATE_TIME")
    public Date getJobCreateTime() {
        return jobCreateTime;
    }

    public void setJobCreateTime(Date jobCreateTime) {
        this.jobCreateTime = jobCreateTime;
        if(jobCreateTime == null){
              removeValidField("jobCreateTime");
              return;
        }
        addValidField("jobCreateTime");
    }

    @Column(name = "CD_ITEM_ID")
    public Integer getCdItemId() {
        return cdItemId;
    }

    public void setCdItemId(Integer cdItemId) {
        this.cdItemId = cdItemId;
        if(cdItemId == null){
              removeValidField("cdItemId");
              return;
        }
        addValidField("cdItemId");
    }

    @Column(name = "COUNT_VALUE")
    public Integer getCountValue() {
        return countValue;
    }

    public void setCountValue(Integer countValue) {
        this.countValue = countValue;
        if(countValue == null){
              removeValidField("countValue");
              return;
        }
        addValidField("countValue");
    }

    @Column(name = "JOB_BEGIN_TIME")
    public Date getJobBeginTime() {
        return jobBeginTime;
    }

    public void setJobBeginTime(Date jobBeginTime) {
        this.jobBeginTime = jobBeginTime;
        if(jobBeginTime == null){
              removeValidField("jobBeginTime");
              return;
        }
        addValidField("jobBeginTime");
    }

    @Column(name = "FINISH_VALUE")
    public Integer getFinishValue() {
        return finishValue;
    }

    public void setFinishValue(Integer finishValue) {
        this.finishValue = finishValue;
        if(finishValue == null){
              removeValidField("finishValue");
              return;
        }
        addValidField("finishValue");
    }

    @Column(name = "FINISH_TIME")
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        if(finishTime == null){
              removeValidField("finishTime");
              return;
        }
        addValidField("finishTime");
    }

    @Column(name = "JOB_CREATE_USER")
    public Integer getJobCreateUser() {
        return jobCreateUser;
    }

    public void setJobCreateUser(Integer jobCreateUser) {
        this.jobCreateUser = jobCreateUser;
        if(jobCreateUser == null){
              removeValidField("jobCreateUser");
              return;
        }
        addValidField("jobCreateUser");
    }
}