package com.xmomen.module.resource.controller;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.logger.Log;
import com.xmomen.module.resource.model.ResourceQuery;
import com.xmomen.module.resource.model.ResourceModel;
import com.xmomen.module.resource.service.ResourceService;

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
 * @date 2017-4-10 23:26:20
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    /**
     * 资源目录列表
     *
     * @param limit      每页结果数
     * @param offset     页码
     * @param id         主键
     * @param ids        主键数组
     * @param excludeIds 不包含主键数组
     * @return Page<ResourceModel> 资源目录领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询资源目录列表")
    public Page<ResourceModel> getResourceList(@RequestParam(value = "limit") Integer limit,
                                               @RequestParam(value = "offset") Integer offset,
                                               @RequestParam(value = "id", required = false) String id,
                                               @RequestParam(value = "entityType", required = false) String entityType,
                                               @RequestParam(value = "entityId", required = false) String entityId,
                                               @RequestParam(value = "ids", required = false) String[] ids,
                                               @RequestParam(value = "excludeIds", required = false) String[] excludeIds) {
        ResourceQuery resourceQuery = new ResourceQuery();
        resourceQuery.setId(id);
        resourceQuery.setExcludeIds(excludeIds);
        resourceQuery.setIds(ids);
        resourceQuery.setEntityType(entityType);
        resourceQuery.setEntityId(entityId);
        return resourceService.getResourceModelPage(limit, offset, resourceQuery);
    }

    /**
     * 查询单个资源目录
     *
     * @param id 主键
     * @return ResourceModel   资源目录领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询资源目录")
    public ResourceModel getResourceById(@PathVariable(value = "id") String id) {
        return resourceService.getOneResourceModel(id);
    }

    /**
     * 新增资源目录
     *
     * @param resourceModel 新增对象参数
     * @return ResourceModel   资源目录领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增资源目录")
    public ResourceModel createResource(@RequestBody @Valid ResourceModel resourceModel) {
        return resourceService.createResource(resourceModel);
    }

    /**
     * 更新资源目录
     *
     * @param id            主键
     * @param resourceModel 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新资源目录")
    public void updateResource(@PathVariable(value = "id") String id,
                               @RequestBody @Valid ResourceModel resourceModel) {
        resourceService.updateResource(resourceModel);
    }

    /**
     * 删除资源目录
     *
     * @param id 主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个资源目录")
    public void deleteResource(@PathVariable(value = "id") String id) {
        resourceService.deleteResource(id);
    }

    /**
     * 删除资源目录
     *
     * @param ids 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除资源目录")
    public void deleteResources(@RequestParam(value = "ids") String[] ids) {
        resourceService.deleteResource(ids);
    }


}
