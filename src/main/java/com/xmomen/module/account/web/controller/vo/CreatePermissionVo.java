package com.xmomen.module.account.web.controller.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeng on 2016/3/18.
 */
public class CreatePermissionVo implements Serializable {

    @NotBlank
    @NotNull
    private String permissionCode;
    @NotBlank
    @NotNull
    private String description;
    private Boolean available;

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
