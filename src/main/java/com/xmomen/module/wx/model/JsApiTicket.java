package com.xmomen.module.wx.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/3/24.
 */
@Data
public class JsApiTicket implements Serializable {

    private Integer errcode;
    private String errmsg;
    private String ticket;
    private Long expires_in;
}
