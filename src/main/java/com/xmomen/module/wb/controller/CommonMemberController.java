package com.xmomen.module.wb.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.service.MemberService;
import com.xmomen.module.core.web.filter.PcFormAuthenticationFilter;
import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.wb.model.PcMember;
import com.xmomen.module.wb.model.PcMemberInfo;

@RestController
public class CommonMemberController {

	private static Logger logger = LoggerFactory.getLogger(CommonMemberController.class);

	@Autowired
	MemberService memberService;
	
	/**
	 * 普通用户注册
	 */
	@RequestMapping(value = "/member/register", method = RequestMethod.POST)
	public CdMember register(@RequestBody @Valid PcMember createPcMember) {
		CreateMember createMember = new CreateMember();
		createMember.setName(createPcMember.getName());
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
		return cdMember;
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public PcMemberInfo login(HttpServletRequest request, Model model) throws Exception{
        if(SecurityUtils.getSubject().isAuthenticated()){
            String phoneNumber = (String) SecurityUtils.getSubject().getPrincipal();
            // 可能是
            CdMember query = new CdMember();
            query.setPhoneNumber(phoneNumber);
            CdMember cdMember = memberService.findMember(query);
            if(cdMember == null) {
                throw new BusinessException("用户名不存在");
            } else {
            	PcMemberInfo pcMemberInfo = new PcMemberInfo();
            	pcMemberInfo.setMemberId(cdMember.getId());
            	pcMemberInfo.setPhoneNumber(cdMember.getPhoneNumber());
            	//pcMemberInfo.setEmail(cdMember.getEmail());
            	pcMemberInfo.setName(cdMember.getName());
            	return pcMemberInfo;
            }
        }
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名不存在";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        } else {
            error = "系统错误";
        }
        throw new BusinessException(error);
    }
	
	@RequestMapping(value = "/member/logout")
    public ResponseEntity logout(HttpServletRequest request){
		String message = "SUCCESS";
		try {
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			logger.error("登出失败", e);
			message = "FAILURE";
		}
		return new ResponseEntity(message, HttpStatus.UNAUTHORIZED);
    }
}
