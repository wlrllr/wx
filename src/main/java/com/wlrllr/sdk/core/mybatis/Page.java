package com.wlrllr.sdk.core.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public class Page<T> extends RowBounds {
    protected int pageNo = 1;
    protected int pageSize = 20;
    protected int offset;
    protected int limit;
    protected T t;
    protected List<T> result = new ArrayList();
    protected int totalCount;
    protected int totalPages;

    public Page() {
    }

    public Page(int pageNo, int pageSize) {
        if(pageNo != 0) {
            this.pageNo = pageNo;
        }

        if(pageSize != 0) {
            this.pageSize = pageSize;
        }

    }

    public void calcOffset() {
        if(this.pageNo < 1) {
            this.pageNo = 1;
        }

        this.offset = (this.pageNo - 1) * this.pageSize;
        this.limit = this.pageSize;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getFirst() {
        return (this.pageNo - 1) * this.pageSize + 1;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = this.getTotalPages();
    }

    public int getTotalPages() {
        if(this.totalCount < 0) {
            return -1;
        } else {
            int pages = this.totalCount / this.pageSize;
            int var10000;
            if(this.totalCount % this.pageSize > 0) {
                ++pages;
                var10000 = pages;
            } else {
                var10000 = pages;
            }

            return var10000;
        }
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public T getT() {
        return this.t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
