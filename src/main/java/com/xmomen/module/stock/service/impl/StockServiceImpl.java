package com.xmomen.module.stock.service.impl;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecord;
import com.xmomen.module.stock.entity.Stock;
import com.xmomen.module.stock.entity.StockExample;
import com.xmomen.module.stock.entity.StockRecord;
import com.xmomen.module.stock.mapper.StockMapperExt;
import com.xmomen.module.stock.model.*;
import com.xmomen.module.stock.service.StockRecordService;
import com.xmomen.module.stock.service.StockService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.wx.model.AjaxResult;
import com.xmomen.module.wx.util.DateUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-13 12:49:20
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    StockRecordService stockRecordService;

    /**
     * 新增商品库存记录
     *
     * @param stockModel 新增商品库存记录对象参数
     * @return StockModel    商品库存记录领域对象
     */
    @Override
    @Transactional
    public StockModel createStock(StockModel stockModel) {
        StockExample stockExample = new StockExample();
        stockExample.createCriteria().andItemIdEqualTo(stockModel.getItemId());
        int num = mybatisDao.countByExample(stockExample);
        if (num > 0) {
            throw new IllegalArgumentException("此商品已有库存信息");
        }
        stockModel.setInsertDate(new Date());
        stockModel.setUpdateDate(new Date());
        Stock stock = createStock(stockModel.getEntity());

        //添加记录
        StockRecord stockRecord = new StockRecord();
        stockRecord.setInsertDate(new Date());
        stockRecord.setInsertUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        stockRecord.setUpdateDate(new Date());
        stockRecord.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        stockRecord.setStockId(stock.getId());
        stockRecord.setRemark("库存初始化");
        stockRecord.setChangType(1);
        stockRecord.setChangeNum(stock.getStockNum());
        this.mybatisDao.save(stockRecord);

        if (stock != null) {
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
            if (stockModel != null) {
                if (stockModelList == null) {
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
        stockModel.setUpdateDate(new Date());
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
     * 库存变更
     *
     * @param stockChange
     */
    @Transactional
    @Override
    public void changeStock(StockChange stockChange) {
        Stock stock = mybatisDao.selectByPrimaryKey(Stock.class, stockChange.getStockId());
        if (stock == null) {
            throw new BusinessException("未找到匹配的库存信息");
        }
        stock.setUpdateUserId(stockChange.getActionBy());
        stock.setUpdateDate(new Date());
        StockRecord stockRecord = new StockRecord();
        stockRecord.setInsertDate(new Date());
        stockRecord.setInsertUserId(stockChange.getActionBy());
        stockRecord.setUpdateDate(new Date());
        stockRecord.setUpdateUserId(stockChange.getActionBy());
        stockRecord.setStockId(stockChange.getStockId());
        if (AppConstants.STOCK_CHANGE_TYPE_IN == stockChange.getType()) {
            stockRecord.setChangeNum(stockChange.getNumber());
            //1-入库，2-出库，3-取消退款入库 4-破损 5-核销
            stockRecord.setChangType(1);
            stockRecord.setRemark("手工入库");
            stock.setStockNum(stock.getStockNum() + stockChange.getNumber());
        }
        else if (AppConstants.STOCK_CHANGE_TYPE_BROKEN == stockChange.getType()) {
            Integer num = stock.getStockNum() - stockChange.getNumber();
            if (num < 0) {
                throw new BusinessException("请输入小于库存数量的破损数值");
            }
            //1-入库，2-出库，3-取消退款入库 4-破损 5-核销
            stockRecord.setChangType(4);
            stockRecord.setRemark("破损");
            stockRecord.setChangeNum(stockChange.getNumber() * -1);
            stock.setStockNum(num);
        }
        else if (AppConstants.STOCK_CHANGE_TYPE_CANCEL == stockChange.getType()) {
            Integer num = stock.getStockNum() - stockChange.getNumber();
            if (num < 0) {
                throw new BusinessException("请输入小于库存数量的核销数值");
            }
            //1-入库，2-出库，3-取消退款入库 4-破损 5-核销
            stockRecord.setChangType(5);
            stockRecord.setRemark("核销");
            stockRecord.setChangeNum(stockChange.getNumber() * -1);
            stock.setStockNum(num);
        }
        mybatisDao.update(stock);
        stockRecordService.createStockRecord(stockRecord);
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
     * @param limit      每页最大数
     * @param offset     页码
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

    /**
     * 库存校验
     *
     * @param itemId  商品ID
     * @param needNum 数量
     * @return true-库存充足 false-库存不足
     */
    public boolean checkStock(Integer itemId, Integer needNum) {
        //查询商品库存是否存在
        Stock stock = new Stock();
        stock.setItemId(itemId);
        List<Stock> stockList = mybatisDao.selectByModel(stock);
        //库存存在
        if (stockList.size() > 0) {
            stock = stockList.get(0);
            if (stock.getStockNum() >= needNum) {
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * 库存变化表
     *
     * @param itemId         商品id
     * @param changeStockNum 变化数量 负数表示扣除
     * @param remark         备注
     * @param changeType     1-入库（预包装，手工入库），2-出库，3-取消退款入库 4-破损 5-核销
     * @return
     */
    @Transactional
    public AjaxResult changeStockNum(Integer itemId, Integer changeStockNum, String remark, Integer changeType) throws BusinessException {
        return changeStockNum(itemId, changeStockNum, null, remark, changeType);
    }

    /**
     * 库存变化表(订单）
     *
     * @param itemId         商品id
     * @param changeStockNum 变化数量 负数表示扣除
     * @param orderId        订单id
     * @param remark         备注
     * @param changeType     1-入库（预包装，手工入库），2-出库，3-取消退款入库 4-破损 5-核销
     * @return
     */
    @Transactional
    public AjaxResult changeStockNum(Integer itemId, Integer changeStockNum, Integer orderId, String remark, Integer changeType) throws BusinessException {
        AjaxResult ajaxResult = new AjaxResult();
        //查询商品库存是否存在
        Stock stock = new Stock();
        stock.setItemId(itemId);
        List<Stock> stockList = mybatisDao.selectByModel(stock);
        //库存存在
        if (stockList.size() > 0) {
            stock = stockList.get(0);
            //变更库存

            //如果是扣除 判断库存是否够
            if (changeStockNum < 0) {
                if (stock.getStockNum() + changeStockNum < 0) {
                    throw new BusinessException("库存不够，下单失败。");
                }
            }
            stock.setStockNum(stock.getStockNum() + changeStockNum);
            mybatisDao.updateByModel(stock);
        }
        //无库存记录 新增
        else {
            if (changeStockNum > 0) {
                stock.setInsertDate(DateUtils.getNowDate());
                stock.setInsertUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                stock.setUpdateDate(DateUtils.getNowDate());
                stock.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
                stock.setStockNum(changeStockNum);
                stock.setWarningNum(0);
                mybatisDao.save(stock);
            }
            else {
                throw new BusinessException("无库存不能扣除。");
            }
        }
        //添加变更记录
        StockRecord stockRecord = new StockRecord();
        stockRecord.setChangeNum(changeStockNum);
        //1-入库（预包装，手工入库），2-出库，3-取消退款入库 4-破损 5-核销
        stockRecord.setChangType(changeType);
        stockRecord.setInsertDate(DateUtils.getNowDate());
        stockRecord.setInsertUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        stockRecord.setUpdateDate(DateUtils.getNowDate());
        stockRecord.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
        stockRecord.setRefOrderId(orderId);
        stockRecord.setRemark(remark);
        stockRecord.setStockId(stock.getId());
        mybatisDao.save(stockRecord);

        //预包装添加包装记录
        if (remark.equals("预包装入库")) {
            //添加预包装记录
            BeforehandPackageRecord beforehandPackageRecord = new BeforehandPackageRecord();
            beforehandPackageRecord.setInsertDate(DateUtils.getNowDate());
            beforehandPackageRecord.setInsertUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
            beforehandPackageRecord.setUpdateDate(DateUtils.getNowDate());
            beforehandPackageRecord.setUpdateUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY));
            beforehandPackageRecord.setCdItemId(itemId);
            beforehandPackageRecord.setPackageNum(changeStockNum);
            mybatisDao.save(beforehandPackageRecord);
        }

        ajaxResult.setResult(1);
        ajaxResult.setMessage("操作成功");
        return ajaxResult;
    }

}
