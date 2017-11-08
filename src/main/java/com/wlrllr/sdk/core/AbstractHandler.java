package com.wlrllr.sdk.core;

import com.wlrllr.config.WxProperties;
import com.wlrllr.sdk.msg.Msg;
import com.wlrllr.sdk.msg.MsgParser;
import com.wlrllr.sdk.msg.in.*;
import com.wlrllr.sdk.msg.in.event.*;
import com.wlrllr.util.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wlrllr on 2017/11/8.
 */
public abstract class AbstractHandler {

    @Autowired
    protected WxProperties wxProperties;

    public String index(HttpServletRequest request) {
        try {
            Msg msg = MsgParser.parse(request.getInputStream());
            if(msg == null){ //绑定服务器校验
               return verify(request);
            }
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
        } catch (Exception e) {

        }
        return "";
    }

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
}
