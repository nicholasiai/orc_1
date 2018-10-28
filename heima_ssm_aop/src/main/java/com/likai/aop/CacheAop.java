package com.likai.aop;

import com.github.pagehelper.PageHelper;
import com.likai.annotation.Cache;
import com.likai.domain.Orders;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//@Component
//@Aspect
public class CacheAop {

    @Autowired
    private HashMap<String,List> map ;

    @Pointcut("execution(* com.likai.service.impl.*.*(..))")
    public void pt1(){
    }

    @Around("pt1()")
    public Object msgrun(ProceedingJoinPoint pjp) throws Throwable {
//        获取全类名
        Class<? extends ProceedingJoinPoint> aClass = pjp.getClass();
//        获取方法名
        String name = pjp.getSignature().getName();
//        设置key值
        String key = aClass+"."+name;
//        获取代理对象
        Object target = pjp.getTarget();
//        获取对象字节码文件
        Class<?> targetClass = target.getClass();
//        获得代理对象的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = targetClass.getMethod(name, signature.getParameterTypes());
//        获取参数
        Object[] args = pjp.getArgs();
//        判断是否有通知缓存注解
        if (method.isAnnotationPresent(Cache.class)){
            System.out.println("有通知缓存注解");
//            有注解，判断有无缓存
            if (map.get(key)!=null){
                System.out.println("从缓存中查询");

//                有缓存,使用缓存
//                PageHelper.startPage((int)args[0],(int)args[1]);
//                List list = (List)pjp.proceed(args);
//                return list;

                List list = map.get(key);
                List list1=new ArrayList();
//                判断总页码是否超过集合长度
                int beginPage;

                if ((int)args[0]<=0){
                    beginPage=1;
                }else {
                    beginPage=(int)args[0];
                }
                int size= beginPage*(int)args[1]>=list.size()?list.size():beginPage*(int)args[1];

                for (int i = (beginPage-1)*(int)args[1]; i < size; i++) {
                    list1.add(list.get(i));
                }
//                PageHelper.startPage((int)args[0],(int)args[1]);
//                List list2 = (List)pjp.proceed(args);
                return list1;

            }else {

//                无缓存，添加缓存
                System.out.println("第一次从缓存中查询");
                List list = (List)pjp.proceed(args);
                map.put(key,list);
                PageHelper.startPage((int)args[0],(int)args[1]);
                List list2 = (List)pjp.proceed(args);
                return list2;
            }

        }

        return pjp.proceed(args);

    }

}
