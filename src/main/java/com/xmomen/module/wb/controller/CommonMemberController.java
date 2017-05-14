package com.xmomen.module.wb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.web.rest.RestError;
import com.xmomen.module.base.model.MemberModel;
import com.xmomen.module.core.web.filter.FormAuthenticationFilterExt;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CommonMemberController extends PcBaseController{

	@Autowired
	MemberService memberService;

	/**
	 * 普通用户注册
	 */
	@RequestMapping(value = "/member/register", method = RequestMethod.POST)
	public CdMember register(@RequestBody @Valid PcMember createPcMember) throws BusinessException {
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
			throw new BusinessException("用户已被注册");
		}
		cdMember.setPassword("");
		return cdMember;
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public ResponseEntity login(HttpServletRequest request, Model model){
		Map<String, Object> result = new HashMap<>();
        if(SecurityUtils.getSubject().isAuthenticated()){
			String username = (String) SecurityUtils.getSubject().getPrincipal();
			result.put("status", 200);
			result.put("username", username);
            return new ResponseEntity(result, HttpStatus.OK);
        }
		String exceptionClassName = (String) request.getAttribute(FormAuthenticationFilterExt.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String error = null;
		RestError restError = new RestError();
		restError.setTimestamp(new Date());
        if(DisabledAccountException.class.getName().equals(exceptionClassName)){
			restError.setMessage("该账号已被锁定，请联系客服。");
		}else if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
			restError.setMessage("用户名不存在");
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			restError.setMessage("用户名或密码错误");
        } else if(exceptionClassName != null) {
			restError.setMessage( "登录失败：" + exceptionClassName);
        }
		restError.setStatus(401);
		return new ResponseEntity(restError, HttpStatus.UNAUTHORIZED);
    }

	/**
	 * 会员账号
	 * @return
	 */
	@RequestMapping(value = "/member/account", method = RequestMethod.GET)
	public CdMember accountSetting(){
		Integer memberId = getCurrentMemberId();
		CdMember memberModel = memberService.getOneMemberModel(String.valueOf(memberId));
		return memberModel;
	}

}
