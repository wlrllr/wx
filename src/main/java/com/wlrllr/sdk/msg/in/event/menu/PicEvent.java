package com.wlrllr.sdk.msg.in.event.menu;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.in.event.BaseEvent;

/**
 * 事件pic_weixin，pic_photo_or_album，pic_sysphoto都采用该model
 * Created by wlrllr on 2017/11/9.
 */
public class PicEvent extends BaseEvent {

    @Alias("EventKey")
    private String eventKey;
    @Alias("SendPicsInfo")
    private PicInfo picInfo;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public PicInfo getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(PicInfo picInfo) {
        this.picInfo = picInfo;
    }
}
