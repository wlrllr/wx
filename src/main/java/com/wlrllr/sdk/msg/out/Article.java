package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.XmlField;

@XmlField("item")
public class Article {
    @XmlField("Title")
    private String title;
    @XmlField("Description")
    private String description;
    @XmlField("PicUrl")
    private String picUrl;
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
