package com.wlrllr.sdk.core.intercepter;

import com.wlrllr.sdk.core.AbstractHandler;
import com.wlrllr.sdk.util.SpringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wlrllr on 2017/11/17.
 */
@Aspect
@Component
public class CustomUrlInterceptor {

    private static Logger logger = LoggerFactory.getLogger(CustomUrlInterceptor.class);

    @Around("execution(* *..*.customerServerUrl(..))")
    public Object method(ProceedingJoinPoint proceedingJoinPoint) {
        logger.info(">>>>>>进入拦截器<<<<<<<<");
        try {
            Class clazz = proceedingJoinPoint.getThis().getClass().getSuperclass();
            Object[] args = proceedingJoinPoint.getArgs();
            Class parent = clazz.getSuperclass().getSuperclass();
            if (parent == null || !parent.isAssignableFrom(AbstractHandler.class)) {
                return proceedingJoinPoint.proceed();
            }
            AbstractHandler handler = SpringUtils.getBean(AbstractHandler.class);
            return handler.index((HttpServletRequest)args[0]);
        } catch (Exception e) {
          e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Object target = proceedingJoinPoint.getTarget();
        return null;
    }
}
