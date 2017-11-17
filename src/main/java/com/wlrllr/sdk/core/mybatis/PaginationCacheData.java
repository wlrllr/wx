package com.wlrllr.sdk.core.mybatis;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cache.TransactionalCacheManager;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public class PaginationCacheData {
    private boolean isPageDB;
    private TransactionalCacheManager tcm;
    private Cache cache;
    private CacheKey key;

    public PaginationCacheData() {
    }

    public boolean isPageDB() {
        return this.isPageDB;
    }

    public void setPageDB(boolean isPageDB) {
        this.isPageDB = isPageDB;
    }

    public TransactionalCacheManager getTcm() {
        return this.tcm;
    }

    public void setTcm(TransactionalCacheManager tcm) {
        this.tcm = tcm;
    }

    public Cache getCache() {
        return this.cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public CacheKey getKey() {
        return this.key;
    }

    public void setKey(CacheKey key) {
        this.key = key;
    }
}
