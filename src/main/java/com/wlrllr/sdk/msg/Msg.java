package com.wlrllr.sdk.msg;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.util.XmlUtils;

import java.io.Serializable;

/**
 * Created by wlrllr on 2017/11/6.
 */
public abstract class Msg implements Serializable{

    @XmlField("ToUserName")
    protected String toUser;
    @XmlField("FromUserName")
    protected String fromUser;
    @XmlField("CreateTime")
    protected Long createTime;
    @XmlField("MsgType")
    protected String msgType;

    /**
     * 请求消息对象转换成响应消息对象
     * @param msg
     */
    protected void covert(Msg msg){
        toUser = msg.getFromUser();
        fromUser = msg.getToUser();
        createTime = now();
        msgType = msg.msgType;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String toXml(){
       return XmlUtils.messageToXML(this);
    }

    public Long now() {
        return System.currentTimeMillis() / 1000;
    }
}
