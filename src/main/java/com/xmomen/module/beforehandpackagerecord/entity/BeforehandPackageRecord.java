package com.xmomen.module.beforehandpackagerecord.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_beforehand_package_record")
public class BeforehandPackageRecord extends BaseMybatisModel {
    /**
     * 
     */
    private String id;

    /**
     * 包装商品
     */
    private Integer cdItemId;

    /**
     * 包装商品数
     */
    private Integer packageNum;

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

    @Column(name = "cd_item_id")
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

    @Column(name = "package_num")
    public Integer getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(Integer packageNum) {
        this.packageNum = packageNum;
        if(packageNum == null){
              removeValidField("packageNum");
              return;
        }
        addValidField("packageNum");
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