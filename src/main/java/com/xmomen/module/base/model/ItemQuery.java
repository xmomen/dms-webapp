package com.xmomen.module.base.model;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/5/13.
 */
public class ItemQuery implements Serializable{

    private Integer id;
    private String keyword;
    private Integer sellStatus;
    private Integer companyId;
    private Integer itemType;
    private Integer[] ids;
    private Integer[] excludeIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer[] getExcludeIds() {
        return excludeIds;
    }

    public void setExcludeIds(Integer[] excludeIds) {
        this.excludeIds = excludeIds;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
