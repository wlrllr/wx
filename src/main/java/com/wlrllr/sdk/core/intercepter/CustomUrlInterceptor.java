package com.wlrllr.sdk.core.intercepter;

import com.wlrllr.sdk.core.AbstractHandleAdapter;
import com.wlrllr.sdk.core.AbstractHandler;
import com.wlrllr.sdk.util.SpringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by wlrllr on 2017/11/17.
 */
@Aspect
@Component
public class CustomUrlInterceptor {

    private static Logger logger = LoggerFactory.getLogger(CustomUrlInterceptor.class);

    @Around("execution(* *..*.index(..))")
    public Object method(ProceedingJoinPoint proceedingJoinPoint) {
        logger.info(">>>>>>进入拦截器<<<<<<<<");
        try {
            Class clazz = proceedingJoinPoint.getThis().getClass();
            Object[] args = proceedingJoinPoint.getArgs();
            Class parent = clazz.getSuperclass();
            Boolean interceptorFlag = false;
            while(true){
                if(parent.isAssignableFrom(Object.class)){
                    break;
                }
                if(parent.isAssignableFrom(AbstractHandleAdapter.class)){
                    interceptorFlag = true;
                    break;
                }
                parent = parent.getSuperclass();
            }
            if (interceptorFlag) {
                AbstractHandler handler = SpringUtils.getBean(AbstractHandler.class);
                return handler.index((HttpServletRequest)args[0]);
            }
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
          e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Object target = proceedingJoinPoint.getTarget();
        return null;
    }
}
