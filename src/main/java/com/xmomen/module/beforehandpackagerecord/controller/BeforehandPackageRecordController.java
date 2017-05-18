package com.xmomen.module.beforehandpackagerecord.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.logger.Log;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordQuery;
import com.xmomen.module.beforehandpackagerecord.model.BeforehandPackageRecordModel;
import com.xmomen.module.beforehandpackagerecord.service.BeforehandPackageRecordService;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
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
 * @date 2017-5-18 23:36:38
 */
@RestController
@RequestMapping(value = "/beforehandPackageRecord")
public class BeforehandPackageRecordController {

    @Autowired
    BeforehandPackageRecordService beforehandPackageRecordService;

    /**
     * 预包装记录表列表
     *
     * @param limit      每页结果数
     * @param offset     页码
     * @param id         主键
     * @param ids        主键数组
     * @param excludeIds 不包含主键数组
     * @return Page<BeforehandPackageRecordModel> 预包装记录表领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询预包装记录表列表")
    public Page<BeforehandPackageRecordModel> getBeforehandPackageRecordList(@RequestParam(value = "limit") Integer limit,
                                                                             @RequestParam(value = "offset") Integer offset,
                                                                             @RequestParam(value = "id", required = false) String id,
                                                                             @RequestParam(value = "ids", required = false) String[] ids,
                                                                             @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        BeforehandPackageRecordQuery beforehandPackageRecordQuery = new BeforehandPackageRecordQuery();
        beforehandPackageRecordQuery.setId(id);
        beforehandPackageRecordQuery.setExcludeIds(excludeIds);
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        beforehandPackageRecordQuery.setInsertUserId(userId);
        beforehandPackageRecordQuery.setIds(ids);
        return beforehandPackageRecordService.getBeforehandPackageRecordModelPage(limit, offset, beforehandPackageRecordQuery);
    }

    /**
     * 查询单个预包装记录表
     *
     * @param id 主键
     * @return BeforehandPackageRecordModel   预包装记录表领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询预包装记录表")
    public BeforehandPackageRecordModel getBeforehandPackageRecordById(@PathVariable(value = "id") String id) {
        return beforehandPackageRecordService.getOneBeforehandPackageRecordModel(id);
    }

    /**
     * 新增预包装记录表
     *
     * @param beforehandPackageRecordModel 新增对象参数
     * @return BeforehandPackageRecordModel   预包装记录表领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增预包装记录表")
    public BeforehandPackageRecordModel createBeforehandPackageRecord(@RequestBody @Valid BeforehandPackageRecordModel beforehandPackageRecordModel) {
        return beforehandPackageRecordService.createBeforehandPackageRecord(beforehandPackageRecordModel);
    }

    /**
     * 更新预包装记录表
     *
     * @param id                           主键
     * @param beforehandPackageRecordModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新预包装记录表")
    public void updateBeforehandPackageRecord(@PathVariable(value = "id") String id,
                                              @RequestBody @Valid BeforehandPackageRecordModel beforehandPackageRecordModel) {
        beforehandPackageRecordService.updateBeforehandPackageRecord(beforehandPackageRecordModel);
    }

    /**
     * 删除预包装记录表
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个预包装记录表")
    public void deleteBeforehandPackageRecord(@PathVariable(value = "id") String id) {
        beforehandPackageRecordService.deleteBeforehandPackageRecord(id);
    }

    /**
     * 删除预包装记录表
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除预包装记录表")
    public void deleteBeforehandPackageRecords(@RequestParam(value = "ids") String[] ids) {
        beforehandPackageRecordService.deleteBeforehandPackageRecord(ids);
    }


}
