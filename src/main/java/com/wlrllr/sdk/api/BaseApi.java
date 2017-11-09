package com.wlrllr.sdk.api;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.config.WxProperties;
import com.wlrllr.constants.DataConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by w_zhanglong on 2017/10/27.
 */
public class BaseApi {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY_ERROR_CODE="errcode";
    @Autowired
    public WxProperties wxProperties;

    public JSONObject returnJson(JSONObject result) {
        if (result != null && result.getIntValue(KEY_ERROR_CODE) == 0) {
            return result;
        }
        return null;
    }

    public Boolean returnBoolean(JSONObject result) {
        if (result != null && result.getIntValue(KEY_ERROR_CODE) == 0) {
            return true;
        }
        return false;
    }

    public String returnString(JSONObject result, String key) {
        String value = result.getString(key);
        if (result != null && StringUtils.isNotEmpty(value)) {
            return value;
        }
        return "";
    }

    public String fillUrlParam(String url, String... param) {
        return String.format(url, DataConstants.ACCESSTOKEN, param);
    }
}
