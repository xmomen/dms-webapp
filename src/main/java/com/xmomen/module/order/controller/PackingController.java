package com.xmomen.module.packing.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbPacking;
import com.xmomen.module.order.model.CreatePacking;
import com.xmomen.module.order.model.PackingModel;
import com.xmomen.module.order.model.PackingQuery;
import com.xmomen.module.order.service.PackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class PackingController {

    @Autowired
    PackingService packingService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 装箱记录列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/packing", method = RequestMethod.GET)
    @Log(actionName = "查询装箱记录列表")
    public Page<PackingModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        PackingQuery packingQuery = new PackingQuery();
        return packingService.getPackingList(packingQuery, limit, offset);
    }

    /**
     * 新增装箱记录
     * @param createPacking
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/packing", method = RequestMethod.POST)
    @Log(actionName = "新增装箱记录")
    public TbPacking createPacking(@RequestBody @Valid CreatePacking createPacking, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return packingService.create(createPacking);
    }

    /**
     *  删除装箱记录
     * @param id
     */
    @RequestMapping(value = "/packing/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除装箱记录")
    public void deletePacking(@PathVariable(value = "id") Integer id){
        packingService.delete(id);
    }

}
