package com.xmomen.module.advice.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_advice")
public class Advice extends BaseMybatisModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

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

    /**
     * 内容
     */
    private String content;

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

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if(title == null){
              removeValidField("title");
              return;
        }
        addValidField("title");
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

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if(content == null){
              removeValidField("content");
              return;
        }
        addValidField("content");
    }
}