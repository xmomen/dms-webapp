package com.xmomen.module.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.entity.CdExpressMember;
import com.xmomen.module.base.mapper.ExpressMemberMapper;
import com.xmomen.module.base.service.ExpressMemberService;
import com.xmomen.module.logger.Log;
@RestController
public class ExpressMemberController {
	@Autowired
	ExpressMemberService expressMemberService;
	@Autowired
	ExpressMemberMapper expressMemberMapper;
	@Autowired
    MybatisDao mybatisDao;
    /**
     * 查询快递员员信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/expressMember", method = RequestMethod.GET)
    @Log(actionName = "查询快递员员信息")
    public Page<CdExpressMember> getExpressMemberList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyword){
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
         map.put("keyword", keyword);
        return (Page<CdExpressMember>) mybatisDao.selectPage(ExpressMemberMapper.ExpressMemberMapperNameSpace + "getExpressMemberList", map, limit, offset);
    }
    
    @RequestMapping(value = "/expressMember", method = RequestMethod.POST)
    @Log(actionName = "新增快递员")
    public void createExpressMember(@RequestBody @Valid CdExpressMember createExpressMember, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        expressMemberService.createExpressMember(createExpressMember);
    }
    
    /**
     *  修改
     * @param id
     */
    @RequestMapping(value = "/expressMember/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改快递员信息")
    public void updateMember(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid CdExpressMember updateExpressMember, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        expressMemberService.updateExpressMember(id, updateExpressMember);
    }
    
    /**
     *  删除
     * @param id
     */
    @RequestMapping(value = "/expressMember/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除快递员信息")
    public void deleteMember(@PathVariable(value = "id") Integer id){
    	expressMemberService.delete(id);
    }
    
}
