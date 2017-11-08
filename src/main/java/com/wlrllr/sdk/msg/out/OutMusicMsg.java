package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/8.
 */
public class OutMusicMsg extends Msg {

    @XmlField("Music")
    private Music music;


    /**
     *
     * @param mediaId ThumbMediaId 不能为空
     * @param title Title
     * @param description Description
     * @param url MusicUrl
     * @param hqUrl HQMusicUrl
     */
    public void addVideo(String mediaId,String title,String description,String url,String hqUrl) {
        music = new Music();
        music.setMediaId(mediaId);
        music.setTitle(title);
        music.setDescription(description);
        music.setUrl(url);
        music.setHqUrl(hqUrl);
    }

    class Music {
        @XmlField("ThumbMediaId")
        private String mediaId;
        @XmlField("Title")
        private String title;
        @XmlField("Description")
        private String description;
        @XmlField("MusicUrl")
        private String url;
        @XmlField("HQMusicUrl")
        private String hqUrl;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHqUrl() {
            return hqUrl;
        }

        public void setHqUrl(String hqUrl) {
            this.hqUrl = hqUrl;
        }
    }

}
