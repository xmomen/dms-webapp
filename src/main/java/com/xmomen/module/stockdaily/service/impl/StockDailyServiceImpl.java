package com.xmomen.module.stockdaily.service.impl;

import com.xmomen.module.report.model.FinanceReport;
import com.xmomen.module.report.model.ReportQuery;
import com.xmomen.module.report.model.StockDailyReport;
import com.xmomen.module.stock.entity.Stock;
import com.xmomen.module.stockdaily.entity.StockDaily;
import com.xmomen.module.stockdaily.entity.StockDailyExample;
import com.xmomen.module.stockdaily.mapper.StockDailyMapperExt;
import com.xmomen.module.stockdaily.model.StockDailyCreate;
import com.xmomen.module.stockdaily.model.StockDailyQuery;
import com.xmomen.module.stockdaily.model.StockDailyUpdate;
import com.xmomen.module.stockdaily.model.StockDailyModel;
import com.xmomen.module.stockdaily.service.StockDailyService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.wx.util.DateUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-26 21:56:23
 */
@Service
public class StockDailyServiceImpl implements StockDailyService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增库存日报
     *
     * @param stockDailyModel 新增库存日报对象参数
     * @return StockDailyModel    库存日报领域对象
     */
    @Override
    @Transactional
    public StockDailyModel createStockDaily(StockDailyModel stockDailyModel) {
        StockDaily stockDaily = createStockDaily(stockDailyModel.getEntity());
        if (stockDaily != null) {
            return getOneStockDailyModel(stockDaily.getId());
        }
        return null;
    }

    /**
     * 新增库存日报实体对象
     *
     * @param stockDaily 新增库存日报实体对象参数
     * @return StockDaily 库存日报实体对象
     */
    @Override
    @Transactional
    public StockDaily createStockDaily(StockDaily stockDaily) {
        return mybatisDao.insertByModel(stockDaily);
    }

    /**
     * 批量新增库存日报
     *
     * @param stockDailyModels 新增库存日报对象集合参数
     * @return List<StockDailyModel>    库存日报领域对象集合
     */
    @Override
    @Transactional
    public List<StockDailyModel> createStockDailys(List<StockDailyModel> stockDailyModels) {
        List<StockDailyModel> stockDailyModelList = null;
        for (StockDailyModel stockDailyModel : stockDailyModels) {
            stockDailyModel = createStockDaily(stockDailyModel);
            if (stockDailyModel != null) {
                if (stockDailyModelList == null) {
                    stockDailyModelList = new ArrayList<>();
                }
                stockDailyModelList.add(stockDailyModel);
            }
        }
        return stockDailyModelList;
    }

    /**
     * 更新库存日报
     *
     * @param stockDailyModel 更新库存日报对象参数
     */
    @Override
    @Transactional
    public void updateStockDaily(StockDailyModel stockDailyModel) {
        mybatisDao.update(stockDailyModel.getEntity());
    }

    /**
     * 更新库存日报实体对象
     *
     * @param stockDaily 新增库存日报实体对象参数
     * @return StockDaily 库存日报实体对象
     */
    @Override
    @Transactional
    public void updateStockDaily(StockDaily stockDaily) {
        mybatisDao.update(stockDaily);
    }

    /**
     * 删除库存日报
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteStockDaily(String[] ids) {
        StockDailyExample stockDailyExample = new StockDailyExample();
        stockDailyExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(stockDailyExample);
    }

    /**
     * 删除库存日报
     *
     * @param id 主键
     */
    @Override
    @Transactional
    public void deleteStockDaily(String id) {
        mybatisDao.deleteByPrimaryKey(StockDaily.class, id);
    }

    /**
     * 查询库存日报领域分页对象（带参数条件）
     *
     * @param limit           每页最大数
     * @param offset          页码
     * @param stockDailyQuery 查询参数
     * @return Page<StockDailyModel>   库存日报参数对象
     */
    @Override
    public Page<StockDailyModel> getStockDailyModelPage(int limit, int offset, StockDailyQuery stockDailyQuery) {
        return (Page<StockDailyModel>) mybatisDao.selectPage(StockDailyMapperExt.StockDailyMapperNameSpace + "getStockDailyModel", stockDailyQuery, limit, offset);
    }

    /**
     * 查询库存日报领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<StockDailyModel> 库存日报领域对象
     */
    @Override
    public Page<StockDailyModel> getStockDailyModelPage(int limit, int offset) {
        return (Page<StockDailyModel>) mybatisDao.selectPage(StockDailyMapperExt.StockDailyMapperNameSpace + "getStockDailyModel", null, limit, offset);
    }

    /**
     * 查询库存日报领域集合对象（带参数条件）
     *
     * @param stockDailyQuery 查询参数对象
     * @return List<StockDailyModel> 库存日报领域集合对象
     */
    @Override
    public List<StockDailyModel> getStockDailyModelList(StockDailyQuery stockDailyQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(StockDailyMapperExt.StockDailyMapperNameSpace + "getStockDailyModel", stockDailyQuery);
    }

    /**
     * 查询库存日报领域集合对象（无参数条件）
     *
     * @return List<StockDailyModel> 库存日报领域集合对象
     */
    @Override
    public List<StockDailyModel> getStockDailyModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(StockDailyMapperExt.StockDailyMapperNameSpace + "getStockDailyModel");
    }

    /**
     * 查询库存日报实体对象
     *
     * @param id 主键
     * @return StockDaily 库存日报实体对象
     */
    @Override
    public StockDaily getOneStockDaily(String id) {
        return mybatisDao.selectByPrimaryKey(StockDaily.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return StockDailyModel 库存日报领域对象
     */
    @Override
    public StockDailyModel getOneStockDailyModel(String id) {
        StockDailyQuery stockDailyQuery = new StockDailyQuery();
        stockDailyQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(StockDailyMapperExt.StockDailyMapperNameSpace + "getStockDailyModel", stockDailyQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param stockDailyQuery 库存日报查询参数对象
     * @return StockDailyModel 库存日报领域对象
     */
    @Override
    public StockDailyModel getOneStockDailyModel(StockDailyQuery stockDailyQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(StockDailyMapperExt.StockDailyMapperNameSpace + "getStockDailyModel", stockDailyQuery);
    }

    /**
     * 生成今日库存快报
     */
    public synchronized void createStockDaily() {
        //删除当日库存
        StockDailyExample stockDailyExample = new StockDailyExample();
        stockDailyExample.createCriteria().andDailyDateEqualTo(DateUtils.getNowDate());
        this.mybatisDao.deleteByExample(stockDailyExample);
        //重新创建今日库存
        mybatisDao.getSqlSessionTemplate().update(StockDailyMapperExt.StockDailyMapperNameSpace + "createStockDaily");
    }

    /**
     * 查询库存快照
     *
     * @param reportQuery
     * @return
     */
    public List<StockDailyReport> getStockDailyReport(ReportQuery reportQuery) {
        List<StockDailyReport> stockDailyModels = mybatisDao.getSqlSessionTemplate().selectList(StockDailyMapperExt.StockDailyMapperNameSpace + "getStockDailyReport", reportQuery);
        return stockDailyModels;
    }
}
