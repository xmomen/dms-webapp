package com.xmomen.module.wx.model;

import com.xmomen.framework.utils.DateUtils;
import com.xmomen.framework.utils.StringUtilsExt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 微信返回的token
 */
public class AccessToken {
    static Logger log = LoggerFactory.getLogger(AccessToken.class);

    /**
     * 获取到的token
     */
    private String access_token = StringUtilsExt.EMPTY;

    /**
     * 凭证有效时间，单位：秒
     */
    private String expires_in = StringUtilsExt.EMPTY;

    /**
     * 最后获取时间，单位:秒
     */
    private long lastTime = 0;

    /**
     * 注意：Json解析用必须设置默认构造函数，禁止删除此构造函数
     */
    public AccessToken() {

    }

    public AccessToken(String access_token, String expires_in, Date lastTime) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        if (lastTime != null) {
            this.lastTime = lastTime.getTime() / 1000;
        }
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 是否失效
     *
     * @return true:失效/false:未失效
     */
    public boolean isExpired() {
        if (StringUtilsExt.isEmpty(this.getAccess_token()) || this.getLastTime() == 0 || StringUtilsExt.isEmpty(this.getExpires_in())) {
            return true;
        }

        long currentTime = (new Date()).getTime() / 1000;
        long diffSecond = currentTime - lastTime;

        log.info(String.format("当前时间：%s/Token取得时间：%s/凭证有效时间：%s/间隔时间：%s", currentTime, this.lastTime, this.expires_in, diffSecond));

        //凭证失效的场合（微信实际失效期为7200）
        if (diffSecond > Integer.parseInt(expires_in) - 600) {
            return true;
        }
        return false;
    }
}
