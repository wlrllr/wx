package com.wlrllr.sdk.api.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by w_zhanglong on 2017/10/26.
 */
public class JSONObj extends JSONObject {

    public JSONObj() {
        super();
    }

    public JSONObj(String key,Object value) {
        super();
        this.put(key,value);
    }

    public JSONObj build(String key, Object value){
        this.put(key,value);
        return this;
    }

    public static final JSONObj toJSONObj(String text) {
        JSONObj obj = new JSONObj();
        obj.putAll(parseObject(text));
        return obj;
    }

}
