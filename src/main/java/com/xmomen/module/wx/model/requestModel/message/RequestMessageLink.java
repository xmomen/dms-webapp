package com.xmomen.module.wx.model.requestModel.message;


import java.io.Serializable;

/**
 * 链接消息
 */
public class RequestMessageLink extends RequestMessageBase implements Serializable {

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息描述
     */
    private String description;

    /**
     * 消息链接
     */
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        url = url;
    }


}
