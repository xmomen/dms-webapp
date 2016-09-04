package com.xmomen.module.wx.model.requestModel.event;


import com.xmomen.module.wx.constants.RequestEventEnum;

import java.io.Serializable;

/**
 * 上报地理位置事件
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[LOCATION]]></Event>
 * <Latitude>23.137466</Latitude>
 * <Longitude>113.352425</Longitude>
 * <Precision>119.385040</Precision>
 * </xml>
 */
public class RequestMessageEventLocation extends RequestMessageEventBase implements Serializable {

    /**
     * 地理位置纬度
     */
    private double latitude;
    /**
     * 地理位置经度
     */
    private double longitude;
    /**
     * 地理位置精度
     */
    private double precision;

    @Override
    public String getEvent() {
        return RequestEventEnum.LOCATION.toString();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }
}
