package com.xmomen.module.wx.model.requestModel.event;


import com.xmomen.module.wx.model.MessageBase;

import java.io.Serializable;

/**
 * 请求事件消息基类
 */
public class RequestMessageEventBase extends MessageBase implements Serializable {

    /**
     * 事件类型
     */
    private String event;

    /**
     * 事件KEY值
     */
    private String eventKey;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
