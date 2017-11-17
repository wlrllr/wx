package com.wlrllr.sdk.core;

import com.wlrllr.model.WxApp;
import com.wlrllr.sdk.util.SpringUtils;
import com.wlrllr.service.WxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wlrllr on 2017/11/8.
 */
@Component
public class ThreadLocalParam {

    private static final ThreadLocal<String> TL = new ThreadLocal<String>();

    private static final ConcurrentHashMap<String, WxApp> APP_INFO = new ConcurrentHashMap<>();

    @Autowired
    private static WxAppService wxAppService;

    public static WxApp getApp() {
        String appId = TL.get();
        WxApp config = APP_INFO.get(TL.get());
        if(config == null){
            config = wxAppService.selectByPrimaryKey(appId);
            APP_INFO.put(appId, config);
        }
        return config;
    }

    public static void setAccessToken(String accessToken, String appId) {
        WxApp config = wxAppService.selectByAppId(appId);
        config.setAccessToken(accessToken);
        APP_INFO.put(config.getAccount(), config);
        //更新数据库中的accessToken
        wxAppService.updateByPrimaryKeySelective(config);
    }

    public static void addApp(WxApp app) {
        APP_INFO.put(app.getAccount(), app);
    }

    public static void removeThreadLocalAppId() {
        APP_INFO.remove(APP_INFO.get(TL.get()));
        TL.remove();
    }

    public static void setAccount(String account) {
        TL.set(account);
        WxApp config = APP_INFO.get(account);
        if (config == null) {
            config = wxAppService.selectByPrimaryKey(account);
            APP_INFO.put(account, config);
        }
    }

    public static String getAccount() {
        return TL.get();
    }
}
