package com.xmomen.module.wx.model.requestModel.message;

import java.io.Serializable;

/**
 * 视频消息
 */
public class RequestMessageVideo extends RequestMessageBase implements Serializable {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
