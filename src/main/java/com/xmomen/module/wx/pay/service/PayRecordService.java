package com.xmomen.module.wx.pay.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.wx.pay.entity.TbPayRecord;
import com.xmomen.module.wx.pay.model.WeixinPayRecord;

@Service
public class PayRecordService {

	@Autowired
	MybatisDao mybatisDao;
	
	public TbPayRecord addPayRecord(WeixinPayRecord weixinPayRecord) {
		TbPayRecord tbPayRecord = new TbPayRecord();
		tbPayRecord.setId(weixinPayRecord.getTradeId());
		tbPayRecord.setOpenId(weixinPayRecord.getOpenId());
		tbPayRecord.setTradeType(weixinPayRecord.getTradeType());
		tbPayRecord.setTradeNo(weixinPayRecord.getTradeNo());
		
		tbPayRecord.setTotalFee(new BigDecimal(Double.valueOf(weixinPayRecord.getTotalFee())/100));
		tbPayRecord.setTransactionTime(new Date());
		return tbPayRecord;
	}
	
	public void finishPayRecord(WeixinPayRecord weixinPayRecord) {
		String id = weixinPayRecord.getTradeId();
		TbPayRecord tbPayRecord = mybatisDao.selectByPrimaryKey(TbPayRecord.class, id);
		if(tbPayRecord != null) {
			tbPayRecord.setTransactionId(weixinPayRecord.getTransactionId());
			tbPayRecord.setCompleteTime(new Date());
			mybatisDao.update(tbPayRecord);
		}
	}
}
