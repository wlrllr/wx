package com.wlrllr.sdk.msg.in.event;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/7.
 */
public abstract class BaseEvent extends Msg {

    @Alias("Event")
    protected String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
