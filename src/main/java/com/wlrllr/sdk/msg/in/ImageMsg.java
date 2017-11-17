package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.Alias;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class ImageMsg extends BaseMsg {

    @Alias("PicUrl")
    private String picUrl;
    @Alias("MediaId")
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
