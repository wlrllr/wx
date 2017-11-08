package com.wlrllr.sdk.msg;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.in.*;
import com.wlrllr.sdk.msg.in.event.*;
import com.wlrllr.sdk.type.EventType;
import com.wlrllr.sdk.type.MsgType;
import com.wlrllr.util.StringUtils;
import com.wlrllr.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class MsgParser {

    private static Logger logger = LoggerFactory.getLogger(MsgParser.class);

    public static Msg parse(String url){
        try {
            return parse(new SAXReader().read(url));
        } catch (DocumentException e) {
            logger.error(">>>>>>>>>解析xml异常,url:{}<<<<<<<",url,e);
        }
        return null;
    }

    public static Msg parse(InputStream inputStream){
        try {
            return parse(new SAXReader().read(inputStream));
        } catch (DocumentException e) {
            logger.error(">>>>>>>>>解析xml异常<<<<<<<",e);
        }
        return null;
    }
    public static Msg parse(Document document) {
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        Element element = root.element("MsgType");
        if (element != null) {
            String msgType = element.getText();
            switch (msgType) {
                case MsgType.TEXT:
                    return parseMsg(elementList,TextMsg.class);
                case MsgType.IMAGE:
                    return parseMsg(elementList,ImageMsg.class);
                case MsgType.LINK:
                    return parseMsg(elementList,LinkMsg.class);
                case MsgType.LOCATION:
                    return parseMsg(elementList,LocationMsg.class);
                case MsgType.VIDEO:
                    return parseMsg(elementList,VideoMsg.class);
                case MsgType.VOICE:
                    return parseMsg(elementList,VoiceMsg.class);
                case MsgType.EVENT:
                    element = root.element("Event");
                    return parseEvent(elementList,element.getText());
            }
        }
        return null;
    }

    private static BaseMsg parseMsg(List<Element> elementList, Class<? extends BaseMsg> clazz){
        Object obj = doParse(elementList,clazz);
        if(obj == null){ //FIXME 返回默认的对象
            return null;
        }else{
            return clazz.cast(obj);
        }
    }

    private static BaseEvent parseEvent(List<Element> elementList,String event){
        Class<? extends BaseEvent> clazz = null;
        switch (event){
            case EventType.CLICK:
                clazz = MenuClickEvent.class;
                break;
            case EventType.LOCATION:
                clazz = LocationEvent.class;
                break;
            case EventType.SCAN:
                clazz = ScanEvent.class;
                break;
            case EventType.SUBSCRIBE:
                clazz = FollowEvent.class;
                break;
            case EventType.UNSUBSCRIBE:
                clazz = FollowEvent.class;
                break;
        }
        if(clazz != null){
            Object obj = doParse(elementList, clazz);
            if(obj == null){ //FIXME 返回默认的对象
                return null;
            }else{
                return clazz.cast(obj);
            }
        }
        return null;
    }

    private static Object doParse(List<Element> elementList, Class clazz) {
        try {
            Object msg = clazz.newInstance();
            if (elementList != null) {
                for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                    for (Field field : clazz.getDeclaredFields()) {
                        XmlField annotation = field.getAnnotation(XmlField.class);
                        if (annotation != null && StringUtils.isNotBlank(annotation.value())) {
                            for (Element element : elementList) {
                                if (annotation.value().equals(element.getName())) {
                                    field.setAccessible(true);
                                    Class type = field.getType();
                                    if(type.isAssignableFrom(Long.class)){
                                        field.set(msg, Long.valueOf(element.getData().toString()));
                                    }else if(type.isAssignableFrom(String.class)){
                                        field.set(msg, element.getText());
                                    }
                                    elementList.remove(element);
                                    break;
                                }
                            }

                        }
                    }
                }
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
