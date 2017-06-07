package com.xmomen.module.wb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.web.rest.RestError;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.service.MemberService;
import com.xmomen.module.core.web.filter.FormAuthenticationFilterExt;
import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.sms.api.SmsMessageService;
import com.xmomen.module.sms.api.SmsResponse;
import com.xmomen.module.sms.model.IdentifyCodeModel;
import com.xmomen.module.sms.util.GlobalIdentifyCodeManager;
import com.xmomen.module.wb.model.PcMember;
import com.xmomen.module.wb.model.PcUpdatePasswordModel;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;

@RestController
public class CommonMemberController extends PcBaseController{

	@Autowired
	MemberService memberService;

	@Autowired
	SmsMessageService smsMessageService;
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
		String phoneNumber = createPcMember.getPhoneNumber();
		if(!StringUtils.isNumeric(phoneNumber) || phoneNumber.length() != 11) {
			throw new BusinessException("不合法的手机号码");
		}
		String identifyCodeKey = createPcMember.getPhoneNumber();
		IdentifyCodeModel identifyCodeModel = GlobalIdentifyCodeManager.getIdentifyCode(identifyCodeKey);
		if(identifyCodeModel == null || identifyCodeModel.isExpired()) {
			throw new BusinessException("验证码未生成或者已过期");
		}
		if(!createPcMember.getPhoneIdentifyCode().equals(identifyCodeModel.getIdentifyCode())) {
			throw new BusinessException("验证码不正确");
		}
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(phoneNumber);
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

	@RequestMapping(value = "/member/logout")
    public ResponseEntity logout(HttpServletRequest request){
		String message = "登出成功";
		try {
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			message = "登出失败";
		}
		return new ResponseEntity(message, HttpStatus.UNAUTHORIZED);
    }

	@RequestMapping(value = "/member/phonecode")
	public SmsResponse sendSms(@RequestParam(value="phone") String phoneNumber) throws Exception {
		if(!StringUtils.isNumeric(phoneNumber) || phoneNumber.length() != 11) {
			throw new BusinessException("不合法的手机号码");
		}
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(phoneNumber);
		CdMember cdMember = memberService.findMember(memberQuery);
		if(cdMember != null){
			throw new BusinessException("该手机号码已注册");
		}
		SmsResponse smsResponse = smsMessageService.sendSingleRequest(phoneNumber);
		if(smsResponse == null) {
			throw new Exception("调用SMS接口失败");
		}
		return smsResponse;
	}
	
	/**
	 * 发送验证码到用户手机，用户找回密码(密码重置)操作
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/member/operationcode")
	public SmsResponse getOperationCode(@RequestParam(value="phone") String phoneNumber) throws Exception {
		if(!StringUtils.isNumeric(phoneNumber) || phoneNumber.length() != 11) {
			throw new BusinessException("不合法的手机号码");
		}
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(phoneNumber);
		CdMember cdMember = memberService.findMember(memberQuery);
		if(cdMember == null){
			throw new BusinessException("该用户不存在");
		}
		/*String operationCode = RandomStringGenerator.getRandomNumberStrByLength(6);
		SmsResponse smsResponse = smsMessageService.sendPasswordInfo(phoneNumber, operationCode);
		if(smsResponse == null) {
			throw new Exception("调用SMS接口失败");
		} else {
			GlobalIdentifyCodeManager.updateOperationCode(phoneNumber, operationCode);
		}
		return smsResponse;*/
		SmsResponse smsResponse = smsMessageService.sendSingleRequest(phoneNumber);
		if(smsResponse == null) {
			throw new Exception("调用SMS接口失败");
		}
		return smsResponse;
	}
	
	/**
	 * 根据发送到手机的验证码，手动输入密码达到重置密码的目的
	 * @param pcMember
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/member/resetpassword", method = RequestMethod.POST)
	public CdMember resetPassword(@RequestBody @Valid PcMember pcMember) throws Exception {
		String phoneNumber = pcMember.getPhoneNumber();
		if(!StringUtils.isNumeric(phoneNumber) || phoneNumber.length() != 11) {
			throw new BusinessException("不合法的手机号码");
		}
		String identifyCodeKey = pcMember.getPhoneNumber();
		//IdentifyCodeModel identifyCodeModel = GlobalIdentifyCodeManager.getOperationCode(identifyCodeKey);
		IdentifyCodeModel identifyCodeModel = GlobalIdentifyCodeManager.getIdentifyCode(identifyCodeKey);
		if(identifyCodeModel == null || identifyCodeModel.isExpired()) {
			throw new BusinessException("验证码未生成或者已过期");
		}
		if(!pcMember.getPhoneIdentifyCode().equals(identifyCodeModel.getIdentifyCode())) {
			throw new BusinessException("验证码不正确");
		}
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(phoneNumber);
		CdMember cdMember = memberService.findMember(memberQuery);
		if(cdMember == null) {
			throw new BusinessException("该用户不存在");
		}
		boolean success = memberService.resetPassword(cdMember.getId(), pcMember.getPassword());
		if(!success) {
			throw new BusinessException("密码重置失败");
		}
		cdMember.setPassword("");
		return cdMember;
	}
	
	/**
	 * 直接发送随机生成的6位数密码到手机，达到重置密码的目的
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/member/resetpassword", method = RequestMethod.GET)
	public SmsResponse resetPassword(@RequestParam(value="phone") String phoneNumber) throws Exception {
		if(!StringUtils.isNumeric(phoneNumber) || phoneNumber.length() != 11) {
			throw new BusinessException("不合法的手机号码");
		}
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(phoneNumber);
		CdMember cdMember = memberService.findMember(memberQuery);
		if(cdMember == null) {
			throw new BusinessException("该用户不存在");
		}
		String newPassword = RandomStringGenerator.getRandomNumberStrByLength(6);
		String oldPassword = cdMember.getPassword();
		if(oldPassword == null) {
			oldPassword = "";
		}
		boolean success = memberService.updatePassword(cdMember.getId(), newPassword, oldPassword);
		if(success) {
			SmsResponse smsResponse = smsMessageService.sendPasswordInfo(phoneNumber, newPassword);
			if(smsResponse == null) {
				//如果未成功发送短信，密码回滚
				cdMember.setPassword(oldPassword);
				memberService.updateMember(cdMember);
				throw new Exception("调用SMS接口失败");
			}
			return smsResponse;
		} else {
			throw new BusinessException("密码重置失败");
		}
	}
	
	/**
	 * 更新密码
	 * @param updatePasswordModel
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/member/changepassword", method = RequestMethod.POST)
	public CdMember changePassword(@RequestBody @Valid PcUpdatePasswordModel updatePasswordModel) throws BusinessException {
		String phoneNumber = updatePasswordModel.getPhoneNumber();
		CdMember memberQuery = new CdMember();
		memberQuery.setPhoneNumber(phoneNumber);
		CdMember cdMember = memberService.findMember(memberQuery);
		if(cdMember == null) {
			throw new BusinessException("当前用户不存在");
		} else {
			boolean success = memberService.updatePassword(cdMember.getId(), updatePasswordModel.getPassword(), updatePasswordModel.getOldPassword());
		    if(!success) {
		    	throw new BusinessException("密码不正确");
		    }
		}
		cdMember.setPassword("");
		return cdMember;
	}
}
