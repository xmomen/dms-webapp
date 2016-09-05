package com.xmomen.module.wx.model;

import java.io.Serializable;

/**
 * 微信Message抽象类
 */
public abstract class MessageBase implements Serializable {
    /**
     * 接收者
     */
    private String toUserName;

    /**
     * 发送者
     */
    private String fromUserName;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息创建时间
     */
    private long createTime;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
