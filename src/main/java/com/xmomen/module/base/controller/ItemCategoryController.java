package com.xmomen.module.base.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.entity.CdCategory;
import com.xmomen.module.base.model.CreateItemCategory;
import com.xmomen.module.base.model.ItemCategoryModel;
import com.xmomen.module.base.service.ItemCategoryService;
import com.xmomen.module.logger.Log;

/**
 * Created by Jeng on 16/3/25.
 */
@RestController
public class ItemCategoryController {

    @Autowired
    ItemCategoryService itemCategoryService;

    /**
     * 查询产品分类信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/itemCategory", method = RequestMethod.GET)
    @Log(actionName = "查询产品分类信息")
    public List<ItemCategoryModel> getItemCategoryTree(@RequestParam(value = "id", required = false) Integer id){
        return itemCategoryService.getItemCategoryTree(id);
    }

    /**
     * 新增产品分类
     * @param createItemCategory
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/itemCategory", method = RequestMethod.POST)
    @Log(actionName = "新增产品分类")
    public void createItemCategory(@RequestBody @Valid CreateItemCategory createItemCategory, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CdCategory cdCategory = new CdCategory();
        cdCategory.setCategoryName(createItemCategory.getName());
        cdCategory.setParentId(createItemCategory.getParentId());
        itemCategoryService.createItemCategory(cdCategory);
    }

    /**
     *  删除机构组织
     * @param id
     */
    @RequestMapping(value = "/itemCategory/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除产品分类")
    public void deleteItemCategory(@PathVariable(value = "id") Integer id){
        itemCategoryService.delete(id);
    }

    /**
     *  修改产品分类
     * @param id
     */
    @RequestMapping(value = "/itemCategory/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改产品分类")
    public void updateItemCategory(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid CreateItemCategory createItemCategory, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CdCategory cdCategory = new CdCategory();
        cdCategory.setId(id);
        cdCategory.setCategoryName(createItemCategory.getName());
        cdCategory.setParentId(createItemCategory.getParentId());
        itemCategoryService.updateItemCategory(cdCategory);
    }
}
