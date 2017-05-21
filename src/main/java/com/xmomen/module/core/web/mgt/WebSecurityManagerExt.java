package com.xmomen.module.core.web.mgt;

import com.xmomen.module.account.service.UserService;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.user.entity.SysUsers;
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

    protected Subject doCreateSubject(SubjectContext context) {
        Subject subject = this.getSubjectFactory().createSubject(context);
        String username = (String) subject.getPrincipal();
        if(username != null){
            SysUsers sysUsers = userService.findByUsername(username);
            if(sysUsers !=null && sysUsers.getId() != null){
                subject.getSession().setAttribute(AppConstants.SESSION_USER_ID_KEY, sysUsers.getId());
            }
        }
        return subject;
    }
}
