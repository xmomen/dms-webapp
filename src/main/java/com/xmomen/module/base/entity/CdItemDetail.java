package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_item_detail")
public class CdItemDetail extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品ID
     */
    private Integer cdItemId;

    /**
     * 商品的详细内容
     */
    private String itemDetailContent;

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

    @Column(name = "item_detail_content")
    public String getItemDetailContent() {
        return itemDetailContent;
    }

    public void setItemDetailContent(String itemDetailContent) {
        this.itemDetailContent = itemDetailContent;
        if(itemDetailContent == null){
              removeValidField("itemDetailContent");
              return;
        }
        addValidField("itemDetailContent");
    }
}