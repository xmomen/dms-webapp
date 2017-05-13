package com.xmomen.module.stock.service.impl;

import com.xmomen.module.stock.entity.Stock;
import com.xmomen.module.stock.entity.StockExample;
import com.xmomen.module.stock.mapper.StockMapperExt;
import com.xmomen.module.stock.model.StockCreate;
import com.xmomen.module.stock.model.StockQuery;
import com.xmomen.module.stock.model.StockUpdate;
import com.xmomen.module.stock.model.StockModel;
import com.xmomen.module.stock.service.StockService;
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
 * @date    2017-5-13 12:49:20
 * @version 1.0.0
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增商品库存记录
     *
     * @param stockModel 新增商品库存记录对象参数
     * @return StockModel    商品库存记录领域对象
     */
    @Override
    @Transactional
    public StockModel createStock(StockModel stockModel) {
        Stock stock = createStock(stockModel.getEntity());
        if(stock != null){
            return getOneStockModel(stock.getId());
        }
        return null;
    }

    /**
     * 新增商品库存记录实体对象
     *
     * @param stock 新增商品库存记录实体对象参数
     * @return Stock 商品库存记录实体对象
     */
    @Override
    @Transactional
    public Stock createStock(Stock stock) {
        return mybatisDao.insertByModel(stock);
    }

    /**
    * 批量新增商品库存记录
    *
    * @param stockModels 新增商品库存记录对象集合参数
    * @return List<StockModel>    商品库存记录领域对象集合
    */
    @Override
    @Transactional
    public List<StockModel> createStocks(List<StockModel> stockModels) {
        List<StockModel> stockModelList = null;
        for (StockModel stockModel : stockModels) {
            stockModel = createStock(stockModel);
            if(stockModel != null){
                if(stockModelList == null){
                    stockModelList = new ArrayList<>();
                }
                stockModelList.add(stockModel);
            }
        }
        return stockModelList;
    }

    /**
     * 更新商品库存记录
     *
     * @param stockModel 更新商品库存记录对象参数
     */
    @Override
    @Transactional
    public void updateStock(StockModel stockModel) {
        mybatisDao.update(stockModel.getEntity());
    }

    /**
     * 更新商品库存记录实体对象
     *
     * @param stock 新增商品库存记录实体对象参数
     * @return Stock 商品库存记录实体对象
     */
    @Override
    @Transactional
    public void updateStock(Stock stock) {
        mybatisDao.update(stock);
    }

    /**
     * 删除商品库存记录
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteStock(String[] ids) {
        StockExample stockExample = new StockExample();
        stockExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(stockExample);
    }

    /**
    * 删除商品库存记录
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteStock(String id) {
        mybatisDao.deleteByPrimaryKey(Stock.class, id);
    }

    /**
     * 查询商品库存记录领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param stockQuery 查询参数
     * @return Page<StockModel>   商品库存记录参数对象
     */
    @Override
    public Page<StockModel> getStockModelPage(int limit, int offset, StockQuery stockQuery) {
        return (Page<StockModel>) mybatisDao.selectPage(StockMapperExt.StockMapperNameSpace + "getStockModel", stockQuery, limit, offset);
    }

    /**
     * 查询商品库存记录领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<StockModel> 商品库存记录领域对象
     */
    @Override
    public Page<StockModel> getStockModelPage(int limit, int offset) {
        return (Page<StockModel>) mybatisDao.selectPage(StockMapperExt.StockMapperNameSpace + "getStockModel", null, limit, offset);
    }

    /**
     * 查询商品库存记录领域集合对象（带参数条件）
     *
     * @param stockQuery 查询参数对象
     * @return List<StockModel> 商品库存记录领域集合对象
     */
    @Override
    public List<StockModel> getStockModelList(StockQuery stockQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(StockMapperExt.StockMapperNameSpace + "getStockModel", stockQuery);
    }

    /**
     * 查询商品库存记录领域集合对象（无参数条件）
     *
     * @return List<StockModel> 商品库存记录领域集合对象
     */
    @Override
    public List<StockModel> getStockModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(StockMapperExt.StockMapperNameSpace + "getStockModel");
    }

    /**
     * 查询商品库存记录实体对象
     *
     * @param id 主键
     * @return Stock 商品库存记录实体对象
     */
    @Override
    public Stock getOneStock(String id) {
        return mybatisDao.selectByPrimaryKey(Stock.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return StockModel 商品库存记录领域对象
     */
    @Override
    public StockModel getOneStockModel(String id) {
        StockQuery stockQuery = new StockQuery();
        stockQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(StockMapperExt.StockMapperNameSpace + "getStockModel", stockQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param stockQuery 商品库存记录查询参数对象
     * @return StockModel 商品库存记录领域对象
     */
    @Override
    public StockModel getOneStockModel(StockQuery stockQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(StockMapperExt.StockMapperNameSpace + "getStockModel", stockQuery);
    }
}
