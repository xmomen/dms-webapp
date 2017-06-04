package com.xmomen.module.core.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.service.MemberService;
import com.xmomen.module.core.web.WebCommonUtils;
import com.xmomen.module.core.web.token.MemberUserToken;

public class PcFormAuthenticationFilter extends FormAuthenticationFilter {

	private static Logger logger = LoggerFactory.getLogger(PcFormAuthenticationFilter.class);

    public static final String DEFAULT_ERROR_EXCEPTION_KEY_ATTRIBUTE_NAME = "shiroLoginFailureException";

	@Autowired
	private MemberService memberService;
	
	private void initUserContext(String phoneNumber, Subject subject){
        CdMember query = new CdMember();
        query.setPhoneNumber(phoneNumber);
        if(phoneNumber == null){
            return;
        }
        CdMember member = memberService.findMember(query);
        subject.getSession().setAttribute(AppConstants.SESSION_USER_ID_KEY, member.getId());
    }
	
	private void buildJSONMessage(String message, ServletRequest request, ServletResponse response){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", HttpStatus.UNAUTHORIZED.value());
            map.put("message", message);
            map.put("timestamp", new Date());
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            servletOutputStream.print(JSONObject.toJSONString(map));
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
                    //allow them to see the login page ;)
                    return true;
                } else {
                    buildJSONMessage("Requires authentication", request, response);
                }
                return false;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
                saveRequestAndRedirectToLogin(request, response);
            } else {
                buildJSONMessage("Requires authentication", request, response);
            }
            return false;
        }
    }

    /**
     * 登录成功处理（兼容自动识别异步请求，json请求及页面请求）
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String phoneNumber = (String) subject.getPrincipal();
        initUserContext(phoneNumber, subject);
//        if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
//            issueSuccessRedirect(request, response);
//        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            Map<String, Object> result = new HashMap<>();
            result.put("status", 200);
            result.put("username", phoneNumber);
            out.println(JSONObject.toJSONString(result));
            out.flush();
            out.close();
//        }
        return false;
    }

    /**
     * 登录失败处理（兼容自动识别异步请求，json请求及页面请求）
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
//        if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
//            setFailureAttribute(request, e);
//            return true;
//        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            String error = null;
            if (IncorrectCredentialsException.class.getSimpleName().equals(message)) {
                error = "账号或密码错误";
            } else if (UnknownAccountException.class.getSimpleName().equals(message)) {
                error = "账号不存在";
            } else {
                error = "未知错误";
            }
            Map<String, Object> result = new HashMap<>();
            result.put("status", 401);
            result.put("message", error);
            out.println(JSONObject.toJSONString(result));
            out.flush();
            out.close();
        } catch (IOException e1) {
            logger.error(e1.getMessage(), e1.getCause());
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        String className = ae.getClass().getName();
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(DEFAULT_ERROR_EXCEPTION_KEY_ATTRIBUTE_NAME, ae);
    }

	@Override
	protected AuthenticationToken createToken(String username, String password, boolean rememberMe, String host) {
		return new MemberUserToken(username, password, rememberMe, host);
	}
}
