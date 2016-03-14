package com.udfex.ucs.module.user.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;

@Entity
@Table(name = "sys_users")
public class SysUsers extends BaseMybatisModel {
    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否启用：0-禁用，1-启用
     */
    private Integer locked;

    @Column(name = "ID")
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

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        if(username == null){
              removeValidField("username");
              return;
        }
        addValidField("username");
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        if(password == null){
              removeValidField("password");
              return;
        }
        addValidField("password");
    }

    @Column(name = "SALT")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
        if(salt == null){
              removeValidField("salt");
              return;
        }
        addValidField("salt");
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if(email == null){
              removeValidField("email");
              return;
        }
        addValidField("email");
    }

    @Column(name = "LOCKED")
    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
        if(locked == null){
              removeValidField("locked");
              return;
        }
        addValidField("locked");
    }
}