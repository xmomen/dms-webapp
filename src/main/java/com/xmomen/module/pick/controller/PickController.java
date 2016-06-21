package com.xmomen.module.pick.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.pick.model.CreateMember;
import com.xmomen.module.pick.model.PickVo;
import com.xmomen.module.pick.service.PickService;

/**
 */
@RestController
public class PickController {
	
	@Autowired
	PickService pickService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 采摘结算
     * @param createPurchase
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/pick/settleAccounts", method = RequestMethod.PUT)
    @Log(actionName = "采摘结算")
    public void settleAccounts(@RequestBody @Valid PickVo pickVo, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        pickService.pick(pickVo);
    }
    
    @RequestMapping(value = "/pick/pickCard", method = RequestMethod.PUT)
    @Log(actionName = "办新卡")
    public void pickCard(@RequestBody @Valid CreateMember createMember, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        pickService.pickCard(createMember);
    }
}
