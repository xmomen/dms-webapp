package com.xmomen.module.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeng on 16/3/25.
 */
public class CouponCategoryModel implements Serializable {

    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private List<CouponCategoryModel> nodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<CouponCategoryModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<CouponCategoryModel> nodes) {
        this.nodes = nodes;
    }
}
