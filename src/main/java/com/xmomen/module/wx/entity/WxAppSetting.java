package com.xmomen.module.wx.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "wx_app_setting")
public class WxAppSetting extends BaseMybatisModel {
    /**
     * UID
     */
    private String uid;

    /**
     * 公众号UID
     */
    private String publicUid;

    /**
     * 是否是服务号
     */
    private Integer isServiceAccount;

    /**
     * AppId
     */
    private String appId;

    /**
     * AppSecret
     */
    private String appSecret;

    /**
     * Token
     */
    private String token;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 自定义回复程序集
     */
    private String customAssemblyName;

    /**
     * 自定义回复类名称
     */
    private String customClassName;

    /**
     * 跳转域名
     */
    private String domainName;

    /**
     * 记录状态
     */
    private Integer recordStatus;

    /**
     * 有效的凭证
     */
    private String accessToken;

    /**
     * 凭证有效时间
     */
    private Integer expiresIn;

    /**
     * 凭证最后取得时间
     */
    private Date lastGetTime;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "uid")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
        if(uid == null){
              removeValidField("uid");
              return;
        }
        addValidField("uid");
    }

    @Column(name = "public_uid")
    public String getPublicUid() {
        return publicUid;
    }

    public void setPublicUid(String publicUid) {
        this.publicUid = publicUid;
        if(publicUid == null){
              removeValidField("publicUid");
              return;
        }
        addValidField("publicUid");
    }

    @Column(name = "is_service_account")
    public Integer getIsServiceAccount() {
        return isServiceAccount;
    }

    public void setIsServiceAccount(Integer isServiceAccount) {
        this.isServiceAccount = isServiceAccount;
        if(isServiceAccount == null){
              removeValidField("isServiceAccount");
              return;
        }
        addValidField("isServiceAccount");
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
        if(appId == null){
              removeValidField("appId");
              return;
        }
        addValidField("appId");
    }

    @Column(name = "app_secret")
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        if(appSecret == null){
              removeValidField("appSecret");
              return;
        }
        addValidField("appSecret");
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        if(token == null){
              removeValidField("token");
              return;
        }
        addValidField("token");
    }

    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
        if(companyName == null){
              removeValidField("companyName");
              return;
        }
        addValidField("companyName");
    }

    @Column(name = "application_name")
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
        if(applicationName == null){
              removeValidField("applicationName");
              return;
        }
        addValidField("applicationName");
    }

    @Column(name = "custom_assembly_name")
    public String getCustomAssemblyName() {
        return customAssemblyName;
    }

    public void setCustomAssemblyName(String customAssemblyName) {
        this.customAssemblyName = customAssemblyName;
        if(customAssemblyName == null){
              removeValidField("customAssemblyName");
              return;
        }
        addValidField("customAssemblyName");
    }

    @Column(name = "custom_class_name")
    public String getCustomClassName() {
        return customClassName;
    }

    public void setCustomClassName(String customClassName) {
        this.customClassName = customClassName;
        if(customClassName == null){
              removeValidField("customClassName");
              return;
        }
        addValidField("customClassName");
    }

    @Column(name = "domain_name")
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
        if(domainName == null){
              removeValidField("domainName");
              return;
        }
        addValidField("domainName");
    }

    @Column(name = "record_status")
    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
        if(recordStatus == null){
              removeValidField("recordStatus");
              return;
        }
        addValidField("recordStatus");
    }

    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        if(accessToken == null){
              removeValidField("accessToken");
              return;
        }
        addValidField("accessToken");
    }

    @Column(name = "expires_in")
    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        if(expiresIn == null){
              removeValidField("expiresIn");
              return;
        }
        addValidField("expiresIn");
    }

    @Column(name = "last_get_time")
    public Date getLastGetTime() {
        return lastGetTime;
    }

    public void setLastGetTime(Date lastGetTime) {
        this.lastGetTime = lastGetTime;
        if(lastGetTime == null){
              removeValidField("lastGetTime");
              return;
        }
        addValidField("lastGetTime");
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        if(remark == null){
              removeValidField("remark");
              return;
        }
        addValidField("remark");
    }
}