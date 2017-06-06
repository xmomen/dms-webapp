package com.xmomen.module.stockdaily.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.report.model.ReportQuery;
import com.xmomen.module.report.model.StockDailyReport;
import com.xmomen.module.stockdaily.model.StockDailyQuery;
import com.xmomen.module.stockdaily.model.StockDailyModel;
import com.xmomen.module.stockdaily.entity.StockDaily;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-26 21:56:23
 */
public interface StockDailyService {

    /**
     * 新增库存日报
     *
     * @param stockDailyModel 新增库存日报对象参数
     * @return StockDailyModel    库存日报领域对象
     */
    public StockDailyModel createStockDaily(StockDailyModel stockDailyModel);

    /**
     * 新增库存日报实体对象
     *
     * @param stockDaily 新增库存日报实体对象参数
     * @return StockDaily 库存日报实体对象
     */
    public StockDaily createStockDaily(StockDaily stockDaily);

    /**
     * 批量新增库存日报
     *
     * @param StockDailyModel 新增库存日报对象集合参数
     * @return List<StockDailyModel>    库存日报领域对象集合
     */
    List<StockDailyModel> createStockDailys(List<StockDailyModel> stockDailyModels);

    /**
     * 更新库存日报
     *
     * @param stockDailyModel 更新库存日报对象参数
     */
    public void updateStockDaily(StockDailyModel stockDailyModel);

    /**
     * 更新库存日报实体对象
     *
     * @param stockDaily 新增库存日报实体对象参数
     * @return StockDaily 库存日报实体对象
     */
    public void updateStockDaily(StockDaily stockDaily);

    /**
     * 批量删除库存日报
     *
     * @param ids 主键数组
     */
    public void deleteStockDaily(String[] ids);

    /**
     * 删除库存日报
     *
     * @param id 主键
     */
    public void deleteStockDaily(String id);

    /**
     * 查询库存日报领域分页对象（带参数条件）
     *
     * @param stockDailyQuery 查询参数
     * @param limit           每页最大数
     * @param offset          页码
     * @return Page<StockDailyModel>   库存日报参数对象
     */
    public Page<StockDailyModel> getStockDailyModelPage(int limit, int offset, StockDailyQuery stockDailyQuery);

    /**
     * 查询库存日报领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<StockDailyModel> 库存日报领域对象
     */
    public Page<StockDailyModel> getStockDailyModelPage(int limit, int offset);

    /**
     * 查询库存日报领域集合对象（带参数条件）
     *
     * @param stockDailyQuery 查询参数对象
     * @return List<StockDailyModel> 库存日报领域集合对象
     */
    public List<StockDailyModel> getStockDailyModelList(StockDailyQuery stockDailyQuery);

    /**
     * 查询库存日报领域集合对象（无参数条件）
     *
     * @return List<StockDailyModel> 库存日报领域集合对象
     */
    public List<StockDailyModel> getStockDailyModelList();

    /**
     * 查询库存日报实体对象
     *
     * @param id 主键
     * @return StockDaily 库存日报实体对象
     */
    public StockDaily getOneStockDaily(String id);

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return StockDailyModel 库存日报领域对象
     */
    public StockDailyModel getOneStockDailyModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param stockDailyQuery 库存日报查询参数对象
     * @return StockDailyModel 库存日报领域对象
     */
    public StockDailyModel getOneStockDailyModel(StockDailyQuery stockDailyQuery) throws TooManyResultsException;

    /**
     * 定时任务创建库存快照
     */
    public void createStockDaily();

    /**
     * 查询财务报表
     *
     * @param reportQuery
     * @return
     */
    public List<StockDailyReport> getStockDailyReport(ReportQuery reportQuery);
}
