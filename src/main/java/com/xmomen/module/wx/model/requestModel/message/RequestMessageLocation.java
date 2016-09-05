package com.xmomen.module.wx.model.requestModel.message;


import java.io.Serializable;

/**
 * 地理位置消息
 */
public class RequestMessageLocation extends RequestMessageBase implements Serializable {

    /**
     * 地理位置维度
     */
    private double Location_X;

    /**
     * 地理位置经度
     */
    private double Location_Y;

    /**
     * 地图缩放大小
     */
    private int Scale;

    /**
     * 地理位置信息
     */
    private String Label;

    public double getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(double location_X) {
        Location_X = location_X;
    }

    public double getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(double location_Y) {
        Location_Y = location_Y;
    }

    public int getScale() {
        return Scale;
    }

    public void setScale(int scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
