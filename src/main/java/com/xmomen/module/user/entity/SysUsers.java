package com.xmomen.module.user.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_users")
public class SysUsers extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 密码盐值
     */
    private String salt;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 1-男，2女
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 办公室电话
     */
    private String officeTel;

    /**
     * 0-禁用，1-启用
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

    @Column(name = "REALNAME")
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
        if(realname == null){
              removeValidField("realname");
              return;
        }
        addValidField("realname");
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

    @Column(name = "AGE")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        if(age == null){
              removeValidField("age");
              return;
        }
        addValidField("age");
    }

    @Column(name = "SEX")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
        if(sex == null){
              removeValidField("sex");
              return;
        }
        addValidField("sex");
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

    @Column(name = "QQ")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
        if(qq == null){
              removeValidField("qq");
              return;
        }
        addValidField("qq");
    }

    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        if(phoneNumber == null){
              removeValidField("phoneNumber");
              return;
        }
        addValidField("phoneNumber");
    }

    @Column(name = "OFFICE_TEL")
    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
        if(officeTel == null){
              removeValidField("officeTel");
              return;
        }
        addValidField("officeTel");
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