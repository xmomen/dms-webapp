package com.xmomen.module.wb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.service.MemberService;
import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.wb.model.PcMember;

@RestController
public class CommonMemberController {

	@Autowired
	MemberService memberService;
	
	/**
	 * 普通用户注册
	 */
	@RequestMapping(value = "/member/register", method = RequestMethod.POST)
	public CdMember register(@RequestBody @Valid PcMember createPcMember) {
		CreateMember createMember = new CreateMember();
		createMember.setPhoneNumber(createPcMember.getPhoneNumber());
		createMember.setPassword(createPcMember.getPassword());
		createMember.setMemberAddressList(new ArrayList<MemberAddressCreate>());
		createMember.setEmail(createPcMember.getEmail());
		
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(createPcMember.getPhoneNumber());
		CdMember cdMember = memberService.findMember(memberQuery);
		if(cdMember == null) {
			cdMember = memberService.createMember(createMember);
		} else if(StringUtils.isEmpty(cdMember.getPassword())) {
			memberService.updatePassword(cdMember.getId(), createPcMember.getPassword(), "");
		} else {
			throw new IllegalArgumentException("用户已被注册");
		}
		cdMember.setPassword("");
		return cdMember;
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model){
        if(SecurityUtils.getSubject().isAuthenticated()){
            return "success";
        }
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名不存在";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        if(error != null) {
        	//throw new IllegalArgumentException(error);
        	return error;
        }
        //model.addAttribute("error", error);
        return "login";
    }
}
