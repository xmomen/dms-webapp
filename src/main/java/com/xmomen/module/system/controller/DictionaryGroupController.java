package com.xmomen.module.system.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.system.entity.SysDictionary;
import com.xmomen.module.system.model.CreateDictionaryGroup;
import com.xmomen.module.system.model.UpdateDictionaryGroup;
import com.xmomen.module.system.service.DictionaryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class DictionaryGroupController {

    @Autowired
    DictionaryGroupService dictionaryGroupService;

    /**
     * 数据字典组列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/dictionary", method = RequestMethod.GET)
    @Log(actionName = "查询数据字典组")
    public Page<SysDictionary> getList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        return dictionaryGroupService.getDictionaryList(keyword, limit, offset);
    }

    /**
     *  数据字典组列表
     * @param id
     */
    @RequestMapping(value = "/dictionary/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询数据字典组")
    public SysDictionary get(@PathVariable(value = "id") Integer id){
        return dictionaryGroupService.getDictionary(id);
    }

    /**
     * 新增数据字典组
     * @param createDictionaryGroup
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/dictionary", method = RequestMethod.POST)
    @Log(actionName = "新增数据字典组")
    public SysDictionary create(@RequestBody @Valid CreateDictionaryGroup createDictionaryGroup, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysDictionary sysDictionary = new SysDictionary();
        sysDictionary.setDictionaryCode(createDictionaryGroup.getDictionaryCode());
        sysDictionary.setDictionaryDesc(createDictionaryGroup.getDictionaryDesc());
        sysDictionary.setAvailable(createDictionaryGroup.getAvailable());
        return dictionaryGroupService.createDictionary(sysDictionary);
    }

    /**
     * 更新数据字典组
     * @param id
     * @param updateDictionaryGroup
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/dictionary/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新数据字典组")
    public void update(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateDictionaryGroup updateDictionaryGroup, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysDictionary sysDictionary = new SysDictionary();
        sysDictionary.setId(id);
        sysDictionary.setAvailable(updateDictionaryGroup.getAvailable());
        sysDictionary.setDictionaryCode(updateDictionaryGroup.getDictionaryCode());
        sysDictionary.setDictionaryDesc(updateDictionaryGroup.getDictionaryDesc());
        dictionaryGroupService.updateDictionary(sysDictionary);
    }

    /**
     *  删除数据字典组
     * @param id
     */
    @RequestMapping(value = "/dictionary/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除数据字典组")
    public void delete(@PathVariable(value = "id") Integer id){
        dictionaryGroupService.deleteDictionary(id);
    }

}
