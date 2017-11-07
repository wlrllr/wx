package com.wlrllr.sdk.msg.in.event;

import com.wlrllr.sdk.core.XmlField;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class MenuClickEvent extends BaseEvent {
    @XmlField("EventKey")
    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
