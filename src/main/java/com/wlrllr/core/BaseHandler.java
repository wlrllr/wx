package com.wlrllr.core;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.config.WxProperties;
import com.wlrllr.constants.DataConstants;
import com.wlrllr.util.WxUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseHandler {

	private static final Logger logger = LoggerFactory.getLogger(BaseHandler.class);

	@Autowired
	private WxProperties wxProperties;
	@Autowired
	private WxApi wxApi;
	@Autowired
	private MessageService messageService;

	@PostConstruct
	public void refreshAccessToken() {
		logger.info(">>>>>>>>初始化获取token<<<<<<<");
		refreshAccessToken(wxProperties.getAppId(), wxProperties.getAppSecret());
	}

	public void refreshAccessToken(String appId, String appSecret) {
		DataConstants.ACCESSTOKEN = wxApi.getAccessToken(appId, appSecret);
	}

	@RequestMapping("/wxServer")
	public String server(HttpServletRequest request) {
		JSONObject param = WxUtils.parseRequest(request);
		logger.info(">>>>>>>>请求参数<<<<<<<{}",JSONObject.toJSONString(param));
		//String type = request.getParameter("encrypt_type");
		String msgType = param.getString("MsgType");
		//绑定服务器验证
		if(StringUtils.isBlank(msgType)){
			return verify(request);
		}
		return messageService.getResponseMessge(param);
	}


	/**
	 * 绑定服务器URL时校验用
	 * @param request
	 * @return
	 */
	private String verify(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if(WxUtils.checkSignature(signature,timestamp,nonce,wxProperties.getToken())){
			return echostr;
		}else{
			return "";
		}

	}
}
