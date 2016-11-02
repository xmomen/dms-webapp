package com.xmomen.module.core.web.exception;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.web.rest.RestError;
import com.xmomen.module.core.web.WebCommonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jeng on 2016/1/11.
 */
public class GlobalMappingExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        if (!WebCommonUtils.isJSON(request)) {// 不是ajax请求
            return super.doResolveException(request, response, handler, ex);
        }
        ModelAndView mv = new ModelAndView();
        //设置ContentType
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //避免乱码
        response.setCharacterEncoding("UTF-8");
        RestError restError = new RestError(ex, request);
        //设置状态码
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = "";
        if(ex instanceof IllegalArgumentException){
            status = HttpStatus.BAD_REQUEST.value();
            message = ex.getMessage();
        }else{
            message = "系统异常，请联系管理员";
        }
        response.setStatus(status);
        restError.setStatus(status);
        restError.setMessage(message);
        try {
            response.getWriter().write(JSONObject.toJSONString(restError));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.error(ex.getMessage(), ex);
        return mv;
    }
}
