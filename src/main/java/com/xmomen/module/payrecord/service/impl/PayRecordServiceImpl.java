package com.xmomen.module.payrecord.service.impl;

import com.xmomen.module.payrecord.entity.PayRecord;
import com.xmomen.module.payrecord.entity.PayRecordExample;
import com.xmomen.module.payrecord.mapper.PayRecordMapperExt;
import com.xmomen.module.payrecord.model.PayRecordCreate;
import com.xmomen.module.payrecord.model.PayRecordQuery;
import com.xmomen.module.payrecord.model.PayRecordUpdate;
import com.xmomen.module.payrecord.model.PayRecordModel;
import com.xmomen.module.payrecord.service.PayRecordService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.wx.pay.model.WeixinPayRecord;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-4-26 22:45:12
 */
@Service
public class PayRecordServiceImpl implements PayRecordService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增支付凭证
     *
     * @param payRecordModel 新增支付凭证对象参数
     * @return PayRecordModel    支付凭证领域对象
     */
    @Override
    @Transactional
    public PayRecordModel createPayRecord(PayRecordModel payRecordModel) {
        PayRecord payRecord = createPayRecord(payRecordModel.getEntity());
        if (payRecord != null) {
            return getOnePayRecordModel(payRecord.getId());
        }
        return null;
    }

    /**
     * 新增支付凭证实体对象
     *
     * @param payRecord 新增支付凭证实体对象参数
     * @return PayRecord 支付凭证实体对象
     */
    @Override
    @Transactional
    public PayRecord createPayRecord(PayRecord payRecord) {
        return mybatisDao.insertByModel(payRecord);
    }

    /**
     * 批量新增支付凭证
     *
     * @param payRecordModels 新增支付凭证对象集合参数
     * @return List<PayRecordModel>    支付凭证领域对象集合
     */
    @Override
    @Transactional
    public List<PayRecordModel> createPayRecords(List<PayRecordModel> payRecordModels) {
        List<PayRecordModel> payRecordModelList = null;
        for (PayRecordModel payRecordModel : payRecordModels) {
            payRecordModel = createPayRecord(payRecordModel);
            if (payRecordModel != null) {
                if (payRecordModelList == null) {
                    payRecordModelList = new ArrayList<>();
                }
                payRecordModelList.add(payRecordModel);
            }
        }
        return payRecordModelList;
    }

    /**
     * 更新支付凭证
     *
     * @param payRecordModel 更新支付凭证对象参数
     */
    @Override
    @Transactional
    public void updatePayRecord(PayRecordModel payRecordModel) {
        mybatisDao.update(payRecordModel.getEntity());
    }

    /**
     * 更新支付凭证实体对象
     *
     * @param payRecord 新增支付凭证实体对象参数
     * @return PayRecord 支付凭证实体对象
     */
    @Override
    @Transactional
    public void updatePayRecord(PayRecord payRecord) {
        mybatisDao.update(payRecord);
    }

    /**
     * 删除支付凭证
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deletePayRecord(String[] ids) {
        PayRecordExample payRecordExample = new PayRecordExample();
        payRecordExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(payRecordExample);
    }

    /**
     * 删除支付凭证
     *
     * @param id 主键
     */
    @Override
    @Transactional
    public void deletePayRecord(String id) {
        mybatisDao.deleteByPrimaryKey(PayRecord.class, id);
    }

    /**
     * 查询支付凭证领域分页对象（带参数条件）
     *
     * @param limit          每页最大数
     * @param offset         页码
     * @param payRecordQuery 查询参数
     * @return Page<PayRecordModel>   支付凭证参数对象
     */
    @Override
    public Page<PayRecordModel> getPayRecordModelPage(int limit, int offset, PayRecordQuery payRecordQuery) {
        return (Page<PayRecordModel>) mybatisDao.selectPage(PayRecordMapperExt.PayRecordMapperNameSpace + "getPayRecordModel", payRecordQuery, limit, offset);
    }

    /**
     * 查询支付凭证领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<PayRecordModel> 支付凭证领域对象
     */
    @Override
    public Page<PayRecordModel> getPayRecordModelPage(int limit, int offset) {
        return (Page<PayRecordModel>) mybatisDao.selectPage(PayRecordMapperExt.PayRecordMapperNameSpace + "getPayRecordModel", null, limit, offset);
    }

    /**
     * 查询支付凭证领域集合对象（带参数条件）
     *
     * @param payRecordQuery 查询参数对象
     * @return List<PayRecordModel> 支付凭证领域集合对象
     */
    @Override
    public List<PayRecordModel> getPayRecordModelList(PayRecordQuery payRecordQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(PayRecordMapperExt.PayRecordMapperNameSpace + "getPayRecordModel", payRecordQuery);
    }

    /**
     * 查询支付凭证领域集合对象（无参数条件）
     *
     * @return List<PayRecordModel> 支付凭证领域集合对象
     */
    @Override
    public List<PayRecordModel> getPayRecordModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(PayRecordMapperExt.PayRecordMapperNameSpace + "getPayRecordModel");
    }

    /**
     * 查询支付凭证实体对象
     *
     * @param id 主键
     * @return PayRecord 支付凭证实体对象
     */
    @Override
    public PayRecord getOnePayRecord(String id) {
        return mybatisDao.selectByPrimaryKey(PayRecord.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return PayRecordModel 支付凭证领域对象
     */
    @Override
    public PayRecordModel getOnePayRecordModel(String id) {
        PayRecordQuery payRecordQuery = new PayRecordQuery();
        payRecordQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(PayRecordMapperExt.PayRecordMapperNameSpace + "getPayRecordModel", payRecordQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param payRecordQuery 支付凭证查询参数对象
     * @return PayRecordModel 支付凭证领域对象
     */
    @Override
    public PayRecordModel getOnePayRecordModel(PayRecordQuery payRecordQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(PayRecordMapperExt.PayRecordMapperNameSpace + "getPayRecordModel", payRecordQuery);
    }


    public PayRecord addPayRecord(WeixinPayRecord weixinPayRecord) {
        PayRecord tbPayRecord = new PayRecord();
        tbPayRecord.setId(weixinPayRecord.getTradeId());
        tbPayRecord.setOpenId(weixinPayRecord.getOpenId());
        tbPayRecord.setTradeType(weixinPayRecord.getTradeType());
        tbPayRecord.setTradeNo(weixinPayRecord.getTradeNo());

        tbPayRecord.setTotalFee(new BigDecimal(Double.valueOf(weixinPayRecord.getTotalFee()) / 100));
        tbPayRecord.setTransactionTime(new Date());
        mybatisDao.insert(tbPayRecord);
        return tbPayRecord;
    }

    public void finishPayRecord(WeixinPayRecord weixinPayRecord) {
        String id = weixinPayRecord.getTradeId();
        PayRecord tbPayRecord = mybatisDao.selectByPrimaryKey(PayRecord.class, id);
        if (tbPayRecord != null) {
            tbPayRecord.setTransactionId(weixinPayRecord.getTransactionId());
            tbPayRecord.setCompleteTime(new Date());
            mybatisDao.update(tbPayRecord);
        }
    }

    public PayRecord getTbPayRecordById(String id) {
        PayRecord tbPayRecord = mybatisDao.selectByPrimaryKey(PayRecord.class, id);
        return tbPayRecord;
    }
}
