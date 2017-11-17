package com.wlrllr.service;

import com.wlrllr.model.WxApp;
import com.wlrllr.model.WxAppExample;

import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public interface WxAppService extends BaseService<WxApp, WxAppExample, String> {

    WxApp selectByAppId(String appId);

    List<WxApp> all();
}
