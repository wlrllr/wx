package com.wlrllr.sdk.core;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.config.WxProperties;
import com.wlrllr.sdk.interceptor.Interceptor;
import com.wlrllr.sdk.interceptor.ThreadLocalParam;
import com.wlrllr.sdk.msg.Msg;
import com.wlrllr.sdk.msg.MsgParser;
import com.wlrllr.sdk.msg.in.*;
import com.wlrllr.sdk.msg.in.event.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wlrllr on 2017/11/8.
 */
public abstract class AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);
    @Autowired
    protected WxProperties wxProperties;

    public String index(HttpServletRequest request) {
        try {
            if(isVerify(request)){
                return verify(request);
            }
            return invoke(MsgParser.parse(request.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String invoke(Msg msg) {
        //拦截做的有问题先这样
        if (msg != null) {
            String appId = msg.getToUser();
            if(StringUtils.isNotBlank(appId)){
                ThreadLocalParam.setThreadLocalAppId(appId);
            }
        }
        logger.info(">>>>>>>>>>Thread:{},请求参数:{}<<<<<<<<", Thread.currentThread(), JSONObject.toJSONString(msg));
        if (msg instanceof TextMsg)
            return doTextMsg((TextMsg) msg);
        else if (msg instanceof LinkMsg)
            return doLinkMsg((LinkMsg) msg);
        else if (msg instanceof ImageMsg)
            return doImageMsg((ImageMsg) msg);
        else if (msg instanceof VoiceMsg)
            return doVoiceMsg((VoiceMsg) msg);
        else if (msg instanceof VideoMsg)
            return doVideoMsg((VideoMsg) msg);
        else if (msg instanceof ShortVideoMsg)
            return doShortVideoMsg((ShortVideoMsg) msg);
        else if (msg instanceof LocationMsg)
            return doLocationMsg((LocationMsg) msg);
            //事件处理
        else if (msg instanceof FollowEvent)
            return doFollowEvent((FollowEvent) msg);
        else if (msg instanceof LocationEvent)
            return doLocationEvent((LocationEvent) msg);
        else if (msg instanceof MenuClickEvent)
            return doMenuClickEvent((MenuClickEvent) msg);
        else if (msg instanceof MenuUrlEvent)
            return doMenuUrlEvent((MenuUrlEvent) msg);
        else if (msg instanceof ScanEvent)
            return doScanEvent((ScanEvent) msg);
        return "";
    }

    private String verify(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (checkSignature(signature, timestamp, nonce, wxProperties.getToken())) {
            return echostr;
        } else {
            return "";
        }

    }

    // 处理接收到的文本消息
    protected abstract String doTextMsg(TextMsg inTextMsg);

    // 处理接收到的链接消息
    protected abstract String doLinkMsg(LinkMsg inLinkMsg);

    // 处理接收到的图片消息
    protected abstract String doImageMsg(ImageMsg inImageMsg);

    // 处理接收到的语音消息
    protected abstract String doVoiceMsg(VoiceMsg inVoiceMsg);

    // 处理接收到的视频消息
    protected abstract String doVideoMsg(VideoMsg inVideoMsg);

    // 处理接收到的视频消息
    protected abstract String doShortVideoMsg(ShortVideoMsg inShortVideoMsg);

    // 处理接收到的地址位置消息
    protected abstract String doLocationMsg(LocationMsg inLocationMsg);

    // 处理接收到的关注/取消关注事件
    protected abstract String doFollowEvent(FollowEvent inFollowEvent);

    // 处理接收到的上报地理位置事件
    protected abstract String doLocationEvent(LocationEvent inLocationEvent);

    //点击菜单按钮事件
    protected abstract String doMenuClickEvent(MenuClickEvent menuClickEvent);

    //点击菜单Url事件
    protected abstract String doMenuUrlEvent(MenuUrlEvent menuUrlEvent);

    //扫描事件
    protected abstract String doScanEvent(ScanEvent scanEvent);

    private boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        if (StringUtils.isNotBlank(signature) && StringUtils.isNotBlank(timestamp) && StringUtils.isNotBlank(nonce)) {
            List<String> params = new ArrayList<>();
            params.add(token);
            params.add(timestamp);
            params.add(nonce);
            Collections.sort(params);
            StringBuilder signBuilder = new StringBuilder();
            for (String string : params) {
                signBuilder.append(string);
            }
            String sign = DigestUtils.sha1Hex(signBuilder.toString()).toLowerCase();
            if (sign.equalsIgnoreCase(signature)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVerify(HttpServletRequest request){
        String echostr = request.getParameter("echostr");
       if(StringUtils.isNotBlank(echostr)){
           return true;
       }
       return false;
    }
}
