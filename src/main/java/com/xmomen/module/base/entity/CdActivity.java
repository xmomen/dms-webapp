package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_activity")
public class CdActivity extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动开始时间
     */
    private Date activityBeginTime;

    /**
     * 活动结束时间
     */
    private Date activityEndTime;

    /**
     * 活动介绍
     */
    private String activityDescribe;

    /**
     * 参与活动的卡类型
     */
    private Integer activityType;

    /**
     * 活动下单时间
     */
    private Integer activityDay;

    /**
     * 是否启用（0-不启用，1-启用）
     */
    private Integer available;

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

    @Column(name = "ACTIVITY_NAME")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
        if(activityName == null){
              removeValidField("activityName");
              return;
        }
        addValidField("activityName");
    }

    @Column(name = "ACTIVITY_BEGIN_TIME")
    public Date getActivityBeginTime() {
        return activityBeginTime;
    }

    public void setActivityBeginTime(Date activityBeginTime) {
        this.activityBeginTime = activityBeginTime;
        if(activityBeginTime == null){
              removeValidField("activityBeginTime");
              return;
        }
        addValidField("activityBeginTime");
    }

    @Column(name = "ACTIVITY_END_TIME")
    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
        if(activityEndTime == null){
              removeValidField("activityEndTime");
              return;
        }
        addValidField("activityEndTime");
    }

    @Column(name = "ACTIVITY_DESCRIBE")
    public String getActivityDescribe() {
        return activityDescribe;
    }

    public void setActivityDescribe(String activityDescribe) {
        this.activityDescribe = activityDescribe;
        if(activityDescribe == null){
              removeValidField("activityDescribe");
              return;
        }
        addValidField("activityDescribe");
    }

    @Column(name = "ACTIVITY_TYPE")
    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
        if(activityType == null){
              removeValidField("activityType");
              return;
        }
        addValidField("activityType");
    }

    @Column(name = "ACTIVITY_DAY")
    public Integer getActivityDay() {
        return activityDay;
    }

    public void setActivityDay(Integer activityDay) {
        this.activityDay = activityDay;
        if(activityDay == null){
              removeValidField("activityDay");
              return;
        }
        addValidField("activityDay");
    }

    @Column(name = "AVAILABLE")
    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
        if(available == null){
              removeValidField("available");
              return;
        }
        addValidField("available");
    }
}