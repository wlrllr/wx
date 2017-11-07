package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class LinkMsg extends BaseMsg {

    @XmlField("Title")
    private String title;
    @XmlField("Description")
    private String description;
    @XmlField("Url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
