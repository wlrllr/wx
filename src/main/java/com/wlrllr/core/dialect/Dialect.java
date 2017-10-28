package com.wlrllr.core.dialect;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public abstract class Dialect {
    public Dialect() {
    }

    public abstract String getLimitString(String var1, int var2, int var3);

    public abstract String getCountString(String var1);
}
