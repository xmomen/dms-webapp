package com.xmomen.module.wb.controller;

import com.xmomen.module.base.constant.AppConstants;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 17/5/12.
 */
@RestController
public class PcBaseController {

    /**
     * 获取当前登录memberId
     * @return
     */
    protected Integer getCurrentMemberId(){
        Integer memberId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
        return memberId;
    }


}
