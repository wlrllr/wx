package com.wlrllr.demo;

import com.wlrllr.config.WxProperties;
import com.wlrllr.constants.DataConstants;
import com.wlrllr.sdk.api.TokenApi;
import com.wlrllr.sdk.core.AbstractHandleAdapter;
import com.wlrllr.sdk.interceptor.ThreadLocalParam;
import com.wlrllr.sdk.msg.in.TextMsg;
import com.wlrllr.sdk.msg.in.event.FollowEvent;
import com.wlrllr.sdk.msg.in.event.MenuClickEvent;
import com.wlrllr.sdk.msg.out.OutTextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseHandler extends AbstractHandleAdapter {

	private static final Logger logger = LoggerFactory.getLogger(BaseHandler.class);

	@Autowired
	private WxProperties wxProperties;
	@Autowired
	private TokenApi tokenApi;

	@PostConstruct
	public void refreshAccessToken() {
		logger.info(">>>>>>>>初始化获取token<<<<<<<");
		DataConstants.ACCESSTOKEN = tokenApi.getAccessToken(wxProperties.getAppId(), wxProperties.getAppSecret());
	}

	@RequestMapping("/wxServer")
	public String server(HttpServletRequest request) {
		return index(request);
	}

	@Override
	protected String doTextMsg(TextMsg inTextMsg) {
		OutTextMsg msg = new OutTextMsg(inTextMsg);
		msg.setContent("你好啊"+ ThreadLocalParam.getThreadLocalAppId());
		return msg.toXml();
	}

	@Override
	protected String doFollowEvent(FollowEvent inFollowEvent) {
		OutTextMsg msg = new OutTextMsg(inFollowEvent);
		msg.setContent("感谢关注");
		return msg.toXml();
	}

	@Override
	protected String doMenuClickEvent(MenuClickEvent menuClickEvent) {
		if("SEARCH_SOMETHING".equals(menuClickEvent.getEventKey())){
			OutTextMsg msg = new OutTextMsg(menuClickEvent);
			msg.setContent("你点击了按钮，查写东西");
			return msg.toXml();
		}
		return super.doMenuClickEvent(menuClickEvent);
	}
}
