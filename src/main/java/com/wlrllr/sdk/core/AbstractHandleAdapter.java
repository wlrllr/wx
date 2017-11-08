package com.wlrllr.sdk.core;

import com.wlrllr.sdk.msg.in.*;
import com.wlrllr.sdk.msg.in.event.*;

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
    protected String doMenuClickEvent(MenuClickEvent menuClickEvent) {
        return defaultDo();
    }

    @Override
    protected String doMenuUrlEvent(MenuUrlEvent menuUrlEvent) {
        return defaultDo();
    }

    @Override
    protected String doScanEvent(ScanEvent scanEvent) {
        return defaultDo();
    }

    public String defaultDo(){
        return "";
    }
}
