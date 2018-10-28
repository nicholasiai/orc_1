package com.likai.aop;


import com.likai.domain.SysLog;
import com.likai.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogAop {


    private Date visitTime;     //访问时间
    private String username;
    private String ip;
    private String url;
    private String method;
    private Long executionTime;     //执行时间

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    @Around("execution(* com.likai.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        获取当前代理的类
        Class aClass = pjp.getSignature().getClass();
        //        获取当前的方法名
        method = pjp.getSignature().getName();
//        判断条件
        if (aClass != null && method != null&&aClass!=LogAop.class) {
            //        获得当前的访问时间
            visitTime = new Date();
//        获取当前访问的用户名
            SecurityContext context = SecurityContextHolder.getContext();
            User user = (User) context.getAuthentication().getPrincipal();
            username = user.getUsername();
//        获取当前访问的ip
            ip = request.getRemoteAddr();
//        获取当前访问的url
             url = request.getRequestURI();

//        获取参数
            Object[] args = pjp.getArgs();
//        执行代理方法
            Object proceed = pjp.proceed(args);
//        获取方法执行后的时间
            executionTime =(new Date().getTime())- visitTime.getTime();
//        将结果封装为对象
            SysLog sysLog = new SysLog();
            sysLog.setVisitTime(visitTime);
            sysLog.setExecutionTime(executionTime);
            sysLog.setIp(ip);
            sysLog.setMethod(method);
            sysLog.setUrl(url);
            sysLog.setUsername(username);
//        调用方法将日志保存到数据库
            sysLogService.save(sysLog);
            return proceed;
        }

        return null;
    }


}
