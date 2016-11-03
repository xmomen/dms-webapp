package com.xmomen.module.wx.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.entity.CdBind;
import com.xmomen.module.wx.constants.RequestEventEnum;
import com.xmomen.module.wx.constants.ResponseMsgTypeEnum;
import com.xmomen.module.wx.model.MessageBase;
import com.xmomen.module.wx.model.requestModel.event.RequestMessageEventBase;
import com.xmomen.module.wx.model.requestModel.event.RequestMessageEventScanCode;
import com.xmomen.module.wx.model.requestModel.event.RequestMessageEventScanCode.ScanCodeInfo;
import com.xmomen.module.wx.model.requestModel.event.RequestMessageEventTemplateSendJobFinish;
import com.xmomen.module.wx.model.responseModel.ResponseMessageText;
import com.xmomen.module.wx.util.MessageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信事件处理（关注，取消关注，上报地理位置等事件)
 */
@Service
public class ResponseMessageEventService implements ResponseMessageBaseService {

    Logger log = LoggerFactory.getLogger(ResponseMessageEventService.class);

    @Autowired
    WeixinApiService weixinApiService;
    
    @Autowired
    BindService bindService;
    
    @Autowired
    MybatisDao mybatisDao;

    /**
     * 对微信的请求消息做出响应
     *
     * @param requestMessage    请求的消息内容
     * @param requestMessageExt 请求内容封装类
     * @return 回复给微信的消息 （xml格式）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public String handleRequestMessage(MessageBase requestMessage) throws InstantiationException, IllegalAccessException {
        // 如果是事件响应
        if (requestMessage instanceof RequestMessageEventBase) {
            RequestMessageEventBase event = (RequestMessageEventBase) requestMessage;
            log.info(event.getEvent());

            //订阅事件
            if (null != event && RequestEventEnum.subscribe.toString().equals(event.getEvent())) {
                return onEventSubscribeRequest(event);
            }
            //取消订阅事件
            else if (null != event && RequestEventEnum.unsubscribe.toString().equals(event.getEvent())) {
                return onEventUnSubscribeRequest(event);
            }
            //菜单事件
            else if (null != event && RequestEventEnum.CLICK.toString().equals(event.getEvent())) {
                return onEventClickRequest(event);
            }
            //扫码事件
            else if (null != event && RequestEventEnum.scancode_waitmsg.toString().equals(event.getEvent())) {
                return onEventScancodeWaitMsg((RequestMessageEventScanCode) event);
            }
            //模板推送消息事件
            else if (null != event && RequestEventEnum.TEMPLATESENDJOBFINISH.toString().equals(event.getEvent())) {
                return onEventTemplateSendJobFinishRequest((RequestMessageEventTemplateSendJobFinish) event);
            }
            else {
                return onEventViewRequest(event);
            }
        }
        //不是
        else {
            return StringUtilsExt.EMPTY;
        }
    }

    /**
     * 订购(关注)
     *
     * @param requestMessage 请求的消息
     * @return 回复的消息
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private String onEventSubscribeRequest(RequestMessageEventBase requestMessage) throws InstantiationException, IllegalAccessException {
        //回复文本消息
        ResponseMessageText responseMessage = new ResponseMessageText();
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        responseMessage.setMsgType(ResponseMsgTypeEnum.text.toString().toLowerCase());

        //用户openId;
        String openId = requestMessage.getFromUserName();

        log.info(String.format("微信关注(openId = %s)。", openId));

        String content = "欢迎关注";

        responseMessage.setContent(content);
        return MessageUtils.textMessageToXml((ResponseMessageText) responseMessage);
    }

    /**
     * 取消订购(取消关注)
     *
     * @param requestMessage 请求的消息
     * @return 回复的消息
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private String onEventUnSubscribeRequest(RequestMessageEventBase requestMessage) throws InstantiationException, IllegalAccessException {
        //拼装回复message
        ResponseMessageText responseMessage = new ResponseMessageText();
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        responseMessage.setMsgType(ResponseMsgTypeEnum.text.toString().toLowerCase());
        responseMessage.setContent("取消关注");

        //用户openId;
        String openId = requestMessage.getFromUserName();

        log.info(String.format("取消关注(openId = %s)。", openId));

        return MessageUtils.textMessageToXml((ResponseMessageText) responseMessage);
    }

    /**
     * 点击菜单跳转事件
     *
     * @param requestMessage 请求的消息
     * @return 回复的消息
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private String onEventViewRequest(MessageBase requestMessage) throws InstantiationException, IllegalAccessException {
        return StringUtilsExt.EMPTY;
    }

    /**
     * 模板消息推送事件处理
     *
     * @param requestMessage 请求的消息
     * @return 回复的消息
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private String onEventTemplateSendJobFinishRequest(RequestMessageEventTemplateSendJobFinish requestMessage) throws InstantiationException, IllegalAccessException {
        //回复空串
        return StringUtilsExt.EMPTY;
    }

    /**
     * 点击菜单事件拉取消息事件
     *
     * @param requestMessage 请求的消息
     * @return 回复的消息
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private String onEventClickRequest(RequestMessageEventBase requestMessage) {
        return StringUtilsExt.EMPTY;
    }
    
    /**
     * 快递扫描操作
     * @param requestMessage
     * @return
     */
    private String onEventScancodeWaitMsg(RequestMessageEventScanCode requestMessage){
    	//回复文本消息
        ResponseMessageText responseMessage = new ResponseMessageText();
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        responseMessage.setMsgType(ResponseMsgTypeEnum.text.toString().toLowerCase());
        ScanCodeInfo scanCodeInfo = requestMessage.getScanCodeInfo();
        //扫描的内容
        String scanResult = scanCodeInfo.getScanResult();
        CdBind bind = new CdBind();
		bind.setOpenId(requestMessage.getFromUserName());
		List<CdBind> binds = mybatisDao.selectByModel(bind);
		String content = "";
		if(binds == null || binds.size() == 0 ){
			content = "请先绑定再进行扫码操作!绑定格式：绑定+手机号(绑定131XXXXXXXX)";
		}else{
	        content = bindService.bindExpressMember(bind.getPhone(), scanResult.split("&")[0]);
		}
        
        log.info(String.format("扫描到的内容(scanResult = %s)。", scanResult.split("&")[0]));

        responseMessage.setContent(content);
        return MessageUtils.textMessageToXml((ResponseMessageText) responseMessage);
    }
}
