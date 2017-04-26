package com.xmomen.module.payrecord.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.logger.Log;
import com.xmomen.module.payrecord.model.PayRecordQuery;
import com.xmomen.module.payrecord.model.PayRecordModel;
import com.xmomen.module.payrecord.service.PayRecordService;

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
 * @author  tanxinzheng
 * @date    2017-4-26 22:45:12
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/payRecord")
public class PayRecordController {

    @Autowired
    PayRecordService payRecordService;

    /**
     * 支付凭证列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<PayRecordModel> 支付凭证领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询支付凭证列表")
    public Page<PayRecordModel> getPayRecordList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        PayRecordQuery payRecordQuery = new PayRecordQuery();
        payRecordQuery.setId(id);
        payRecordQuery.setExcludeIds(excludeIds);
        payRecordQuery.setIds(ids);
        return payRecordService.getPayRecordModelPage(limit, offset, payRecordQuery);
    }

    /**
     * 查询单个支付凭证
     * @param   id  主键
     * @return  PayRecordModel   支付凭证领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询支付凭证")
    public PayRecordModel getPayRecordById(@PathVariable(value = "id") String id){
        return payRecordService.getOnePayRecordModel(id);
    }

    /**
     * 新增支付凭证
     * @param   payRecordModel  新增对象参数
     * @return  PayRecordModel   支付凭证领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增支付凭证")
    public PayRecordModel createPayRecord(@RequestBody @Valid PayRecordModel payRecordModel) {
        return payRecordService.createPayRecord(payRecordModel);
    }

    /**
     * 更新支付凭证
     * @param id                            主键
     * @param payRecordModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新支付凭证")
    public void updatePayRecord(@PathVariable(value = "id") String id,
                           @RequestBody @Valid PayRecordModel payRecordModel){
        payRecordService.updatePayRecord(payRecordModel);
    }

    /**
     *  删除支付凭证
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个支付凭证")
    public void deletePayRecord(@PathVariable(value = "id") String id){
        payRecordService.deletePayRecord(id);
    }

    /**
     *  删除支付凭证
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除支付凭证")
    public void deletePayRecords(@RequestParam(value = "ids") String[] ids){
        payRecordService.deletePayRecord(ids);
    }



}
