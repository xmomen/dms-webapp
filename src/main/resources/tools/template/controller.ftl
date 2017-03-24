package ${targetPackage};

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.Page;
<#--import com.xmomen.framework.poi.ExcelImportValidFailException;-->
import com.xmomen.module.logger.Log;
import ${modulePackage}.model.${domainObjectClassName}Query;
import ${modulePackage}.model.${domainObjectClassName}Model;
import ${modulePackage}.service.${domainObjectClassName}Service;

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

<#include "header.ftl">
@RestController
@RequestMapping(value = "${restMapping}")
public class ${domainObjectClassName}Controller {

    @Autowired
    ${domainObjectClassName}Service ${domainObjectName}Service;

    /**
     * ${tableComment}列表
     * @param   limit           每页结果数
     * @param   offset          页码
<#if keywordColumns?exists >
     * @param   keyword         关键字
</#if>
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<${domainObjectClassName}Model> ${tableComment}领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询${tableComment}列表")
    public Page<${domainObjectClassName}Model> get${domainObjectClassName}List(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                            <#if keywordColumns?exists >
                                  @RequestParam(value = "keyword", required = false) String keyword,
                            </#if>
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        ${domainObjectClassName}Query ${domainObjectName}Query = new ${domainObjectClassName}Query();
        ${domainObjectName}Query.setId(id);
        ${domainObjectName}Query.setExcludeIds(excludeIds);
        ${domainObjectName}Query.setIds(ids);
    <#if keywordColumns?exists >
        ${domainObjectName}Query.setKeyword(keyword);
    </#if>
        return ${domainObjectName}Service.get${domainObjectClassName}ModelPage(limit, offset, ${domainObjectName}Query);
    }

    /**
     * 查询单个${tableComment}
     * @param   id  主键
     * @return  ${domainObjectClassName}Model   ${tableComment}领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询${tableComment}")
    public ${domainObjectClassName}Model get${domainObjectClassName}ById(@PathVariable(value = "id") String id){
        return ${domainObjectName}Service.getOne${domainObjectClassName}Model(id);
    }

    /**
     * 新增${tableComment}
     * @param   ${domainObjectName}Model  新增对象参数
     * @return  ${domainObjectClassName}Model   ${tableComment}领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增${tableComment}")
    public ${domainObjectClassName}Model create${domainObjectClassName}(@RequestBody @Valid ${domainObjectClassName}Model ${domainObjectName}Model) {
        return ${domainObjectName}Service.create${domainObjectClassName}(${domainObjectName}Model);
    }

    /**
     * 更新${tableComment}
     * @param id                            主键
     * @param ${domainObjectName}Model 更新对象参数
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新${tableComment}")
    public void update${domainObjectClassName}(@PathVariable(value = "id") String id,
                           @RequestBody @Valid ${domainObjectClassName}Model ${domainObjectName}Model){
        ${domainObjectName}Service.update${domainObjectClassName}(${domainObjectName}Model);
    }

    /**
     *  删除${tableComment}
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个${tableComment}")
    public void delete${domainObjectClassName}(@PathVariable(value = "id") String id){
        ${domainObjectName}Service.delete${domainObjectClassName}(id);
    }

    /**
     *  删除${tableComment}
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除${tableComment}")
    public void delete${domainObjectClassName}s(@RequestParam(value = "ids") String[] ids){
        ${domainObjectName}Service.delete${domainObjectClassName}(ids);
    }

    <#--/**-->
    <#--* 导出-->
    <#--* @param id     主键-->
    <#--* @param ids    包含的主键数组-->
    <#--* @param excludeIds     排除的主键数组-->
<#--<#if keywordColumns?exists >-->
    <#--* @param keyword    关键字-->
<#--</#if>-->
    <#--* @param modelMap   modelMap对象-->
    <#--* @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图-->
    <#--*/-->
    <#--@RequestMapping(value="/export", method = RequestMethod.GET)-->
    <#--public ModelAndView export${domainObjectClassName}(-->
            <#--@RequestParam(value = "id", required = false) String id,-->
            <#--@RequestParam(value = "ids", required = false) String[] ids,-->
            <#--@RequestParam(value = "excludeIds", required = false) String[] excludeIds,-->
        <#--<#if keywordColumns?exists >-->
            <#--@RequestParam(value = "keyword", required = false) String keyword,-->
        <#--</#if>-->
            <#--ModelMap modelMap) {-->
        <#--${domainObjectClassName}Query ${domainObjectName}Query = new ${domainObjectClassName}Query();-->
        <#--${domainObjectName}Query.setId(id);-->
        <#--${domainObjectName}Query.setExcludeIds(excludeIds);-->
        <#--${domainObjectName}Query.setIds(ids);-->
    <#--<#if keywordColumns?exists >-->
        <#--${domainObjectName}Query.setKeyword(keyword);-->
    <#--</#if>-->
        <#--List<${domainObjectClassName}Model> ${domainObjectName}List = ${domainObjectName}Service.get${domainObjectClassName}ModelList(${domainObjectName}Query);-->
        <#--modelMap.put(NormalExcelConstants.FILE_NAME, "${tableComment}信息");-->
        <#--modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());-->
        <#--modelMap.put(NormalExcelConstants.CLASS, ${domainObjectClassName}Model.class);-->
        <#--modelMap.put(NormalExcelConstants.DATA_LIST, ${domainObjectName}List);-->
        <#--return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);-->
    <#--}-->

    <#--/**-->
    <#--* 上传文件-->
    <#--* @param file  上传的Excel文件-->
    <#--*/-->
    <#--@RequestMapping(value = "/import", method = RequestMethod.POST)-->
    <#--public void importPermissions(@RequestParam(value = "file") MultipartFile file) throws BusinessException {-->
        <#--if(file.isEmpty()){-->
            <#--throw new BusinessException("上传失败，文件为空");-->
        <#--}-->
        <#--ImportParams importParams = new ImportParams();-->
        <#--importParams.setNeedVerfiy(true);-->
        <#--importParams.setTitleRows(0);-->
        <#--importParams.setHeadRows(1);-->
        <#--InputStream inputStream = null;-->
        <#--List<${domainObjectClassName}Model> list = null;-->
        <#--ExcelImportResult excelImportResult = null;-->
        <#--try {-->
            <#--inputStream = file.getInputStream();-->
            <#--excelImportResult = ExcelImportUtil.importExcelVerify(inputStream, ${domainObjectClassName}Model.class, importParams);-->
        <#--} catch (ExcelImportException e){-->
            <#--throw new BusinessException(e.getMessage(), e);-->
        <#--} catch (IOException e) {-->
            <#--throw new BusinessException(e.getMessage(), e);-->
        <#--} catch (Exception e) {-->
            <#--throw new BusinessException(e.getMessage(), e);-->
        <#--} finally {-->
            <#--IOUtils.closeQuietly(inputStream);-->
        <#--}-->
        <#--if(excelImportResult.isVerfiyFail()){-->
            <#--throw new ExcelImportValidFailException(excelImportResult);-->
        <#--}-->
        <#--list = excelImportResult.getList();-->
        <#--${domainObjectName}Service.create${domainObjectClassName}s(list);-->
    <#--}-->


}
