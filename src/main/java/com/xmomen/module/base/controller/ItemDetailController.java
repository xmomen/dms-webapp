package com.xmomen.module.base.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.mapper.ItemDetailMapper;
import com.xmomen.module.base.mapper.ItemMapper;
import com.xmomen.module.base.model.*;
import com.xmomen.module.base.service.ItemDetailService;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemDetailController {
    @Autowired
    ItemDetailService itemDetailService;
    @Autowired
    ItemDetailMapper itemDetailMapper;
    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增产品详情
     *
     * @param createItemDetail
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/itemDetail", method = RequestMethod.POST)
    @Log(actionName = "新增产品")
    public void createItemDeatil(@RequestBody @Valid CreateItemDetail createItemDetail, BindingResult bindingResult) throws ArgumentValidException {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new ArgumentValidException(bindingResult);
        }
        itemDetailService.createItemDetail(createItemDetail);
    }

    /**
     * 修改产品详情
     *
     * @param id
     */
    @RequestMapping(value = "/itemDetail/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改产品详情")
    public void updateItemDetail(@PathVariable(value = "id") Integer id,
                                 @RequestBody @Valid UpdateItemDetail updateItemDetail, BindingResult bindingResult) throws ArgumentValidException {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new ArgumentValidException(bindingResult);
        }
        itemDetailService.updateItemDetail(id, updateItemDetail);
    }

    /**
     * 删除商品详情
     *
     * @param id
     */
    @RequestMapping(value = "/itemDetail/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除产品详情信息")
    public void deleteItemDetail(@PathVariable(value = "id") Integer id) {
        itemDetailService.delete(id);
    }
}
