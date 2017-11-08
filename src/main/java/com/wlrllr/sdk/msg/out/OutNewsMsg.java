package com.wlrllr.sdk.msg.out;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;
import com.wlrllr.util.XmlUtils;

import java.util.List;

public class OutNewsMsg extends Msg {

    @XmlField("ArticleCount")
    private Integer count;
    @XmlField("Articles")
    private List<Article> articles;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toXml(){
        return XmlUtils.messageToXML(this,Article.class);
    }
}

