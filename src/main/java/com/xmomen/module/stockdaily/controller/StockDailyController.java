package com.xmomen.module.stockdaily.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.logger.Log;
import com.xmomen.module.stockdaily.model.StockDailyQuery;
import com.xmomen.module.stockdaily.model.StockDailyModel;
import com.xmomen.module.stockdaily.service.StockDailyService;

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
 * @date 2017-5-26 21:56:23
 */
@RestController
@RequestMapping(value = "/stockDaily")
public class StockDailyController {

    @Autowired
    StockDailyService stockDailyService;

    /**
     * 库存日报列表
     *
     * @param limit      每页结果数
     * @param offset     页码
     * @param id         主键
     * @param ids        主键数组
     * @param excludeIds 不包含主键数组
     * @return Page<StockDailyModel> 库存日报领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询库存日报列表")
    public Page<StockDailyModel> getStockDailyList(@RequestParam(value = "limit") Integer limit,
                                                   @RequestParam(value = "offset") Integer offset,
                                                   @RequestParam(value = "id", required = false) String id,
                                                   @RequestParam(value = "ids", required = false) String[] ids,
                                                   @RequestParam(value = "dailyDateStart", required = false) String dailyDateStart,
                                                   @RequestParam(value = "dailyDateEnd", required = false) String dailyDateEnd,
                                                   @RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        StockDailyQuery stockDailyQuery = new StockDailyQuery();
        stockDailyQuery.setId(id);
        stockDailyQuery.setExcludeIds(excludeIds);
        stockDailyQuery.setIds(ids);
        stockDailyQuery.setKeyword(keyword);
        stockDailyQuery.setDailyDateStart(dailyDateStart);
        stockDailyQuery.setDailyDateEnd(dailyDateEnd);
        return stockDailyService.getStockDailyModelPage(limit, offset, stockDailyQuery);
    }

    /**
     * 查询单个库存日报
     *
     * @param id 主键
     * @return StockDailyModel   库存日报领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询库存日报")
    public StockDailyModel getStockDailyById(@PathVariable(value = "id") String id) {
        return stockDailyService.getOneStockDailyModel(id);
    }

    /**
     * 新增库存日报
     *
     * @param stockDailyModel 新增对象参数
     * @return StockDailyModel   库存日报领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增库存日报")
    public StockDailyModel createStockDaily(@RequestBody @Valid StockDailyModel stockDailyModel) {
        return stockDailyService.createStockDaily(stockDailyModel);
    }

    /**
     * 更新库存日报
     *
     * @param id              主键
     * @param stockDailyModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新库存日报")
    public void updateStockDaily(@PathVariable(value = "id") String id,
                                 @RequestBody @Valid StockDailyModel stockDailyModel) {
        stockDailyService.updateStockDaily(stockDailyModel);
    }

    /**
     * 删除库存日报
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个库存日报")
    public void deleteStockDaily(@PathVariable(value = "id") String id) {
        stockDailyService.deleteStockDaily(id);
    }

    /**
     * 删除库存日报
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除库存日报")
    public void deleteStockDailys(@RequestParam(value = "ids") String[] ids) {
        stockDailyService.deleteStockDaily(ids);
    }

    /**
     * 手动创建库存快照
     */
    @RequestMapping(value = "/createStockDaily", method = RequestMethod.GET)
    @Log(actionName = "手动创建库存快照")
    public void createStockDaily() {
        stockDailyService.createStockDaily();
    }
}
