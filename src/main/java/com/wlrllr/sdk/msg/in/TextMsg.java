package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.Alias;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class TextMsg extends BaseMsg {

    @Alias("Content")
    private  String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
