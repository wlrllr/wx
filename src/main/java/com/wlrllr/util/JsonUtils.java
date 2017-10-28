package com.wlrllr.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public class JsonUtils{

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static Object toBean(JSONObject object,Class clazz){
        if(object != null && !object.isEmpty()){
            try {
                Object result = clazz.newInstance();
                Field[] fields = clazz.getFields();
                for(Field f : fields){
                    f.set(result, object.get(f.getName()));
                }
                return result;
            } catch (Exception e) {
                logger.error(">>>>>>>>转换Bean异常<<<<<<<<<<",e);
            }
        }
        return null;
    }
}
