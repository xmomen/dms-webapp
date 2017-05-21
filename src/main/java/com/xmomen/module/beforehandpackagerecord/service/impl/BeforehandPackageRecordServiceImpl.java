package com.xmomen.module.beforehandpackagerecord.service.impl;

import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecord;
import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecordExample;
import com.xmomen.module.beforehandpackagerecord.mapper.BeforehandPackageRecordMapperExt;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordCreate;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordQuery;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordUpdate;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordModel;
import com.xmomen.module.beforehandpackagerecord.service.BeforehandPackageRecordService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-5-18 23:36:38
 * @version 1.0.0
 */
@Service
public class BeforehandPackageRecordServiceImpl implements BeforehandPackageRecordService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增预包装记录表
     *
     * @param beforehandPackageRecordModel 新增预包装记录表对象参数
     * @return BeforehandPackageRecordModel    预包装记录表领域对象
     */
    @Override
    @Transactional
    public BeforehandPackageRecordModel createBeforehandPackageRecord(BeforehandPackageRecordModel beforehandPackageRecordModel) {
        BeforehandPackageRecord beforehandPackageRecord = createBeforehandPackageRecord(beforehandPackageRecordModel.getEntity());
        if(beforehandPackageRecord != null){
            return getOneBeforehandPackageRecordModel(beforehandPackageRecord.getId());
        }
        return null;
    }

    /**
     * 新增预包装记录表实体对象
     *
     * @param beforehandPackageRecord 新增预包装记录表实体对象参数
     * @return BeforehandPackageRecord 预包装记录表实体对象
     */
    @Override
    @Transactional
    public BeforehandPackageRecord createBeforehandPackageRecord(BeforehandPackageRecord beforehandPackageRecord) {
        return mybatisDao.insertByModel(beforehandPackageRecord);
    }

    /**
    * 批量新增预包装记录表
    *
    * @param beforehandPackageRecordModels 新增预包装记录表对象集合参数
    * @return List<BeforehandPackageRecordModel>    预包装记录表领域对象集合
    */
    @Override
    @Transactional
    public List<BeforehandPackageRecordModel> createBeforehandPackageRecords(List<BeforehandPackageRecordModel> beforehandPackageRecordModels) {
        List<BeforehandPackageRecordModel> beforehandPackageRecordModelList = null;
        for (BeforehandPackageRecordModel beforehandPackageRecordModel : beforehandPackageRecordModels) {
            beforehandPackageRecordModel = createBeforehandPackageRecord(beforehandPackageRecordModel);
            if(beforehandPackageRecordModel != null){
                if(beforehandPackageRecordModelList == null){
                    beforehandPackageRecordModelList = new ArrayList<>();
                }
                beforehandPackageRecordModelList.add(beforehandPackageRecordModel);
            }
        }
        return beforehandPackageRecordModelList;
    }

    /**
     * 更新预包装记录表
     *
     * @param beforehandPackageRecordModel 更新预包装记录表对象参数
     */
    @Override
    @Transactional
    public void updateBeforehandPackageRecord(BeforehandPackageRecordModel beforehandPackageRecordModel) {
        mybatisDao.update(beforehandPackageRecordModel.getEntity());
    }

    /**
     * 更新预包装记录表实体对象
     *
     * @param beforehandPackageRecord 新增预包装记录表实体对象参数
     * @return BeforehandPackageRecord 预包装记录表实体对象
     */
    @Override
    @Transactional
    public void updateBeforehandPackageRecord(BeforehandPackageRecord beforehandPackageRecord) {
        mybatisDao.update(beforehandPackageRecord);
    }

    /**
     * 删除预包装记录表
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteBeforehandPackageRecord(String[] ids) {
        BeforehandPackageRecordExample beforehandPackageRecordExample = new BeforehandPackageRecordExample();
        beforehandPackageRecordExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(beforehandPackageRecordExample);
    }

    /**
    * 删除预包装记录表
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteBeforehandPackageRecord(String id) {
        mybatisDao.deleteByPrimaryKey(BeforehandPackageRecord.class, id);
    }

    /**
     * 查询预包装记录表领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param beforehandPackageRecordQuery 查询参数
     * @return Page<BeforehandPackageRecordModel>   预包装记录表参数对象
     */
    @Override
    public Page<BeforehandPackageRecordModel> getBeforehandPackageRecordModelPage(int limit, int offset, BeforehandPackageRecordQuery beforehandPackageRecordQuery) {
        return (Page<BeforehandPackageRecordModel>) mybatisDao.selectPage(BeforehandPackageRecordMapperExt.BeforehandPackageRecordMapperNameSpace + "getBeforehandPackageRecordModel", beforehandPackageRecordQuery, limit, offset);
    }

    /**
     * 查询预包装记录表领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<BeforehandPackageRecordModel> 预包装记录表领域对象
     */
    @Override
    public Page<BeforehandPackageRecordModel> getBeforehandPackageRecordModelPage(int limit, int offset) {
        return (Page<BeforehandPackageRecordModel>) mybatisDao.selectPage(BeforehandPackageRecordMapperExt.BeforehandPackageRecordMapperNameSpace + "getBeforehandPackageRecordModel", null, limit, offset);
    }

    /**
     * 查询预包装记录表领域集合对象（带参数条件）
     *
     * @param beforehandPackageRecordQuery 查询参数对象
     * @return List<BeforehandPackageRecordModel> 预包装记录表领域集合对象
     */
    @Override
    public List<BeforehandPackageRecordModel> getBeforehandPackageRecordModelList(BeforehandPackageRecordQuery beforehandPackageRecordQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(BeforehandPackageRecordMapperExt.BeforehandPackageRecordMapperNameSpace + "getBeforehandPackageRecordModel", beforehandPackageRecordQuery);
    }

    /**
     * 查询预包装记录表领域集合对象（无参数条件）
     *
     * @return List<BeforehandPackageRecordModel> 预包装记录表领域集合对象
     */
    @Override
    public List<BeforehandPackageRecordModel> getBeforehandPackageRecordModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(BeforehandPackageRecordMapperExt.BeforehandPackageRecordMapperNameSpace + "getBeforehandPackageRecordModel");
    }

    /**
     * 查询预包装记录表实体对象
     *
     * @param id 主键
     * @return BeforehandPackageRecord 预包装记录表实体对象
     */
    @Override
    public BeforehandPackageRecord getOneBeforehandPackageRecord(String id) {
        return mybatisDao.selectByPrimaryKey(BeforehandPackageRecord.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return BeforehandPackageRecordModel 预包装记录表领域对象
     */
    @Override
    public BeforehandPackageRecordModel getOneBeforehandPackageRecordModel(String id) {
        BeforehandPackageRecordQuery beforehandPackageRecordQuery = new BeforehandPackageRecordQuery();
        beforehandPackageRecordQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(BeforehandPackageRecordMapperExt.BeforehandPackageRecordMapperNameSpace + "getBeforehandPackageRecordModel", beforehandPackageRecordQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param beforehandPackageRecordQuery 预包装记录表查询参数对象
     * @return BeforehandPackageRecordModel 预包装记录表领域对象
     */
    @Override
    public BeforehandPackageRecordModel getOneBeforehandPackageRecordModel(BeforehandPackageRecordQuery beforehandPackageRecordQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(BeforehandPackageRecordMapperExt.BeforehandPackageRecordMapperNameSpace + "getBeforehandPackageRecordModel", beforehandPackageRecordQuery);
    }
}
