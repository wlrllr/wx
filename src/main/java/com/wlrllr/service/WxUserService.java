package com.wlrllr.service;

import com.wlrllr.model.WxUser;
import com.wlrllr.model.WxUserExample;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public interface WxUserService  extends BaseService<WxUser,WxUserExample,Integer>{

    void forbid(String openid,String appId);

    void addUser(WxUser user);
}
