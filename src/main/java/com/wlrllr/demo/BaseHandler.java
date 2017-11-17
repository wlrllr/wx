package com.wlrllr.demo;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.model.WxApp;
import com.wlrllr.model.WxUser;
import com.wlrllr.sdk.api.TokenApi;
import com.wlrllr.sdk.api.UserApi;
import com.wlrllr.sdk.core.AbstractHandleAdapter;
import com.wlrllr.sdk.core.ThreadLocalParam;
import com.wlrllr.sdk.core.config.WxProperties;
import com.wlrllr.sdk.msg.in.ImageMsg;
import com.wlrllr.sdk.msg.in.TextMsg;
import com.wlrllr.sdk.msg.in.event.FollowEvent;
import com.wlrllr.sdk.msg.in.event.menu.ClickEvent;
import com.wlrllr.sdk.msg.in.event.menu.PicEvent;
import com.wlrllr.sdk.msg.in.event.menu.ScanWaitEvent;
import com.wlrllr.sdk.msg.out.OutTextMsg;
import com.wlrllr.sdk.type.EventType;
import com.wlrllr.sdk.util.JsonUtils;
import com.wlrllr.service.WxAppService;
import com.wlrllr.service.WxUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static org.apache.coyote.http11.Constants.a;

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
    @Autowired
    private WxAppService wxAppService;

    @PostConstruct
    public void refreshAccessToken() {
        logger.info(">>>>>>>>初始化获取token<<<<<<<");
        wxAppService.selectByAppId(wxProperties.getAppId());
        List<WxApp> list = wxAppService.all();
        if(list != null){
            for(WxApp wxApp : list){
                String accessToken = tokenApi.getAccessToken(wxApp.getAppId(), wxApp.getAppSecret());
                wxApp.setAccessToken(accessToken);
                ThreadLocalParam.addApp(wxApp);
            }
        }
    }

    @RequestMapping("/server")
    @Override
    public void customerServerUrl(HttpServletRequest request) {
    }

    @Override
    protected String doTextMsg(TextMsg inTextMsg) {
        OutTextMsg msg = new OutTextMsg(inTextMsg);
        msg.setContent("你好啊" + ThreadLocalParam.getAccount());
        return msg.toXml();
    }

    @Override
    protected String doFollowEvent(FollowEvent inFollowEvent) {
        if(EventType.UN_SUBSCRIBE.equals(inFollowEvent.getEvent())){
            return "";
        }
        OutTextMsg msg = new OutTextMsg(inFollowEvent);
        msg.setContent("感谢关注");
        //获取用户信息
        JSONObject user = userApi.getUserInfo(inFollowEvent.getFromUser());
        logger.info(">>>>>>用户信息:{}<<<<<<<",user.toJSONString());
        WxUser wxUser = (WxUser) JsonUtils.toBean(user,WxUser.class);
        wxUser.setAppId(ThreadLocalParam.getAccount());
        wxUser.setSubscribeTime(new Date());
        /*wxUser.setOpenId(user.getString("openid"));
        wxUser.setCity(user.getString("city"));
        wxUser.setCountry(user.getString("country"));
        wxUser.setHeadimgurl(user.getString("headimgurl"));
        wxUser.setNickName(user.getString("nickname"));
        wxUser.setSex(user.getInteger("sex"));
        wxUser.setProvince(user.getString("province"));*/
        wxUserService.addUser(wxUser);
        return msg.toXml();
    }

    @Override
    protected String doMenuClickEvent(ClickEvent clickEvent) {
        if ("SEARCH_SOMETHING".equals(clickEvent.getEventKey())) {
            OutTextMsg msg = new OutTextMsg(clickEvent);
            msg.setContent("你点击了按钮，查写东西");
            return msg.toXml();
        }
        return super.doMenuClickEvent(clickEvent);
    }


    @Override
    protected String doScanWaitEvent(ScanWaitEvent scanWaitEvent) {
        OutTextMsg msg = new OutTextMsg(scanWaitEvent);
        msg.setContent("(*@ο@*) 哇～!扫到了好东西：" + scanWaitEvent.getCodeInfo().getScanResult());
        String str = msg.toXml();
        logger.info(">>>>>>>>回复消息:{}<<<<<<<<<", str);
        return str;
    }

    /**
     * 不能回复？
     *
     * @param picEvent
     * @return
     */
    @Override
    protected String doPicEvent(PicEvent picEvent) {
        OutTextMsg msg = new OutTextMsg(picEvent);
        if (EventType.PIC_PHOTO.equals(picEvent.getEvent())) {
            msg.setContent("选取了图片或拍照");
        } else if (EventType.PIC_SYSPHOTO.equals(picEvent.getEvent())) {
            msg.setContent("选取了拍照");
        } else if (EventType.PIC_WX.equals(picEvent.getEvent())) {
            msg.setContent("选取了图片");
        }
        String str = msg.toXml();
        logger.info(">>>>>>>>回复消息:{}<<<<<<<<<", str);
        return str;
    }

    @Override
    protected String doImageMsg(ImageMsg inImageMsg) {
        OutTextMsg msg = new OutTextMsg(inImageMsg);
        msg.setContent("发送了一张照片");
        String str = msg.toXml();
        logger.info(">>>>>>>>回复消息:{}<<<<<<<<<", str);
        return str;
    }
}
