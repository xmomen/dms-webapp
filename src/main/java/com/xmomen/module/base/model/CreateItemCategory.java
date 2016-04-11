package com.xmomen.module.base.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeng on 16/3/26.
 */
public class CreateItemCategory implements Serializable {

    @NotNull
    @NotBlank
    private String name;
    private Integer parentId;

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
}
