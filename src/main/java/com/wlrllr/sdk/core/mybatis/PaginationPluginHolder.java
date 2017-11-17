package com.wlrllr.sdk.core.mybatis;

import org.apache.ibatis.cache.CacheKey;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public class PaginationPluginHolder {
    private static ThreadLocal<PaginationCacheData> threadLocal = new ThreadLocal();

    public PaginationPluginHolder() {
    }

    public static void setData(PaginationCacheData data) {
        threadLocal.set(data);
    }

    public static PaginationCacheData getData() {
        return threadLocal.get();
    }

    public static void clearData() {
        threadLocal.remove();
    }

    public static CacheKey buildTotalKey(CacheKey key) throws Exception {
        CacheKey totalKey = key.clone();
        totalKey.update("total");
        return totalKey;
    }
}
