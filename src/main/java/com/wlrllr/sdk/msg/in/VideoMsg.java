package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.Alias;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class VideoMsg extends BaseMsg {

    @Alias("ThumbMediaId")
    private String thumbMediaId;
    @Alias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
