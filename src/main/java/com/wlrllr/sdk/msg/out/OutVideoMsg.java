package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/8.
 */
public class OutVideoMsg extends Msg {

    @Alias("Video")
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    /**
     * @param mediaId     不能为空
     * @param title
     * @param description
     */
    public void addVideo(String mediaId, String title, String description) {
        video = new Video();
        video.setMediaId(mediaId);
        video.setTitle(title);
        video.setDescription(description);
    }

    class Video {

        @Alias("MediaId")
        private String mediaId;
        @Alias("Title")
        private String title;
        @Alias("Description")
        private String description;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

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
    }

}
