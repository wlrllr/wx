package com.wlrllr.core.bean;

import com.wlrllr.constants.WxConstants;

/**
 * Created by w_zhanglong on 2017/10/19.
 */
public class TextMessage extends BaseMessage{

    private String Content;

    public TextMessage() {
        super();
        setMsgType(WxConstants.MESSAGE_TYPE_TEXT);
    }

    public TextMessage(String FromUserName,String ToUserName) {
        super();
        setFromUserName(FromUserName);
        setToUserName(ToUserName);
        setMsgType(WxConstants.MESSAGE_TYPE_TEXT);
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }
}
