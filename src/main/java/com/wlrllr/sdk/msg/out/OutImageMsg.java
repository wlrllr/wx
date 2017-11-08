package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;

public class OutImageMsg extends Msg {

    @XmlField("Image")
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void addImage(String mediaId) {
        image = new Image();
        image.setMediaId(mediaId);
    }

    class Image{
        @XmlField("MediaId")
        private String mediaId;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
    }
}

