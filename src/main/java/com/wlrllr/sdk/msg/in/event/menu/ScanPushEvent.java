package com.wlrllr.sdk.msg.in.event.menu;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.in.event.BaseEvent;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class ScanPushEvent extends BaseEvent {

    @Alias("EventKey")
    private String eventKey;
    @Alias("ScanCodeInfo")
    private ScanCodeInfo codeInfo;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public ScanCodeInfo getCodeInfo() {
        return codeInfo;
    }

    public void setCodeInfo(ScanCodeInfo codeInfo) {
        this.codeInfo = codeInfo;
    }
}
