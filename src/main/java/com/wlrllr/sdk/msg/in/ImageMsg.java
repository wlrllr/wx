package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class ImageMsg extends BaseMsg {

    @XmlField("PicUrl")
    private String picUrl;
    @XmlField("MediaId")
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
