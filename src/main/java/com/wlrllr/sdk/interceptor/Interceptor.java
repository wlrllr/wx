package com.wlrllr.sdk.interceptor;

import com.wlrllr.sdk.msg.Msg;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by wlrllr on 2017/11/8.
 */
@Aspect
@Component
public class Interceptor {

    @Around("execution(String com.wlrllr.sdk.core.AbstractHandler.invoke(..))")
    public Object interceptor(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        Msg msg = (Msg) args[0];
        if (msg != null) {
            String appId = msg.getToUser();
            if(StringUtils.isNotBlank(appId)){
                ThreadLocalParam.setThreadLocalAppId(appId);
            }
        }
        return null;
    }
}