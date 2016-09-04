package com.xmomen.module.wx.model.requestModel.event;

import java.io.Serializable;

/**
 * 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知，发送到开发者中心中填写的服务器配置地址中。
 * <xml>
 * <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
 * <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
 * <CreateTime>1395658920</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
 * <MsgID>200163836</MsgID>
 * <Status><![CDATA[success]]></Status>
 * </xml>
 */
public class RequestMessageEventTemplateSendJobFinish extends RequestMessageEventBase implements Serializable {

    /**
     * 发送状态
     */
    private String status;

    /**
     * 消息ID
     */
    private String msgID;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }
}
