package com.xmomen.module.wx.model.requestModel.message;


import java.io.Serializable;

/**
 * 语音消息
 */
public class RequestMessageVoice extends RequestMessageBase implements Serializable {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    private String format;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
