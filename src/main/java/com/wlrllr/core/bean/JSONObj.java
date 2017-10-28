package com.wlrllr.core.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

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

}