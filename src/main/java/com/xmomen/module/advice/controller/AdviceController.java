package com.xmomen.module.advice.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.logger.Log;
import com.xmomen.module.advice.model.AdviceQuery;
import com.xmomen.module.advice.model.AdviceModel;
import com.xmomen.module.advice.service.AdviceService;

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
 * @date 2017-5-14 20:05:05
 */
@RestController
@RequestMapping(value = "/advice")
public class AdviceController {

    @Autowired
    AdviceService adviceService;

    /**
     * 快报列表
     *
     * @param limit      每页结果数
     * @param offset     页码
     * @param id         主键
     * @param ids        主键数组
     * @param excludeIds 不包含主键数组
     * @return Page<AdviceModel> 快报领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询快报列表")
    public Page<AdviceModel> getAdviceList(@RequestParam(value = "limit") Integer limit,
                                           @RequestParam(value = "offset") Integer offset,
                                           @RequestParam(value = "id", required = false) String id,
                                           @RequestParam(value = "ids", required = false) String[] ids,
                                           @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        AdviceQuery adviceQuery = new AdviceQuery();
        adviceQuery.setId(id);
        adviceQuery.setExcludeIds(excludeIds);
        adviceQuery.setIds(ids);
        return adviceService.getAdviceModelPage(limit, offset, adviceQuery);
    }

    /**
     * 查询单个快报
     *
     * @param id 主键
     * @return AdviceModel   快报领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询快报")
    public AdviceModel getAdviceById(@PathVariable(value = "id") String id) {
        return adviceService.getOneAdviceModel(id);
    }

    /**
     * 新增快报
     *
     * @param adviceModel 新增对象参数
     * @return AdviceModel   快报领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增快报")
    public AdviceModel createAdvice(@RequestBody @Valid AdviceModel adviceModel) {
        return adviceService.createAdvice(adviceModel);
    }

    /**
     * 更新快报
     *
     * @param id          主键
     * @param adviceModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新快报")
    public void updateAdvice(@PathVariable(value = "id") String id,
                             @RequestBody @Valid AdviceModel adviceModel) {
        adviceService.updateAdvice(adviceModel);
    }

    /**
     * 删除快报
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个快报")
    public void deleteAdvice(@PathVariable(value = "id") String id) {
        adviceService.deleteAdvice(id);
    }

    /**
     * 删除快报
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除快报")
    public void deleteAdvices(@RequestParam(value = "ids") String[] ids) {
        adviceService.deleteAdvice(ids);
    }


}
