package com.xmomen.module.system.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeng on 16/3/25.
 */
public class OrganizationModel implements Serializable {

    private Integer id;
    private String name;
    private String desc;
    private String code;
    private Integer parentNodeId;
    private List<OrganizationModel> nodes;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(Integer parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public List<OrganizationModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<OrganizationModel> nodes) {
        this.nodes = nodes;
    }
}
