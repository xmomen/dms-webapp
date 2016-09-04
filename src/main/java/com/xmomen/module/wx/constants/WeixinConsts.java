package com.xmomen.module.wx.constants;

/**
 * 微信接口URL
 */
public class WeixinConsts {
    /**
     * 获得accesstoken
     * GET请求
     */
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}";

    /**
     * 发送模版消息
     * POST请求
     */
    public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={ACCESS_TOKEN}";

    /**
     * 创建微信菜单
     * POST请求
     */
    public static final String CREATE_WEIXIN_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={ACCESS_TOKEN}";

    /**
     * 获取所有的模板消息
     * GET请求
     */
    public static final String GET_ALL_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token={ACCESS_TOKEN}";

    /**
     * 客服发消息接口
     * POST请求
     */
    public static final String CUSTOM_SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={ACCESS_TOKEN}";
    /**
     * 微信授权地址
     * appid 公众号的唯一标识
     * redirect_uri 授权后重定向的回调链接地址
     * scope  应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。
     * 并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * state 重定向后会带上state参数，开发者可以填写任意参数值
     * #wechat_redirect 直接在微信打开链接，可以不填此参数。做页面302重定向时候，必须带此参数
     * GET请求
     */
    public static final String OAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={APPID}&redirect_uri={REDIRECT_URI}&response_type=code&scope={SCOPE}&state=state#wechat_redirect";
    /**
     * 获取用户信息
     * GET请求
     */
    public static String GET_WEIXIN_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={ACCESS_TOKEN}&openid={OPENID}";
    /**
     * 下载多媒体文件 视频文件不支持下载，调用该接口需http协议
     * GET请求
     */
    public static String GET_MEDIA_FILE = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={ACCESS_TOKEN}&media_id={MEDIA_ID}";
    /**
     * 获取oauth网页认证的token
     * GET请求
     */
    public static String GET_ACCESS_TOKEN_OAUTH = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code";
}
