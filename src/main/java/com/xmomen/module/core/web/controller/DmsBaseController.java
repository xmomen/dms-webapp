package com.xmomen.module.core.web.controller;

import com.xmomen.module.base.constant.AppConstants;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/5/13.
 */
@RestController
public class DmsBaseController {

    protected Integer getCurrentUserId(){
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        return userId;
    }
}
