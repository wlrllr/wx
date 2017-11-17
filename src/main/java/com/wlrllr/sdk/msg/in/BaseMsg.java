package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/7.
 */
public abstract class BaseMsg extends Msg {

    @Alias("MsgId")
    protected Long msgId;


    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
