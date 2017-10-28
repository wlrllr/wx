package com.wlrllr.core.bean;

import com.wlrllr.constants.WxConstants;

import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/19.
 */
public class ResponseMessage {
    private String messageType;
    private String content;
    private String mediaId;
    private String title;
    private String description;
    private String thumbMediaId;
    private String musicUrl;
    private String hQMusicUrl;
    private String articleCount;
    private List<Item> articles;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Item> getArticles() {
        return articles;
    }

    public void setArticles(List<Item> articles) {
        this.articles = articles;
    }

    public Object create(String fromUserName,String toUserName){
        if(WxConstants.MESSAGE_TYPE_TEXT.equals(messageType)){
            TextMessage textMessage = new TextMessage(fromUserName,toUserName);
            textMessage.setContent(getContent());
            return textMessage;
        }
        return null;
    }
}
