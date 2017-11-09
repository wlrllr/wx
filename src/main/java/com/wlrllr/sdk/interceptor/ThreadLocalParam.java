package com.wlrllr.sdk.interceptor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wlrllr on 2017/11/8.
 */
public class ThreadLocalParam {

    private static final ThreadLocal<String> TL = new ThreadLocal<String>();

    private static final ConcurrentHashMap<String,AppConfig> APP_INFO = new ConcurrentHashMap<>();

    //这里怎么样改成从数据库中取，而非传过来
    public static void addAppConfig(String appId){
        /*if(appConfig != null){
            AppConfig config = APP_INFO.get(appConfig.getAppId());
            if(config == null){

                APP_INFO.put(appConfig.getAppId(),appConfig);
            }
        }*/
    }

    public static void setAccessToken(String accessToken){
        AppConfig config = APP_INFO.get(TL.get());
        if(config != null){
            config.setAccessToken(accessToken);
        }
    }

    public static void removeThreadLocalAppId() {
        TL.remove();
    }

    public static void setThreadLocalAppId(String appId) {
        TL.set(appId);
    }

    public static String getThreadLocalAppId() {
        return TL.get();
    }
}
