package com.xmomen.module.stock.service.impl;

import com.xmomen.module.stock.entity.StockRecord;
import com.xmomen.module.stock.entity.StockRecordExample;
import com.xmomen.module.stock.mapper.StockRecordMapperExt;
import com.xmomen.module.stock.model.StockRecordCreate;
import com.xmomen.module.stock.model.StockRecordQuery;
import com.xmomen.module.stock.model.StockRecordUpdate;
import com.xmomen.module.stock.model.StockRecordModel;
import com.xmomen.module.stock.service.StockRecordService;
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
 * @date    2017-5-13 12:48:23
 * @version 1.0.0
 */
@Service
public class StockRecordServiceImpl implements StockRecordService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增商品库存
     *
     * @param stockRecordModel 新增商品库存对象参数
     * @return StockRecordModel    商品库存领域对象
     */
    @Override
    @Transactional
    public StockRecordModel createStockRecord(StockRecordModel stockRecordModel) {
        StockRecord stockRecord = createStockRecord(stockRecordModel.getEntity());
        if(stockRecord != null){
            return getOneStockRecordModel(stockRecord.getId());
        }
        return null;
    }

    /**
     * 新增商品库存实体对象
     *
     * @param stockRecord 新增商品库存实体对象参数
     * @return StockRecord 商品库存实体对象
     */
    @Override
    @Transactional
    public StockRecord createStockRecord(StockRecord stockRecord) {
        return mybatisDao.insertByModel(stockRecord);
    }

    /**
    * 批量新增商品库存
    *
    * @param stockRecordModels 新增商品库存对象集合参数
    * @return List<StockRecordModel>    商品库存领域对象集合
    */
    @Override
    @Transactional
    public List<StockRecordModel> createStockRecords(List<StockRecordModel> stockRecordModels) {
        List<StockRecordModel> stockRecordModelList = null;
        for (StockRecordModel stockRecordModel : stockRecordModels) {
            stockRecordModel = createStockRecord(stockRecordModel);
            if(stockRecordModel != null){
                if(stockRecordModelList == null){
                    stockRecordModelList = new ArrayList<>();
                }
                stockRecordModelList.add(stockRecordModel);
            }
        }
        return stockRecordModelList;
    }

    /**
     * 更新商品库存
     *
     * @param stockRecordModel 更新商品库存对象参数
     */
    @Override
    @Transactional
    public void updateStockRecord(StockRecordModel stockRecordModel) {
        mybatisDao.update(stockRecordModel.getEntity());
    }

    /**
     * 更新商品库存实体对象
     *
     * @param stockRecord 新增商品库存实体对象参数
     * @return StockRecord 商品库存实体对象
     */
    @Override
    @Transactional
    public void updateStockRecord(StockRecord stockRecord) {
        mybatisDao.update(stockRecord);
    }

    /**
     * 删除商品库存
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteStockRecord(String[] ids) {
        StockRecordExample stockRecordExample = new StockRecordExample();
        stockRecordExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(stockRecordExample);
    }

    /**
    * 删除商品库存
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteStockRecord(String id) {
        mybatisDao.deleteByPrimaryKey(StockRecord.class, id);
    }

    /**
     * 查询商品库存领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param stockRecordQuery 查询参数
     * @return Page<StockRecordModel>   商品库存参数对象
     */
    @Override
    public Page<StockRecordModel> getStockRecordModelPage(int limit, int offset, StockRecordQuery stockRecordQuery) {
        return (Page<StockRecordModel>) mybatisDao.selectPage(StockRecordMapperExt.StockRecordMapperNameSpace + "getStockRecordModel", stockRecordQuery, limit, offset);
    }

    /**
     * 查询商品库存领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<StockRecordModel> 商品库存领域对象
     */
    @Override
    public Page<StockRecordModel> getStockRecordModelPage(int limit, int offset) {
        return (Page<StockRecordModel>) mybatisDao.selectPage(StockRecordMapperExt.StockRecordMapperNameSpace + "getStockRecordModel", null, limit, offset);
    }

    /**
     * 查询商品库存领域集合对象（带参数条件）
     *
     * @param stockRecordQuery 查询参数对象
     * @return List<StockRecordModel> 商品库存领域集合对象
     */
    @Override
    public List<StockRecordModel> getStockRecordModelList(StockRecordQuery stockRecordQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(StockRecordMapperExt.StockRecordMapperNameSpace + "getStockRecordModel", stockRecordQuery);
    }

    /**
     * 查询商品库存领域集合对象（无参数条件）
     *
     * @return List<StockRecordModel> 商品库存领域集合对象
     */
    @Override
    public List<StockRecordModel> getStockRecordModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(StockRecordMapperExt.StockRecordMapperNameSpace + "getStockRecordModel");
    }

    /**
     * 查询商品库存实体对象
     *
     * @param id 主键
     * @return StockRecord 商品库存实体对象
     */
    @Override
    public StockRecord getOneStockRecord(String id) {
        return mybatisDao.selectByPrimaryKey(StockRecord.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return StockRecordModel 商品库存领域对象
     */
    @Override
    public StockRecordModel getOneStockRecordModel(String id) {
        StockRecordQuery stockRecordQuery = new StockRecordQuery();
        stockRecordQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(StockRecordMapperExt.StockRecordMapperNameSpace + "getStockRecordModel", stockRecordQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param stockRecordQuery 商品库存查询参数对象
     * @return StockRecordModel 商品库存领域对象
     */
    @Override
    public StockRecordModel getOneStockRecordModel(StockRecordQuery stockRecordQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(StockRecordMapperExt.StockRecordMapperNameSpace + "getStockRecordModel", stockRecordQuery);
    }
}
