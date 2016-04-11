package com.xmomen.module.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeng on 16/3/25.
 */
public class ItemCategoryModel implements Serializable {

    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private List<ItemCategoryModel> nodes;

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

    public List<ItemCategoryModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<ItemCategoryModel> nodes) {
        this.nodes = nodes;
    }
}
