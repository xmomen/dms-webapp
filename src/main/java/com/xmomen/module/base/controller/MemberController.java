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
import com.xmomen.module.base.mapper.MemberMapper;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.model.MemberModel;
import com.xmomen.module.base.model.UpdateMember;
import com.xmomen.module.base.service.MemberSercvice;
import com.xmomen.module.logger.Log;

/**
 * Created by ted on 16/3/28.
 */
@RestController
public class MemberController {

    @Autowired
    MemberSercvice memberService;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    MybatisDao mybatisDao;
    /**
     * 查询客户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    @Log(actionName = "查询客户信息")
    public Page<MemberModel> getMemberList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyword){
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
         map.put("keyword", keyword);
        return (Page<MemberModel>) mybatisDao.selectPage(MemberMapper.MemberMapperNameSpace + "getMemberList", map, limit, offset);
    }
    
    
    @RequestMapping(value = "/member", method = RequestMethod.POST)
    @Log(actionName = "新增客户")
    public void createMember(@RequestBody @Valid CreateMember createMember, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        memberService.createMember(createMember);
    }
    
    /**
     *  修改
     * @param id
     */
    @RequestMapping(value = "/member/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改客户信息")
    public void updateMember(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid UpdateMember updateMember, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        memberService.updateMember(id,updateMember);
    }
    
    /**
     *  删除
     * @param id
     */
    @RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除客户信息")
    public void deleteMember(@PathVariable(value = "id") Integer id){
    	memberService.delete(id);
    }
}
