package com.xmomen.module.order.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbPurchase;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.model.PurchaseModel;
import com.xmomen.module.order.service.PurchaseService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 采购单列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    @Log(actionName = "查询采购单列表")
    public Page<PurchaseModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        return purchaseService.getPurchaseList(keyword, limit, offset);
    }

    /**
     * 新增采购单
     * @param createPurchase
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @Log(actionName = "新增采购单")
    public void createPurchase(@RequestBody @Valid CreatePurchase createPurchase, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        purchaseService.createPurchase(createPurchase);
    }

    /**
     *  删除采购单
     * @param id
     */

    @RequestMapping(value = "/purchase/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除采购单")
    public void deletePurchase(@PathVariable(value = "id") Integer id){
        purchaseService.deletePurchase(id);
    }

}
