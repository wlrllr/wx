package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;
import com.wlrllr.sdk.type.MsgType;


public class OutTextMsg extends Msg {

    @XmlField("Content")
    private String content; //不能为空

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OutTextMsg(Msg msg) {
        msg.setMsgType(MsgType.TEXT);
        covert(msg);
    }
}


