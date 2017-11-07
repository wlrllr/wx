package com.wlrllr.sdk.msg.in.event;

import com.wlrllr.sdk.core.XmlField;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class ScanEvent extends BaseEvent {

    @XmlField("EventKey")
    private String eventKey;
    @XmlField("Ticket")
    private String ticket;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
