package com.wlrllr.sdk.core.dialect;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public class MySql5Dialect extends Dialect {

    public String getLimitString(String sql, int offset, int limit) {
        return MySql5PageHepler.getLimitString(sql, offset, limit);
    }

    public String getCountString(String sql) {
        return MySql5PageHepler.getCountString(sql);
    }
}
