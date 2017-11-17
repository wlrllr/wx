package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.Alias;

@Alias("item")
public class Article {
    @Alias("Title")
    private String title;
    @Alias("Description")
    private String description;
    @Alias("PicUrl")
    private String picUrl;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
