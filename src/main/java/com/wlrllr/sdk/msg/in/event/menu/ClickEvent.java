package com.wlrllr.sdk.msg.in.event.menu;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.in.event.BaseEvent;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class ClickEvent extends BaseEvent {
    @Alias("EventKey")
    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
