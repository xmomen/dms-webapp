package com.xmomen.module.core.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jeng on 2016/3/1.
 */
public class WebCommonUtils {

    public static boolean isJSON(ServletRequest request){
        if("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))
                || "application/json".equalsIgnoreCase(request.getContentType())){
            return true;
        }
        return false;
    }
}
