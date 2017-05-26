package com.xmomen.module.stock.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.StringUtils;
import com.xmomen.module.core.web.controller.DmsBaseController;
import com.xmomen.module.logger.Log;
import com.xmomen.module.stock.model.StockChange;
import com.xmomen.module.stock.model.StockQuery;
import com.xmomen.module.stock.model.StockModel;
import com.xmomen.module.stock.service.StockService;

import com.xmomen.module.wx.model.AjaxResult;
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
import java.util.Date;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-5-13 12:49:20
 */
@RestController
@RequestMapping(value = "/stock")
public class StockController extends DmsBaseController {

    @Autowired
    StockService stockService;

    /**
     * 商品库存记录列表
     *
     * @param limit      每页结果数
     * @param offset     页码
     * @param id         主键
     * @param ids        主键数组
     * @param excludeIds 不包含主键数组
     * @return Page<StockModel> 商品库存记录领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询商品库存记录列表")
    public Page<StockModel> getStockList(@RequestParam(value = "limit") Integer limit,
                                         @RequestParam(value = "offset") Integer offset,
                                         @RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "id", required = false) String id,
                                         @RequestParam(value = "ids", required = false) String[] ids,
                                         @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
                                         @RequestParam(value = "itemName", required = false) String itemName,
                                         @RequestParam(value = "itemCode", required = false) String itemCode) {
        StockQuery stockQuery = new StockQuery();
        stockQuery.setKeyword(StringUtils.trimToEmpty(keyword));
        stockQuery.setId(id);
        stockQuery.setExcludeIds(excludeIds);
        stockQuery.setIds(ids);
        stockQuery.setItemCode(itemCode);
        stockQuery.setItemName(itemName);
        return stockService.getStockModelPage(limit, offset, stockQuery);
    }

    /**
     * 查询单个商品库存记录
     *
     * @param id 主键
     * @return StockModel   商品库存记录领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询商品库存记录")
    public StockModel getStockById(@PathVariable(value = "id") String id) {
        return stockService.getOneStockModel(id);
    }

    /**
     * 新增商品库存记录
     *
     * @param stockModel 新增对象参数
     * @return StockModel   商品库存记录领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增商品库存记录")
    public StockModel createStock(@RequestBody @Valid StockModel stockModel) {
        stockModel.setInsertUserId(getCurrentUserId());
        stockModel.setUpdateUserId(getCurrentUserId());
        return stockService.createStock(stockModel);
    }

    /**
     * 更新商品库存记录
     *
     * @param id         主键
     * @param stockModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新商品库存记录")
    public void updateStock(@PathVariable(value = "id") String id,
                            @RequestBody @Valid StockModel stockModel) {
        stockModel.setUpdateUserId(getCurrentUserId());
        stockService.updateStock(stockModel);
    }

    /**
     * 库存变更
     *
     * @param id
     * @param stockChange
     */
    @RequestMapping(value = "/{id}/change", method = RequestMethod.PUT)
    @Log(actionName = "更新商品库存记录")
    public void updateStock(@PathVariable(value = "id") String id,
                            @RequestBody @Valid StockChange stockChange) {
        stockChange.setActionBy(getCurrentUserId());
        stockChange.setStockId(id);
        stockService.changeStock(stockChange);
    }


    /**
     * 删除商品库存记录
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个商品库存记录")
    public void deleteStock(@PathVariable(value = "id") String id) {
        stockService.deleteStock(id);
    }

    /**
     * 删除商品库存记录
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除商品库存记录")
    public void deleteStocks(@RequestParam(value = "ids") String[] ids) {
        stockService.deleteStock(ids);
    }

    /**
     * 预包装库存变化
     */
    @RequestMapping(value = "/beforehandPackageChangeStock", method = RequestMethod.GET)
    @Log
    public AjaxResult beforehandPackageChangeStock(@RequestParam(value = "itemId") Integer itemId, @RequestParam(value = "changeStockNum") Integer changeStockNum) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            ajaxResult = this.stockService.changeStockNum(itemId, changeStockNum, "预包装入库", 1);
        } catch (Exception e) {
            ajaxResult.setResult(0);
            ajaxResult.setMessage(e.getMessage());
        }
        return ajaxResult;
    }
}
