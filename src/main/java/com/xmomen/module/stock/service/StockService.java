package com.xmomen.module.stock.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.stock.model.StockQuery;
import com.xmomen.module.stock.model.StockModel;
import com.xmomen.module.stock.entity.Stock;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-5-13 12:49:20
 * @version 1.0.0
 */
public interface StockService {

    /**
     * 新增商品库存记录
     * @param  stockModel   新增商品库存记录对象参数
     * @return  StockModel    商品库存记录领域对象
     */
    public StockModel createStock(StockModel stockModel);

    /**
     * 新增商品库存记录实体对象
     * @param   stock 新增商品库存记录实体对象参数
     * @return  Stock 商品库存记录实体对象
     */
    public Stock createStock(Stock stock);

    /**
    * 批量新增商品库存记录
    * @param StockModel     新增商品库存记录对象集合参数
    * @return List<StockModel>    商品库存记录领域对象集合
    */
    List<StockModel> createStocks(List<StockModel> stockModels);

    /**
     * 更新商品库存记录
     * @param stockModel    更新商品库存记录对象参数
     */
    public void updateStock(StockModel stockModel);

    /**
     * 更新商品库存记录实体对象
     * @param   stock 新增商品库存记录实体对象参数
     * @return  Stock 商品库存记录实体对象
     */
    public void updateStock(Stock stock);

    /**
     * 批量删除商品库存记录
     * @param ids   主键数组
     */
    public void deleteStock(String[] ids);

    /**
     * 删除商品库存记录
     * @param id   主键
     */
    public void deleteStock(String id);

    /**
     * 查询商品库存记录领域分页对象（带参数条件）
     * @param stockQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<StockModel>   商品库存记录参数对象
     */
    public Page<StockModel> getStockModelPage(int limit, int offset, StockQuery stockQuery);

    /**
     * 查询商品库存记录领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<StockModel> 商品库存记录领域对象
     */
    public Page<StockModel> getStockModelPage(int limit, int offset);

    /**
     * 查询商品库存记录领域集合对象（带参数条件）
     * @param stockQuery 查询参数对象
     * @return List<StockModel> 商品库存记录领域集合对象
     */
    public List<StockModel> getStockModelList(StockQuery stockQuery);

    /**
     * 查询商品库存记录领域集合对象（无参数条件）
     * @return List<StockModel> 商品库存记录领域集合对象
     */
    public List<StockModel> getStockModelList();

    /**
     * 查询商品库存记录实体对象
     * @param id 主键
     * @return Stock 商品库存记录实体对象
     */
    public Stock getOneStock(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return StockModel 商品库存记录领域对象
     */
    public StockModel getOneStockModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param stockQuery 商品库存记录查询参数对象
     * @return StockModel 商品库存记录领域对象
     */
    public StockModel getOneStockModel(StockQuery stockQuery) throws TooManyResultsException;
}
