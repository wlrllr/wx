package com.wlrllr.demo;

import com.wlrllr.config.WxProperties;
import com.wlrllr.constants.DataConstants;
import com.wlrllr.sdk.api.TokenApi;
import com.wlrllr.sdk.api.UserApi;
import com.wlrllr.sdk.core.AbstractHandleAdapter;
import com.wlrllr.sdk.interceptor.ThreadLocalParam;
import com.wlrllr.sdk.msg.in.ImageMsg;
import com.wlrllr.sdk.msg.in.TextMsg;
import com.wlrllr.sdk.msg.in.event.FollowEvent;
import com.wlrllr.sdk.msg.in.event.menu.ClickEvent;
import com.wlrllr.sdk.msg.in.event.menu.PicEvent;
import com.wlrllr.sdk.msg.in.event.menu.ScanWaitEvent;
import com.wlrllr.sdk.msg.out.OutTextMsg;
import com.wlrllr.sdk.type.EventType;
import com.wlrllr.service.WxUserService;
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
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private UserApi userApi;

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
		//获取用户信息
		userApi.getUserInfo(inFollowEvent.getFromUser());
		//wxUserService.updateByPrimaryKeySelective();
		return msg.toXml();
	}

	@Override
	protected String doMenuClickEvent(ClickEvent clickEvent) {
		if("SEARCH_SOMETHING".equals(clickEvent.getEventKey())){
			OutTextMsg msg = new OutTextMsg(clickEvent);
			msg.setContent("你点击了按钮，查写东西");
			return msg.toXml();
		}
		return super.doMenuClickEvent(clickEvent);
	}


	@Override
	protected String doScanWaitEvent(ScanWaitEvent scanWaitEvent) {
		OutTextMsg msg = new OutTextMsg(scanWaitEvent);
		msg.setContent("(*@ο@*) 哇～!扫到了好东西："+scanWaitEvent.getCodeInfo().getScanResult());
		String str = msg.toXml();
		logger.info(">>>>>>>>回复消息:{}<<<<<<<<<",str);
		return str;
	}

	/**
	 * 不能回复？
	 * @param picEvent
	 * @return
	 */
	@Override
	protected String doPicEvent(PicEvent picEvent) {
		OutTextMsg msg = new OutTextMsg(picEvent);
		if(EventType.PIC_PHOTO.equals(picEvent.getEvent())){
			msg.setContent("选取了图片或拍照");
		}else if(EventType.PIC_SYSPHOTO.equals(picEvent.getEvent())){
			msg.setContent("选取了拍照");
		}else if(EventType.PIC_WX.equals(picEvent.getEvent())){
			msg.setContent("选取了图片");
		}
		String str = msg.toXml();
		logger.info(">>>>>>>>回复消息:{}<<<<<<<<<",str);
		return str;
	}

	@Override
	protected String doImageMsg(ImageMsg inImageMsg) {
		OutTextMsg msg = new OutTextMsg(inImageMsg);
		msg.setContent("发送了一张照片");
		String str = msg.toXml();
		logger.info(">>>>>>>>回复消息:{}<<<<<<<<<",str);
		return str;
	}
}
