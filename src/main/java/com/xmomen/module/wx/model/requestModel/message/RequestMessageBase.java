package com.xmomen.module.wx.model.requestModel.message;


import com.xmomen.module.wx.model.MessageBase;

import java.io.Serializable;

/**
 * 接收到请求基类（包含事件消息，用户交互消息）
 */
public class RequestMessageBase extends MessageBase implements Serializable {

    /**
     * 消息ID
     */
    private String msgId;

    public String getMsgID() {
        return msgId;
    }

    public void setMsgID(String msgID) {
        this.msgId = msgID;
    }
}
