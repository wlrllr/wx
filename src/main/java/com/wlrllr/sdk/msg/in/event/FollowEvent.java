
package com.wlrllr.sdk.msg.in.event;

import com.wlrllr.sdk.core.Alias;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class FollowEvent extends BaseEvent{

    @Alias("EventKey")
    private String eventKey;
    @Alias("Ticket")
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






