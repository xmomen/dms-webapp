package com.xmomen.module.core.web.mgt;

import com.xmomen.module.account.service.UserService;
import com.xmomen.module.user.entity.SysUsers;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tanxinzheng on 16/6/27.
 */
public class RememberMeManagerExt extends CookieRememberMeManager {

    private static final transient Logger log = LoggerFactory.getLogger(RememberMeManagerExt.class);

    @Autowired
    UserService userService;

    public void onSuccessfulLogin(Subject subject, AuthenticationToken token, AuthenticationInfo info) {
        this.forgetIdentity(subject);
        if(this.isRememberMe(token)) {
            this.rememberIdentity(subject, token, info);
            String username = (String) subject.getPrincipal();
            SysUsers sysUsers = userService.findByUsername(username);
            subject.getSession().setAttribute("user_id", sysUsers.getId());
        } else if(log.isDebugEnabled()) {
            log.debug("AuthenticationToken did not indicate RememberMe is requested.  RememberMe functionality will not be executed for corresponding account.");
        }

    }
}
