package com.xmomen.module.wx.model.messageTemplate;

/**
 * 微信服务器请求回复封装类
 */
public class WeixinResponseStatusModel {

    /**
     * 状态码
     */
    private int errcode;

    /**
     * 消息
     */
    private String errmsg;

    /**
     * 消息id
     */
    private String msgid;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
