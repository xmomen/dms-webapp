package com.xmomen.module.base.member.controller;

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
import com.xmomen.module.account.web.controller.vo.UpdateUserGroup;
import com.xmomen.module.base.member.mapper.MemberMapper;
import com.xmomen.module.base.member.model.CreateMember;
import com.xmomen.module.base.member.model.MemberModel;
import com.xmomen.module.base.member.service.MemberSercvice;
import com.xmomen.module.logger.Log;
import com.xmomen.module.system.entity.SysOrganization;
import com.xmomen.module.system.model.CreateOrganization;

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
}
