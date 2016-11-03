package com.xmomen.module.wx.controller;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.service.MessageHandlerService;
import com.xmomen.module.wx.util.SignUtil;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信后端接入控制器
 */
@Controller
@RequestMapping("/wx/api")
public class WeixinController {
    @Autowired
    MessageHandlerService messageHandlerService;

    private Logger log = LoggerFactory.getLogger(WeixinController.class);

    /**
     * 微信接口接入API主方法
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping(value = "/weixinApi")
    public void weixinApi(HttpServletRequest request, HttpServletResponse response) {
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;

        if (StringUtilsExt.isEmpty(echostr)) {
            try {
            	 log.info("请求服务器");
                //设置response字符串格式为UTF-8
                response.setCharacterEncoding("utf-8");

                //处理请求，并且获取 回复消息
                String responseXml = messageHandlerService.execute(request.getInputStream());

                log.info("回复微信服务器的内容：" + responseXml);

                //将回复消息返回给微信服务器
                out = response.getWriter();

                out.print(responseXml);

                out.flush();
            } catch (IOException e) {
                log.error("接口调用失败：", e);
            } finally {
                IOUtils.closeQuietly(out);
            }
        }
        //配置验证用，原样返回随机字符串
        else {
            log.info("随机字符串(echostr): " + echostr);
            try {
                out = response.getWriter();

                //开发者通过检验signature对请求进行校验（下面有校验方式）。
                //若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，否则接入失败。
                if (checkSignature(request)) {
                    out.print(echostr);
                    log.info("微信接入验证成功。");
                }
                else {
                    log.error("微信接入验证失败。");
                }
                out.flush();
            } catch (IOException e) {
                log.error("微信接入验证失败：", e);
            } finally {
                IOUtils.closeQuietly(out);
            }
        }
    }

    /**
     * 效验URL地址是否来自微信
     *
     * @param request HttpServletRequest
     * @return 校验结果
     */
    private boolean checkSignature(HttpServletRequest request) {
        // 微信加密签名
        String msg_signature = request.getParameter("signature");

        // 时间戳
        String timestamp = request.getParameter("timestamp");

        // 随机数
        String nonce = request.getParameter("nonce");

        String publicUid = request.getParameter("publicUid");
        
        String token = request.getParameter("token");

        if (StringUtilsExt.isEmpty(publicUid)) {
            log.error("请在微信对接接口地址中设定publicUid参数。");
            return false;
        }
        if (StringUtilsExt.isEmpty(token)) {
            log.error("请在微信对接接口地址中设定token参数。");
            return false;
        }

        return SignUtil.checkSignature(token, msg_signature, timestamp, nonce);
    }
}
