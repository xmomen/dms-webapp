package com.xmomen.module.payrecord.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.payrecord.model.PayRecordQuery;
import com.xmomen.module.payrecord.model.PayRecordModel;
import com.xmomen.module.payrecord.entity.PayRecord;
import com.xmomen.module.wx.pay.model.WeixinPayRecord;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-4-26 22:45:12
 */
public interface PayRecordService {

    /**
     * 新增支付凭证
     *
     * @param payRecordModel 新增支付凭证对象参数
     * @return PayRecordModel    支付凭证领域对象
     */
    public PayRecordModel createPayRecord(PayRecordModel payRecordModel);

    /**
     * 新增支付凭证实体对象
     *
     * @param payRecord 新增支付凭证实体对象参数
     * @return PayRecord 支付凭证实体对象
     */
    public PayRecord createPayRecord(PayRecord payRecord);

    /**
     * 批量新增支付凭证
     *
     * @param PayRecordModel 新增支付凭证对象集合参数
     * @return List<PayRecordModel>    支付凭证领域对象集合
     */
    List<PayRecordModel> createPayRecords(List<PayRecordModel> payRecordModels);

    /**
     * 更新支付凭证
     *
     * @param payRecordModel 更新支付凭证对象参数
     */
    public void updatePayRecord(PayRecordModel payRecordModel);

    /**
     * 更新支付凭证实体对象
     *
     * @param payRecord 新增支付凭证实体对象参数
     * @return PayRecord 支付凭证实体对象
     */
    public void updatePayRecord(PayRecord payRecord);

    /**
     * 批量删除支付凭证
     *
     * @param ids 主键数组
     */
    public void deletePayRecord(String[] ids);

    /**
     * 删除支付凭证
     *
     * @param id 主键
     */
    public void deletePayRecord(String id);

    /**
     * 查询支付凭证领域分页对象（带参数条件）
     *
     * @param payRecordQuery 查询参数
     * @param limit          每页最大数
     * @param offset         页码
     * @return Page<PayRecordModel>   支付凭证参数对象
     */
    public Page<PayRecordModel> getPayRecordModelPage(int limit, int offset, PayRecordQuery payRecordQuery);

    /**
     * 查询支付凭证领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<PayRecordModel> 支付凭证领域对象
     */
    public Page<PayRecordModel> getPayRecordModelPage(int limit, int offset);

    /**
     * 查询支付凭证领域集合对象（带参数条件）
     *
     * @param payRecordQuery 查询参数对象
     * @return List<PayRecordModel> 支付凭证领域集合对象
     */
    public List<PayRecordModel> getPayRecordModelList(PayRecordQuery payRecordQuery);

    /**
     * 查询支付凭证领域集合对象（无参数条件）
     *
     * @return List<PayRecordModel> 支付凭证领域集合对象
     */
    public List<PayRecordModel> getPayRecordModelList();

    /**
     * 查询支付凭证实体对象
     *
     * @param id 主键
     * @return PayRecord 支付凭证实体对象
     */
    public PayRecord getOnePayRecord(String id);

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return PayRecordModel 支付凭证领域对象
     */
    public PayRecordModel getOnePayRecordModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param payRecordQuery 支付凭证查询参数对象
     * @return PayRecordModel 支付凭证领域对象
     */
    public PayRecordModel getOnePayRecordModel(PayRecordQuery payRecordQuery) throws TooManyResultsException;

    public PayRecord addPayRecord(WeixinPayRecord weixinPayRecord);

    public void finishPayRecord(WeixinPayRecord weixinPayRecord);

    public PayRecord getTbPayRecordById(String id);
}
