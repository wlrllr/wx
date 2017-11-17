package com.wlrllr.sdk.api;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.sdk.core.config.WxProperties;
import com.wlrllr.sdk.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户相关接口
 * Created by w_zhanglong on 2017/10/24.
 */
@Component
public class TokenApi extends BaseApi {

    @Autowired
    private WxProperties wxProperties;

    public String getAccessToken(String appId, String appSecret) {
        String accessTokenUrl = String.format(wxProperties.getAccessTokenUrl(), appId, appSecret);
        JSONObject result = HttpUtils.get(accessTokenUrl);
        return returnString(result, "access_token");
    }

}
