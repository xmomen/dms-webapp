package com.xmomen.module.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.model.PayModel;
import com.xmomen.module.wx.pay.model.PayResData;
import com.xmomen.module.wx.service.MessageHandlerService;
import com.xmomen.module.wx.service.WeixinApiService;
import com.xmomen.module.wx.util.SignUtil;

/**
 * 微信后端接入控制器
 */
@RestController
@RequestMapping("/wx/api")
public class WeixinController {

    @Autowired
    MessageHandlerService messageHandlerService;

    @Autowired
    WeixinApiService weixinApiService;

    private Logger log = LoggerFactory.getLogger(WeixinController.class);

    /**
     * 获取js-sdk的config信息
     *
     * @param url
     * @return
     */
    @RequestMapping(value = "/jsapi_ticket")
    public Map getJsapiTicket(@RequestParam(value = "url") String url) {
        return weixinApiService.getJsSDKConfig("gh_9248df680cef", url);
    }

    /**
     * 订单支付下单
     *
     * @param outTradeNo 订单号
     * @param totalFee   总金额
     * @param openId     openId
     * @return
     */
    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    @ResponseBody
    public PayResData payOrder(@RequestBody @Valid PayModel payOrderModel, HttpServletRequest request) {
        return weixinApiService.payOrder(payOrderModel.getOutTradeNo(), payOrderModel.getTotalFee(), payOrderModel.getOpenId(), payOrderModel.getType(), request);
    }

    /**
     * 支付回调方法
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/notify")
    @ResponseBody
    public String notify(HttpServletRequest request) {
        // 获取微信POST过来反馈信息
        System.out.print("微信支付回调获取数据开始");
        log.info("微信支付回调获取数据开始");
        String inputLine;
        String notityXml = "";
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            log.info("xml获取失败：" + e);
        }
        log.info("收到微信异步回调：" + notityXml);
        return this.weixinApiService.notify(notityXml);
    }

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
