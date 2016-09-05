package com.xmomen.module.wx.model.requestModel.event;

import java.io.Serializable;

/**
 * 扫描二维码事件
 * <xml><ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[subscribe]]></Event>
 * <EventKey><![CDATA[qrscene_123123]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * </xml>
 */
public class RequestMessageEventScan extends RequestMessageEventBase implements Serializable {
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
