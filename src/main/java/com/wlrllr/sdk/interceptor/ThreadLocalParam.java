package com.wlrllr.sdk.interceptor;

/**
 * Created by wlrllr on 2017/11/8.
 */
public class ThreadLocalParam {

    private static final ThreadLocal<String> TL = new ThreadLocal<String>();

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
