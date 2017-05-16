package com.xmomen.module.advice.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.advice.model.AdviceQuery;
import com.xmomen.module.advice.model.AdviceModel;
import com.xmomen.module.advice.entity.Advice;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-5-14 20:05:05
 * @version 1.0.0
 */
public interface AdviceService {

    /**
     * 新增快报
     * @param  adviceModel   新增快报对象参数
     * @return  AdviceModel    快报领域对象
     */
    public AdviceModel createAdvice(AdviceModel adviceModel);

    /**
     * 新增快报实体对象
     * @param   advice 新增快报实体对象参数
     * @return  Advice 快报实体对象
     */
    public Advice createAdvice(Advice advice);

    /**
    * 批量新增快报
    * @param AdviceModel     新增快报对象集合参数
    * @return List<AdviceModel>    快报领域对象集合
    */
    List<AdviceModel> createAdvices(List<AdviceModel> adviceModels);

    /**
     * 更新快报
     * @param adviceModel    更新快报对象参数
     */
    public void updateAdvice(AdviceModel adviceModel);

    /**
     * 更新快报实体对象
     * @param   advice 新增快报实体对象参数
     * @return  Advice 快报实体对象
     */
    public void updateAdvice(Advice advice);

    /**
     * 批量删除快报
     * @param ids   主键数组
     */
    public void deleteAdvice(String[] ids);

    /**
     * 删除快报
     * @param id   主键
     */
    public void deleteAdvice(String id);

    /**
     * 查询快报领域分页对象（带参数条件）
     * @param adviceQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<AdviceModel>   快报参数对象
     */
    public Page<AdviceModel> getAdviceModelPage(int limit, int offset, AdviceQuery adviceQuery);

    /**
     * 查询快报领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<AdviceModel> 快报领域对象
     */
    public Page<AdviceModel> getAdviceModelPage(int limit, int offset);

    /**
     * 查询快报领域集合对象（带参数条件）
     * @param adviceQuery 查询参数对象
     * @return List<AdviceModel> 快报领域集合对象
     */
    public List<AdviceModel> getAdviceModelList(AdviceQuery adviceQuery);

    /**
     * 查询快报领域集合对象（无参数条件）
     * @return List<AdviceModel> 快报领域集合对象
     */
    public List<AdviceModel> getAdviceModelList();

    /**
     * 查询快报实体对象
     * @param id 主键
     * @return Advice 快报实体对象
     */
    public Advice getOneAdvice(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return AdviceModel 快报领域对象
     */
    public AdviceModel getOneAdviceModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param adviceQuery 快报查询参数对象
     * @return AdviceModel 快报领域对象
     */
    public AdviceModel getOneAdviceModel(AdviceQuery adviceQuery) throws TooManyResultsException;
}
