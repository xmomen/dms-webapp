package com.xmomen.module.advice.service.impl;

import com.xmomen.module.advice.entity.Advice;
import com.xmomen.module.advice.entity.AdviceExample;
import com.xmomen.module.advice.mapper.AdviceMapperExt;
import com.xmomen.module.advice.model.AdviceCreate;
import com.xmomen.module.advice.model.AdviceQuery;
import com.xmomen.module.advice.model.AdviceUpdate;
import com.xmomen.module.advice.model.AdviceModel;
import com.xmomen.module.advice.service.AdviceService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.wx.util.DateUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-5-14 20:05:05
 * @version 1.0.0
 */
@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增快报
     *
     * @param adviceModel 新增快报对象参数
     * @return AdviceModel    快报领域对象
     */
    @Override
    @Transactional
    public AdviceModel createAdvice(AdviceModel adviceModel) {
        Advice advice = createAdvice(adviceModel.getEntity());
        if(advice != null){
            return getOneAdviceModel(advice.getId());
        }
        return null;
    }

    /**
     * 新增快报实体对象
     *
     * @param advice 新增快报实体对象参数
     * @return Advice 快报实体对象
     */
    @Override
    @Transactional
    public Advice createAdvice(Advice advice) {
        advice.setInsertDate(DateUtils.getNowDate());
        advice.setInsertUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        advice.setUpdateDate(mybatisDao.getSysdate());
        advice.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        return mybatisDao.insertByModel(advice);
    }

    /**
    * 批量新增快报
    *
    * @param adviceModels 新增快报对象集合参数
    * @return List<AdviceModel>    快报领域对象集合
    */
    @Override
    @Transactional
    public List<AdviceModel> createAdvices(List<AdviceModel> adviceModels) {
        List<AdviceModel> adviceModelList = null;
        for (AdviceModel adviceModel : adviceModels) {
            adviceModel = createAdvice(adviceModel);
            if(adviceModel != null){
                if(adviceModelList == null){
                    adviceModelList = new ArrayList<>();
                }
                adviceModelList.add(adviceModel);
            }
        }
        return adviceModelList;
    }

    /**
     * 更新快报
     *
     * @param adviceModel 更新快报对象参数
     */
    @Override
    @Transactional
    public void updateAdvice(AdviceModel adviceModel) {
        adviceModel.setUpdateDate(mybatisDao.getSysdate());
        adviceModel.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        mybatisDao.update(adviceModel.getEntity());
    }

    /**
     * 更新快报实体对象
     *
     * @param advice 新增快报实体对象参数
     * @return Advice 快报实体对象
     */
    @Override
    @Transactional
    public void updateAdvice(Advice advice) {
        advice.setUpdateDate(mybatisDao.getSysdate());
        advice.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        mybatisDao.update(advice);
    }

    /**
     * 删除快报
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteAdvice(String[] ids) {
        AdviceExample adviceExample = new AdviceExample();
        adviceExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(adviceExample);
    }

    /**
    * 删除快报
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteAdvice(String id) {
        mybatisDao.deleteByPrimaryKey(Advice.class, id);
    }

    /**
     * 查询快报领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param adviceQuery 查询参数
     * @return Page<AdviceModel>   快报参数对象
     */
    @Override
    public Page<AdviceModel> getAdviceModelPage(int limit, int offset, AdviceQuery adviceQuery) {
        return (Page<AdviceModel>) mybatisDao.selectPage(AdviceMapperExt.AdviceMapperNameSpace + "getAdviceModel", adviceQuery, limit, offset);
    }

    /**
     * 查询快报领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<AdviceModel> 快报领域对象
     */
    @Override
    public Page<AdviceModel> getAdviceModelPage(int limit, int offset) {
        return (Page<AdviceModel>) mybatisDao.selectPage(AdviceMapperExt.AdviceMapperNameSpace + "getAdviceModel", null, limit, offset);
    }

    /**
     * 查询快报领域集合对象（带参数条件）
     *
     * @param adviceQuery 查询参数对象
     * @return List<AdviceModel> 快报领域集合对象
     */
    @Override
    public List<AdviceModel> getAdviceModelList(AdviceQuery adviceQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(AdviceMapperExt.AdviceMapperNameSpace + "getAdviceModel", adviceQuery);
    }

    /**
     * 查询快报领域集合对象（无参数条件）
     *
     * @return List<AdviceModel> 快报领域集合对象
     */
    @Override
    public List<AdviceModel> getAdviceModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(AdviceMapperExt.AdviceMapperNameSpace + "getAdviceModel");
    }

    /**
     * 查询快报实体对象
     *
     * @param id 主键
     * @return Advice 快报实体对象
     */
    @Override
    public Advice getOneAdvice(String id) {
        return mybatisDao.selectByPrimaryKey(Advice.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return AdviceModel 快报领域对象
     */
    @Override
    public AdviceModel getOneAdviceModel(String id) {
        AdviceQuery adviceQuery = new AdviceQuery();
        adviceQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(AdviceMapperExt.AdviceMapperNameSpace + "getAdviceModel", adviceQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param adviceQuery 快报查询参数对象
     * @return AdviceModel 快报领域对象
     */
    @Override
    public AdviceModel getOneAdviceModel(AdviceQuery adviceQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(AdviceMapperExt.AdviceMapperNameSpace + "getAdviceModel", adviceQuery);
    }
}
