package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/8.
 */
public class OutVoiceMsg extends Msg {

    @XmlField("Voice")
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    /**
     *
     * @param mediaId 不能为空
     */
    public void addVoice(String mediaId) {
        voice = new Voice();
        voice.setMediaId(mediaId);
    }

    class Voice {
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
