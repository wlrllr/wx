package com.wlrllr.util;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
import com.wlrllr.core.bean.TextMessage;
import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.MsgParser;
import com.wlrllr.sdk.msg.in.ImageMsg;
import com.wlrllr.sdk.msg.in.TextMsg;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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

    public static String messageToXML(Object text) {
        if(text == null){
            return null;
        }
        xstream.myAlias("xml", text.getClass());
        return xstream.toXML(text);
    }

    public static String fromXml(String is,Class clazz){
        try {
            Document document = new SAXReader().read(is);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            Element element = root.element("xml");
            logger.info("........{}",root.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
return null;
    }

    public static void main(String[] args){
        String s = JSONObject.toJSONString(MsgParser.parse("C:\\Users\\Administrator\\Desktop\\test.xml"));
        logger.info("......{}",s);
    }
}
class MyXStream extends XStream{
    private Map<String,String> xmlNames;

    public void myAlias(String name, Class type) {
        super.alias(name,type);
        if(xmlNames == null){
            xmlNames = new HashMap<>();
        }
        for(;type != Object.class;type = type.getSuperclass()){
            for(Field field : type.getDeclaredFields()){
                XmlField annotation = field.getAnnotation(XmlField.class);
                if(annotation != null && StringUtils.isNotBlank(annotation.value())){
                    xmlNames.put(field.getName(),annotation.value());
                }
            }
        }
    }

    public MyXStream(HierarchicalStreamDriver driver){
        super(driver);
    }

    public String get(String key){
        String value = xmlNames.get(key);
        if(StringUtils.isBlank(value)){
            return key;
        }
        return value;
    }
}
