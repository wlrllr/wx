package com.wlrllr.sdk.util;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.out.Article;
import com.wlrllr.sdk.msg.out.OutImageMsg;
import com.wlrllr.sdk.msg.out.OutNewsMsg;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);


    public static void xmlToJson(Element root, JSONObject data) {
        List<Element> elementList = root.elements();
        if (elementList.size() == 0) {
            data.put(root.getName(), root.getText());
        } else {
            for (Element e : elementList) {
                xmlToJson(e, data);
            }
        }
    }

    /**
     * 扩展xstream使其支持CDATA
     */
    private static MyXStream xstream = new MyXStream(new XppDriver() {
        @Override
        protected synchronized XmlPullParser createParser() throws XmlPullParserException {
            return super.createParser();
        }

        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(xstream.get(name), clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static String messageToXML(Object text, Class... clazzs) {
        if (text == null) {
            return null;
        }
        xstream.myAlias("xml", text.getClass());
        if (clazzs != null) {
            for (Class clazz : clazzs) {
                Alias annotation = (Alias) clazz.getAnnotation(Alias.class);
                xstream.myAlias(annotation.value(), clazz);
            }
        }
        return xstream.toXML(text);
    }

    public static void main(String[] args) {
        OutImageMsg imageMsg = new OutImageMsg();
        imageMsg.addImage("123213");
        imageMsg.setCreateTime(System.currentTimeMillis());
        imageMsg.setFromUser("fromUser");
        imageMsg.setToUser("toUser");
        System.out.print(imageMsg.toXml());

        OutNewsMsg newsMsg = new OutNewsMsg();
        newsMsg.setCount(2);
        newsMsg.setCreateTime(System.currentTimeMillis());
        newsMsg.setFromUser("fromUser");
        newsMsg.setToUser("toUser");
        List<Article> list = new ArrayList<>();
        Article article = new Article();
        article.setDescription("Description");
        article.setPicUrl("picUrl");
        article.setTitle("title");
        article.setUrl("url");
        list.add(article);
        newsMsg.setArticles(list);
        // xstream.myAlias("item", Article.class);
        System.out.print(newsMsg.toXml());
    }
}

class MyXStream extends XStream {
    private Map<String, String> xmlNames;

    public void myAlias(String name, Class type) {
        super.alias(name, type);
        if (xmlNames == null) {
            xmlNames = new HashMap<>();
        }
        for (; type != Object.class; type = type.getSuperclass()) {
            for (Field field : type.getDeclaredFields()) {
                Alias annotation = field.getAnnotation(Alias.class);
                if (annotation != null && StringUtils.isNotBlank(annotation.value())) {
                    xmlNames.put(field.getName(), annotation.value());
                }
            }
        }
    }

    public MyXStream(HierarchicalStreamDriver driver) {
        super(driver);
    }

    public String get(String key) {
        String value = xmlNames.get(key);
        if (StringUtils.isBlank(value)) {
            return key;
        }
        return value;
    }
}
