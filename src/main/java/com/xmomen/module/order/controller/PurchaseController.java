package com.xmomen.module.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.model.PurchaseModel;
import com.xmomen.module.order.service.PurchaseService;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "purchaseStatus", required = false) Integer purchaseStatus,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                  @RequestParam(value = "isDistribute", required = false) Integer isDistribute,
                                  @RequestParam(value = "sellUnit", required = false) String sellUnit){
    	 Map param = new HashMap();
         param.put("keyword", keyword);
         param.put("isDistribute", isDistribute);
         param.put("sellUnit", sellUnit);
         param.put("purchaseStatus", purchaseStatus);
        if(startTime != null) {
            param.put("startTime", startTime.substring(0, 10));
        }
        if(endTime != null){
            param.put("endTime", endTime.substring(0, 10));
        }
        return purchaseService.getPurchaseList(param, limit, offset);
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
     * 修改采购状态
     * @param id
     * @param purchaseStatus
     */
    @RequestMapping(value = "/purchase/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改采购状态")
    public void createPurchase(@PathVariable(value = "id") Integer id,
                               @RequestParam(value = "purchaseStatus") Integer purchaseStatus) {
        purchaseService.updatePurchaseStatus(id, purchaseStatus);
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
