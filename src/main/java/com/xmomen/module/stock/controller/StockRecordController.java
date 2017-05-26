package com.xmomen.module.stock.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.logger.Log;
import com.xmomen.module.stock.model.StockRecordQuery;
import com.xmomen.module.stock.model.StockRecordModel;
import com.xmomen.module.stock.service.StockRecordService;

import org.apache.commons.io.IOUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-13 12:48:23
 */
@RestController
@RequestMapping(value = "/stockRecord")
public class StockRecordController {

    @Autowired
    StockRecordService stockRecordService;

    /**
     * 商品库存列表
     *
     * @param limit      每页结果数
     * @param offset     页码
     * @param id         主键
     * @param ids        主键数组
     * @param excludeIds 不包含主键数组
     * @return Page<StockRecordModel> 商品库存领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询商品库存列表")
    public Page<StockRecordModel> getStockRecordList(@RequestParam(value = "limit") Integer limit,
                                                     @RequestParam(value = "offset") Integer offset,
                                                     @RequestParam(value = "id", required = false) String id,
                                                     @RequestParam(value = "ids", required = false) String[] ids,
                                                     @RequestParam(value = "stockId", required = false) String stockId,
                                                     @RequestParam(value = "changeType", required = false) Integer changeType,
                                                     @RequestParam(value = "dailyDate", required = false) String dailyDate,
                                                     @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        StockRecordQuery stockRecordQuery = new StockRecordQuery();
        stockRecordQuery.setId(id);
        stockRecordQuery.setExcludeIds(excludeIds);
        stockRecordQuery.setIds(ids);
        stockRecordQuery.setStockId(stockId);
        stockRecordQuery.setChangeType(changeType);
        stockRecordQuery.setDailyDate(dailyDate);
        return stockRecordService.getStockRecordModelPage(limit, offset, stockRecordQuery);
    }

    /**
     * 查询单个商品库存
     *
     * @param id 主键
     * @return StockRecordModel   商品库存领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询商品库存")
    public StockRecordModel getStockRecordById(@PathVariable(value = "id") String id) {
        return stockRecordService.getOneStockRecordModel(id);
    }

    /**
     * 新增商品库存
     *
     * @param stockRecordModel 新增对象参数
     * @return StockRecordModel   商品库存领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增商品库存")
    public StockRecordModel createStockRecord(@RequestBody @Valid StockRecordModel stockRecordModel) {
        return stockRecordService.createStockRecord(stockRecordModel);
    }

    /**
     * 更新商品库存
     *
     * @param id               主键
     * @param stockRecordModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新商品库存")
    public void updateStockRecord(@PathVariable(value = "id") String id,
                                  @RequestBody @Valid StockRecordModel stockRecordModel) {
        stockRecordService.updateStockRecord(stockRecordModel);
    }

    /**
     * 删除商品库存
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个商品库存")
    public void deleteStockRecord(@PathVariable(value = "id") String id) {
        stockRecordService.deleteStockRecord(id);
    }

    /**
     * 删除商品库存
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除商品库存")
    public void deleteStockRecords(@RequestParam(value = "ids") String[] ids) {
        stockRecordService.deleteStockRecord(ids);
    }


}
