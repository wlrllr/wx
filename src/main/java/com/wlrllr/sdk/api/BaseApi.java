package com.wlrllr.sdk.api;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.sdk.api.model.JSONObj;
import com.wlrllr.sdk.core.ThreadLocalParam;
import com.wlrllr.sdk.core.config.WxProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by w_zhanglong on 2017/10/27.
 */
public class BaseApi {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY_ERROR_CODE = "errcode";
    @Autowired
    protected WxProperties wxProperties;

    public JSONObj returnJson(JSONObj result) {
        log(result);
        if (result != null && result.getIntValue(KEY_ERROR_CODE) == 0) {
            return result;
        }
        return null;
    }

    public Boolean returnBoolean(JSONObject result) {
        log(result);
        if (result != null && result.getIntValue(KEY_ERROR_CODE) == 0) {
            return true;
        }
        return false;
    }

    public String returnString(JSONObject result, String key) {
        log(result);
        String value = result.getString(key);
        if (result != null && StringUtils.isNotEmpty(value)) {
            return value;
        }
        return "";
    }

    private void log(JSONObject result){
        logger.info(">>>>>>>返回结果:{}<<<<<<<<<",result.toJSONString());

    }
    public String fillUrlParam(String url, String... param) {
        if(param != null && param.length>0){
            int length = param.length;
            if(length == 1){
                url = String.format(url, ThreadLocalParam.getApp().getAccessToken(),param[0]);
            }else if(length==2){
                url = String.format(url, ThreadLocalParam.getApp().getAccessToken(),param[0],param[1]);
            }else if(length==3){
                url = String.format(url, ThreadLocalParam.getApp().getAccessToken(),param[0],param[1],param[2]);
            }else if(length==4){
                url = String.format(url, ThreadLocalParam.getApp().getAccessToken(),param[0],param[1],param[2],param[3]);
            }
        }else{
            url = String.format(url, ThreadLocalParam.getApp().getAccessToken());
        }
        logger.info(">>>>>>>>>>请求链接：{}<<<<<<<<<<",url);
        return url;
    }

}
