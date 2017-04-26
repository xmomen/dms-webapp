package com.xmomen.module.wx.pay.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.wx.pay.entity.TbPayRecord;
import com.xmomen.module.wx.pay.entity.mapper.PayRecordMapper;
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
		mybatisDao.getSqlSessionTemplate().insert(PayRecordMapper.PAY_RECORD_MAPPER_NAMESPACE + "insertPayRecord", tbPayRecord);
		return tbPayRecord;
	}
	
	public void finishPayRecord(WeixinPayRecord weixinPayRecord) {
		String id = weixinPayRecord.getTradeId();
		TbPayRecord tbPayRecord = mybatisDao.selectByPrimaryKey(TbPayRecord.class, id);
		if(tbPayRecord != null) {
			tbPayRecord.setTransactionId(weixinPayRecord.getTransactionId());
			tbPayRecord.setCompleteTime(new Date());
			mybatisDao.getSqlSessionTemplate().update(PayRecordMapper.PAY_RECORD_MAPPER_NAMESPACE + "markFinished", tbPayRecord);
			//mybatisDao.update(tbPayRecord);
		}
	}
	
	public TbPayRecord getTbPayRecordById(String id) {
		TbPayRecord tbPayRecord = mybatisDao.selectByPrimaryKey(TbPayRecord.class, id);
		return tbPayRecord;
	}
	
	public TbPayRecord getTbpayRecordByRecord(TbPayRecord tbPayRecord) {
		List<TbPayRecord> tbPayRecords = mybatisDao.getSqlSessionTemplate()
				.selectList(PayRecordMapper.PAY_RECORD_MAPPER_NAMESPACE + "selectByQuery", tbPayRecord);
		//List<TbPayRecord> tbPayRecords = mybatisDao.selectByModel(tbPayRecord);
		if(tbPayRecords == null || tbPayRecords.isEmpty()) {
			return null;
		}
		return tbPayRecords.get(0);
	}
	
	public List<TbPayRecord> getTbpayRecordListByRecord(TbPayRecord tbPayRecord) {
		List<TbPayRecord> tbPayRecords = mybatisDao.getSqlSessionTemplate()
				.selectList(PayRecordMapper.PAY_RECORD_MAPPER_NAMESPACE + "selectByQuery", tbPayRecord);
		return tbPayRecords;
	}
	
	public void insert(TbPayRecord tbPayRecord) {
		mybatisDao.getSqlSessionTemplate().insert(PayRecordMapper.PAY_RECORD_MAPPER_NAMESPACE + "insertPayRecord", tbPayRecord);
	}
}
