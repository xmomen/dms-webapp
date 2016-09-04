package com.xmomen.module.wx.model.messageTemplate;

/**
 * 客服发送消息模板抽象类
 */
public abstract class CustomSendMessageBaseModel {
    /**
     * 普通用户openid
     */
    String touser;
    /**
     * 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息为news，卡券为wxcard
     */
    String msgtype;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
