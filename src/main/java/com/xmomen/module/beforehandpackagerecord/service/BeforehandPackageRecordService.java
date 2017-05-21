package com.xmomen.module.beforehandpackagerecord.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordQuery;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordModel;
import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecord;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-5-18 23:36:38
 * @version 1.0.0
 */
public interface BeforehandPackageRecordService {

    /**
     * 新增预包装记录表
     * @param  beforehandPackageRecordModel   新增预包装记录表对象参数
     * @return  BeforehandPackageRecordModel    预包装记录表领域对象
     */
    public BeforehandPackageRecordModel createBeforehandPackageRecord(BeforehandPackageRecordModel beforehandPackageRecordModel);

    /**
     * 新增预包装记录表实体对象
     * @param   beforehandPackageRecord 新增预包装记录表实体对象参数
     * @return  BeforehandPackageRecord 预包装记录表实体对象
     */
    public BeforehandPackageRecord createBeforehandPackageRecord(BeforehandPackageRecord beforehandPackageRecord);

    /**
    * 批量新增预包装记录表
    * @param BeforehandPackageRecordModel     新增预包装记录表对象集合参数
    * @return List<BeforehandPackageRecordModel>    预包装记录表领域对象集合
    */
    List<BeforehandPackageRecordModel> createBeforehandPackageRecords(List<BeforehandPackageRecordModel> beforehandPackageRecordModels);

    /**
     * 更新预包装记录表
     * @param beforehandPackageRecordModel    更新预包装记录表对象参数
     */
    public void updateBeforehandPackageRecord(BeforehandPackageRecordModel beforehandPackageRecordModel);

    /**
     * 更新预包装记录表实体对象
     * @param   beforehandPackageRecord 新增预包装记录表实体对象参数
     * @return  BeforehandPackageRecord 预包装记录表实体对象
     */
    public void updateBeforehandPackageRecord(BeforehandPackageRecord beforehandPackageRecord);

    /**
     * 批量删除预包装记录表
     * @param ids   主键数组
     */
    public void deleteBeforehandPackageRecord(String[] ids);

    /**
     * 删除预包装记录表
     * @param id   主键
     */
    public void deleteBeforehandPackageRecord(String id);

    /**
     * 查询预包装记录表领域分页对象（带参数条件）
     * @param beforehandPackageRecordQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<BeforehandPackageRecordModel>   预包装记录表参数对象
     */
    public Page<BeforehandPackageRecordModel> getBeforehandPackageRecordModelPage(int limit, int offset, BeforehandPackageRecordQuery beforehandPackageRecordQuery);

    /**
     * 查询预包装记录表领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<BeforehandPackageRecordModel> 预包装记录表领域对象
     */
    public Page<BeforehandPackageRecordModel> getBeforehandPackageRecordModelPage(int limit, int offset);

    /**
     * 查询预包装记录表领域集合对象（带参数条件）
     * @param beforehandPackageRecordQuery 查询参数对象
     * @return List<BeforehandPackageRecordModel> 预包装记录表领域集合对象
     */
    public List<BeforehandPackageRecordModel> getBeforehandPackageRecordModelList(BeforehandPackageRecordQuery beforehandPackageRecordQuery);

    /**
     * 查询预包装记录表领域集合对象（无参数条件）
     * @return List<BeforehandPackageRecordModel> 预包装记录表领域集合对象
     */
    public List<BeforehandPackageRecordModel> getBeforehandPackageRecordModelList();

    /**
     * 查询预包装记录表实体对象
     * @param id 主键
     * @return BeforehandPackageRecord 预包装记录表实体对象
     */
    public BeforehandPackageRecord getOneBeforehandPackageRecord(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return BeforehandPackageRecordModel 预包装记录表领域对象
     */
    public BeforehandPackageRecordModel getOneBeforehandPackageRecordModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param beforehandPackageRecordQuery 预包装记录表查询参数对象
     * @return BeforehandPackageRecordModel 预包装记录表领域对象
     */
    public BeforehandPackageRecordModel getOneBeforehandPackageRecordModel(BeforehandPackageRecordQuery beforehandPackageRecordQuery) throws TooManyResultsException;
}
