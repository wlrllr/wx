package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.Msg;

public class OutImageMsg extends Msg {

    @Alias("Image")
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

    class Image {
        @Alias("MediaId")
        private String mediaId;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
    }
}

