package com.wlrllr.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.constants.DataConstants;
import com.wlrllr.constants.WxConstants;
import com.wlrllr.util.HttpUtils;
import com.wlrllr.config.WxProperties;

@Component
public class WxApi {

	@Autowired
	private WxProperties wxProperties;

	public String getAccessToken(String appId, String appSecret) {
		String accessTokenUrl = String.format(wxProperties.getAccessTokenUrl(), appId, appSecret);
		JSONObject result = HttpUtils.get(accessTokenUrl);
		if (result == null || StringUtils.isEmpty(result.getString("access_token"))) {
			return null;
		} else {
			return result.getString("access_token");
		}
	}

	/**
	 * 消息接口
	 * 
	 * @author wlrllr
	 * 
	 */
	public  class MessageManager {

	}

	/**
	 * 客服接口
	 * 
	 * @author wlrllr
	 * 
	 */
	public  class CustomerServiceManager {

	}

}
