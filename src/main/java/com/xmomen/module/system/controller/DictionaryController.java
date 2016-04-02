package com.xmomen.module.system.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.system.entity.SysDictionary;
import com.xmomen.module.system.entity.SysDictionaryParameter;
import com.xmomen.module.system.model.CreateDictionary;
import com.xmomen.module.system.model.UpdateDictionary;
import com.xmomen.module.system.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    /**
     *  数据字典列表
     * @param group_id
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(value = "/dictionary/{group_id}/child", method = RequestMethod.GET)
    @Log(actionName = "查询数据字典")
    public Page<SysDictionary> getList(
            @PathVariable(value = "group_id") Integer group_id,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset){
        return dictionaryService.getDictionaryList(group_id, limit, offset);
    }

    /**
     *  数据字典列表
     * @param id
     */
    @RequestMapping(value = "/dictionary/{group_id}/child/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询数据字典")
    public SysDictionaryParameter get(@PathVariable(value = "group_id") Integer group_id,
                             @PathVariable(value = "id") Integer id){
        return dictionaryService.getDictionary(id);
    }

    /**
     * 新增数据字典
     * @param group_id
     * @param createDictionary
     * @param bindingResult
     * @return
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/dictionary/{group_id}/child", method = RequestMethod.POST)
    @Log(actionName = "新增数据字典")
    public SysDictionaryParameter create(@PathVariable(value = "group_id") Integer group_id,
            @RequestBody @Valid CreateDictionary createDictionary, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysDictionaryParameter sysDictionaryParameter = new SysDictionaryParameter();
        sysDictionaryParameter.setSysDictionaryId(createDictionary.getSysDictionaryId());
        sysDictionaryParameter.setRealValue(createDictionary.getRealValue());
        sysDictionaryParameter.setShowValue(createDictionary.getShowValue());
        sysDictionaryParameter.setSortValue(createDictionary.getSortValue());
        sysDictionaryParameter.setAvailable(createDictionary.getAvailable());
        return dictionaryService.createDictionary(sysDictionaryParameter);
    }

    /**
     * 更新数据字典
     * @param group_id
     * @param id
     * @param updateDictionary
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/dictionary/{group_id}/child/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新数据字典")
    public void update(@PathVariable(value = "group_id") Integer group_id,
                       @PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateDictionary updateDictionary, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysDictionaryParameter sysDictionaryParameter = new SysDictionaryParameter();
        sysDictionaryParameter.setId(id);
        sysDictionaryParameter.setSysDictionaryId(group_id);
        sysDictionaryParameter.setRealValue(updateDictionary.getRealValue());
        sysDictionaryParameter.setShowValue(updateDictionary.getShowValue());
        sysDictionaryParameter.setSortValue(updateDictionary.getSortValue());
        sysDictionaryParameter.setAvailable(updateDictionary.getAvailable());
        dictionaryService.updateDictionary(sysDictionaryParameter);
    }

    /**
     * 删除数据字典
     * @param group_id
     * @param id
     */
    @RequestMapping(value = "/dictionary/{group_id}/child/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除数据字典")
    public void delete(@PathVariable(value = "group_id") Integer group_id,
                       @PathVariable(value = "id") Integer id){
        dictionaryService.deleteDictionary(id);
    }

}
