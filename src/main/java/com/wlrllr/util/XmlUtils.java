package com.wlrllr.util;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.List;

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
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
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
        xstream.alias("xml", text.getClass());
        return xstream.toXML(text);
    }
}
