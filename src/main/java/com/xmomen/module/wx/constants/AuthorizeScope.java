package com.xmomen.module.wx.constants;

/**
 * 授权方式
 */
public enum AuthorizeScope {

    //不弹出授权页面，直接跳转，只能获取用户openid
    snsapi_base,

    //弹出授权页面，可通过openid拿到昵称、性别、所在地、
    snsapi_userinfo;
}
