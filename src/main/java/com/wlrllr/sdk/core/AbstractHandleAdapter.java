package com.wlrllr.sdk.core;

import com.wlrllr.sdk.msg.in.*;
import com.wlrllr.sdk.msg.in.event.*;
import com.wlrllr.sdk.msg.in.event.menu.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 对接收到的所有消息和事件做默认处理
 * Created by wlrllr on 2017/11/8.
 */
public class AbstractHandleAdapter extends AbstractHandler {

    @Override
    protected String doTextMsg(TextMsg inTextMsg) {
        return defaultDo();
    }

    @Override
    protected String doLinkMsg(LinkMsg inLinkMsg) {
        return defaultDo();
    }

    @Override
    protected String doImageMsg(ImageMsg inImageMsg) {
        return defaultDo();
    }

    @Override
    protected String doVoiceMsg(VoiceMsg inVoiceMsg) {
        return defaultDo();
    }

    @Override
    protected String doVideoMsg(VideoMsg inVideoMsg) {
        return defaultDo();
    }

    @Override
    protected String doShortVideoMsg(ShortVideoMsg inShortVideoMsg) {
        return defaultDo();
    }

    @Override
    protected String doLocationMsg(LocationMsg inLocationMsg) {
        return defaultDo();
    }

    @Override
    protected String doFollowEvent(FollowEvent inFollowEvent) {
        return defaultDo();
    }

    @Override
    protected String doLocationEvent(LocationEvent inLocationEvent) {
        return defaultDo();
    }

    @Override
    protected String doMenuClickEvent(ClickEvent clickEvent) {
        return defaultDo();
    }

    @Override
    protected String doMenuUrlEvent(ViewEvent viewEvent) {
        return defaultDo();
    }

    @Override
    protected String doScanEvent(ScanEvent scanEvent) {
        return defaultDo();
    }

    @Override
    protected String doLocationSelectEvent(LocationSelectEvent locationSelectEvent) {
        return defaultDo();
    }

    /**
     * 包含三种事件pic_weixin，pic_photo_or_album，pic_sysphoto
     *
     * @param picEvent
     * @return
     */
    @Override
    protected String doPicEvent(PicEvent picEvent) {
        return defaultDo();
    }

    @Override
    protected String doScanPushEvent(ScanPushEvent scanPushEvent) {
        return defaultDo();
    }

    @Override
    protected String doScanWaitEvent(ScanWaitEvent scanWaitEvent) {
        return defaultDo();
    }

    @Override
    protected String doMassSendJobEvent(MassSendJobEvent massSendJobEvent) {
        return defaultDo();
    }

    public void customerServerUrl(HttpServletRequest request) {

    }

    public String defaultDo() {
        return "";
    }
}
