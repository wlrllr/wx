package com.wlrllr.sdk.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wlrllr.sdk.api.model.BaseModel;
import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.core.Covert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wlrllr on 2017/10/24.
 */
public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static Object toBean(JSONObject object, Class clazz) {
        if (object != null && !object.isEmpty()) {
            try {
                Object result = clazz.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    String key = field.getName();
                    Object value = object.get(key);
                    Alias annotation = field.getAnnotation(Alias.class);
                    if (annotation != null && StringUtils.isNotBlank(annotation.value())) {
                        key = annotation.value();
                        value = object.get(key);
                        Covert covert = field.getAnnotation(Covert.class);
                        if (covert != null && covert.target() != null && covert.source() != null) {
                            Class claz = covert.target();
                            Class source = covert.source();
                            if(claz.isAssignableFrom(Date.class)){ //对象属性为Date时
                                if(source.isAssignableFrom(Long.class)){ //返回结果为Long类型
                                     value = new Date((Long)value);
                                }
                            }else if(claz.isAssignableFrom(List.class)){
                                if(source.isAssignableFrom(JSONArray.class)){ //返回结果为JSONArray类型
                                }
                            }
                        }
                    }
                    field.set(result,  value);
                }
                return result;
            } catch (Exception e) {
                logger.error(">>>>>>>>转换Bean异常<<<<<<<<<<", e);
            }
        }
        return null;
    }

    public static List toList(JSONArray array) {
        List list = new ArrayList();
       if(array != null && array.size()>0){
           for(int i=0;i<array.size();i++){

           }
       }
        return list;
    }

    public static JSONArray toJson(List<? extends BaseModel> list) {
        JSONArray result = new JSONArray();
        if (list != null && list.size() > 0) {
            for (BaseModel model : list) {
                result.add(toJson(model));
            }
        }
        return result;
    }

    public static JSONObject toJson(BaseModel obj) {
        if (obj != null) {
            try {
                JSONObject result = new JSONObject();
                for (Field field : obj.getClass().getDeclaredFields()) {
                    String key = field.getName();
                    Alias annotation = field.getAnnotation(Alias.class);
                    if (annotation != null && StringUtils.isNotBlank(annotation.value())) {
                        key = annotation.value();
                    }
                    field.setAccessible(true);
                    result.put(key, field.get(obj));
                }
                return result;
            } catch (Exception e) {
                logger.error(">>>>>>>>Bean转换成JSON异常<<<<<<<<<<", e);
            }
        }
        return null;
    }


}
