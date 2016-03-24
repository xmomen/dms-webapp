package com.xmomen.module.core.web.controller.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeng on 16/1/7.
 */
public class AccountSettingDto implements Serializable {

    private String username;
    private List<NavItem> navItems;
    private List<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<NavItem> getNavItems() {
        return navItems;
    }

    public void setNavItems(List<NavItem> navItems) {
        this.navItems = navItems;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public class NavItem {
        private String url;
        private String name;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class Role {
        private String role;
        private String description;
        private Boolean available;

        public Role() {
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean isAvailable() {
            return available;
        }

        public void setAvailable(Boolean available) {
            this.available = available;
        }
    }
}
