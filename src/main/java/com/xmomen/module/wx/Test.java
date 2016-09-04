package com.xmomen.module.wx;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.module.wx.model.MessageBase;
import com.xmomen.module.wx.model.requestModel.event.RequestMessageEventScanCode;
import com.xmomen.module.wx.util.XMLUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String json = "{\"createTime\":1472960471,\"event\":\"scancode_push\",\"eventKey\":\"rselfmenu_0_1\",\"fromUserName\":\"ovH3uwhNwt_Q646pRA43aCCwtSRE\",\"msgType\":\"event\",\"scanCodeInfo\":{\"scanResult\":\"http://weixin.qq.com/r/ajmhuTTE6mmVrXDa92x4\",\"scanType\":\"qrcode\"},\"toUserName\":\"gh_8fce9baef56f\"}";
			try{
				  //将请求的消息封装成实体对象
				RequestMessageEventScanCode requestMessageBase = (RequestMessageEventScanCode) JSONObject.parseObject(json, RequestMessageEventScanCode.class);
				System.out.println(requestMessageBase.getScanCodeInfo().getScanResult());
			}catch(Exception e){
				e.printStackTrace();
				
			}
		
	}

}
