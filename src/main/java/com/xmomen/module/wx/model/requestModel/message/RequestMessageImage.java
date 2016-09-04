package com.xmomen.module.wx.model.requestModel.message;


import java.io.Serializable;

/**
 * 图片消息
 */
public class RequestMessageImage extends RequestMessageBase implements Serializable {

    /**
     * 图片链接（由微信系统生成）
     */
    private String picUrl;

    /**
     * 图片消息媒体id
     */
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
