package com.xmomen.module.core.web.mgt;

import com.xmomen.module.account.service.UserService;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.service.MemberService;
import com.xmomen.module.user.entity.SysUsers;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tanxinzheng on 16/7/3.
 */
public class WebSecurityManagerExt extends DefaultWebSecurityManager {

    @Autowired
    UserService userService;

    @Autowired
    MemberService memberService;

    protected Subject doCreateSubject(SubjectContext context) {
    	Subject subject = this.getSubjectFactory().createSubject(context);
        String username = (String) subject.getPrincipal();
        if(username != null){
        	//后端用户数据源
            SysUsers sysUsers = userService.findByUsername(username);
            if(sysUsers !=null && sysUsers.getId() != null){
                subject.getSession().setAttribute(AppConstants.SESSION_USER_ID_KEY, sysUsers.getId());
            } else {
            	CdMember memberQuery = new CdMember();
            	memberQuery.setPhoneNumber(username);
            	//前端用户数据源
            	CdMember member = memberService.findMember(memberQuery);
            	if(member != null && member.getId() != null) {
            		subject.getSession().setAttribute(AppConstants.SESSION_USER_ID_KEY, member.getId());
            	}
            }
        }
        return subject;
    }

    @Override
	public void setAuthenticator(Authenticator authenticator) throws IllegalArgumentException {
		super.setAuthenticator(authenticator);
		if (authenticator instanceof ModularRealmAuthenticator) {
            ((ModularRealmAuthenticator) authenticator).setRealms(getRealms());
        }
	}
}
