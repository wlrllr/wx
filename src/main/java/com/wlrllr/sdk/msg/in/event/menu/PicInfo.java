package com.wlrllr.sdk.msg.in.event.menu;

import com.wlrllr.sdk.core.Alias;

import java.util.List;

/**
 * Created by wlrllr on 2017/11/9.
 */
public class PicInfo {
    @Alias("Count")
    private int count;
    @Alias("PicList")
    private List<Pic> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Pic> getList() {
        return list;
    }

    public void setList(List<Pic> list) {
        this.list = list;
    }

    @Alias("item")
    class Pic {
        @Alias("PicMd5Sum")
        private String picMd5Sum;

        public String getPicMd5Sum() {
            return picMd5Sum;
        }

        public void setPicMd5Sum(String picMd5Sum) {
            this.picMd5Sum = picMd5Sum;
        }
    }
}
