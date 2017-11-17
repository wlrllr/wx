package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.Alias;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class LinkMsg extends BaseMsg {

    @Alias("Title")
    private String title;
    @Alias("Description")
    private String description;
    @Alias("Url")
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
