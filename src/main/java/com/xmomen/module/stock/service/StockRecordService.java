package com.xmomen.module.stock.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.stock.model.StockRecordQuery;
import com.xmomen.module.stock.model.StockRecordModel;
import com.xmomen.module.stock.entity.StockRecord;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-5-13 12:48:23
 * @version 1.0.0
 */
public interface StockRecordService {

    /**
     * 新增商品库存
     * @param  stockRecordModel   新增商品库存对象参数
     * @return  StockRecordModel    商品库存领域对象
     */
    public StockRecordModel createStockRecord(StockRecordModel stockRecordModel);

    /**
     * 新增商品库存实体对象
     * @param   stockRecord 新增商品库存实体对象参数
     * @return  StockRecord 商品库存实体对象
     */
    public StockRecord createStockRecord(StockRecord stockRecord);

    /**
    * 批量新增商品库存
    * @param StockRecordModel     新增商品库存对象集合参数
    * @return List<StockRecordModel>    商品库存领域对象集合
    */
    List<StockRecordModel> createStockRecords(List<StockRecordModel> stockRecordModels);

    /**
     * 更新商品库存
     * @param stockRecordModel    更新商品库存对象参数
     */
    public void updateStockRecord(StockRecordModel stockRecordModel);

    /**
     * 更新商品库存实体对象
     * @param   stockRecord 新增商品库存实体对象参数
     * @return  StockRecord 商品库存实体对象
     */
    public void updateStockRecord(StockRecord stockRecord);

    /**
     * 批量删除商品库存
     * @param ids   主键数组
     */
    public void deleteStockRecord(String[] ids);

    /**
     * 删除商品库存
     * @param id   主键
     */
    public void deleteStockRecord(String id);

    /**
     * 查询商品库存领域分页对象（带参数条件）
     * @param stockRecordQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<StockRecordModel>   商品库存参数对象
     */
    public Page<StockRecordModel> getStockRecordModelPage(int limit, int offset, StockRecordQuery stockRecordQuery);

    /**
     * 查询商品库存领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<StockRecordModel> 商品库存领域对象
     */
    public Page<StockRecordModel> getStockRecordModelPage(int limit, int offset);

    /**
     * 查询商品库存领域集合对象（带参数条件）
     * @param stockRecordQuery 查询参数对象
     * @return List<StockRecordModel> 商品库存领域集合对象
     */
    public List<StockRecordModel> getStockRecordModelList(StockRecordQuery stockRecordQuery);

    /**
     * 查询商品库存领域集合对象（无参数条件）
     * @return List<StockRecordModel> 商品库存领域集合对象
     */
    public List<StockRecordModel> getStockRecordModelList();

    /**
     * 查询商品库存实体对象
     * @param id 主键
     * @return StockRecord 商品库存实体对象
     */
    public StockRecord getOneStockRecord(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return StockRecordModel 商品库存领域对象
     */
    public StockRecordModel getOneStockRecordModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param stockRecordQuery 商品库存查询参数对象
     * @return StockRecordModel 商品库存领域对象
     */
    public StockRecordModel getOneStockRecordModel(StockRecordQuery stockRecordQuery) throws TooManyResultsException;
}
