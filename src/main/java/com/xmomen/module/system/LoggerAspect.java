package com.xmomen.module.system;

import com.xmomen.module.system.model.LogModel;
import com.xmomen.module.system.service.LogService;
import com.xmomen.module.logger.Log;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Jeng on 16/3/20.
 */
@Aspect
public class LoggerAspect {

    @Autowired
    LogService logService;

    @Autowired
    HttpServletRequest request;

    /**
     * 日志逻辑切入点
     */
    @Pointcut("@annotation(com.xmomen.module.logger.Log)")
    public void getLogInfo() { }

    /**
     * 管理员添加操作日志(后置通知)
     * @param joinPoint
     * @throws Throwable
     */
    @AfterReturning(value = "getLogInfo()")
    public void afterReturning(JoinPoint joinPoint) throws Throwable{
        //获取登录用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if(username == null){//没有管理员登录
            return;
        }
        //判断参数
        if(joinPoint.getArgs() == null){//没有参数
            return;
        }
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法
        Object[] args = joinPoint.getArgs();     //方法的参数
        Log an = (Log)getAnnotationByMethod(method ,Log.class );
        if(an == null){
            return;
        }
        String actionName = an.actionName();
        Integer user_id =(Integer) SecurityUtils.getSubject().getSession().getAttribute("user_id");
        //创建日志对象
        LogModel log = new LogModel();
        log.setUserId(user_id);//设置管理员id
        log.setActionDate(new Date());//操作时间
        log.setActionResult(null);//操作内容
        log.setClientIp(getRemoteHost(request));
        log.setActionName(actionName);//操作
        logService.insertLog(log);//添加日志
    }

    public Annotation getAnnotationByMethod(Method method , Class annoClass){
        Annotation all[] = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    public Method getMethodByClassAndName(Class c , String methodName){
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method ;
            }
        }
        return null;
    }

    public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}
