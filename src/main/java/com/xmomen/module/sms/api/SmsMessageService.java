package com.xmomen.module.sms.api;

import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.module.sms.util.GlobalIdentifyCodeManager;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;

@Component
public class SmsMessageService implements InitializingBean {

	static Logger log = LoggerFactory.getLogger(SmsService.class);

	private MNSClient client;
	
	public void startUp() {
		CloudAccount account = new CloudAccount("LTAI1klbIDX7r7cw", 
				"YIy5b9LtvfRvboEXhJjztmJCY7eO2I", 
				"http://1478678502173333.mns.cn-hangzhou.aliyuncs.com/");
		client = account.getMNSClient();
	}
	public SmsResponse sendSingleRequest(String phoneNumber) throws Exception {
		if(client == null || !client.isOpen()) {
			log.info("client已关闭，重新创建连接");
			startUp();
		}
        CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");

        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        

        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("益谷上禾");
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode("SMS_67180402");
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        String identifyCode = RandomStringGenerator.getRandomNumberStrByLength(6);
        smsReceiverParams.setParam("code", identifyCode);
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(phoneNumber, smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            log.info("MessageId:" + ret.getMessageId());
            GlobalIdentifyCodeManager.updateIdenfifyCode(phoneNumber, identifyCode);
            SmsResponse response = new SmsResponse();
            response.setSuccess(true);
            return response;
        } catch (ServiceException se) {
        	log.error("API异常：" + se.getMessage());
            throw new BusinessException("执行SMS API 异常：ErrorCode=" + se.getErrorCode() + ", RequestId=" + se.getRequestId());
        } catch (Exception e) {
        	log.error("系统异常", e.getMessage());
        	client.close();
        	client = null;
            return null;
        }
	}
	
	public SmsResponse sendPasswordInfo(String phoneNumber, String plainPassword) {
		if(client == null || !client.isOpen()) {
			log.info("client已关闭，重新创建连接");
			startUp();
		}
        CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("益谷上禾");
        // TODO 密码重置的模板
        batchSmsAttributes.setTemplateCode("SMS_67180402");
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("code", plainPassword);
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(phoneNumber, smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        try {
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            log.info("MessageId:" + ret.getMessageId());
            SmsResponse response = new SmsResponse();
            response.setSuccess(true);
            return response;
        } catch (ServiceException se) {
        	log.error("API异常：" + se.getMessage());
            throw new BusinessException("执行SMS API 异常：ErrorCode=" + se.getErrorCode() + ", RequestId=" + se.getRequestId());
        } catch (Exception e) {
        	log.error("系统异常", e.getMessage());
        	client.close();
        	client = null;
            return null;
        }
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		startUp();
	}
}
