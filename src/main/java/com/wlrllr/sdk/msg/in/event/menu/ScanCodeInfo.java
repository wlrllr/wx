package com.wlrllr.sdk.msg.in.event.menu;

import com.wlrllr.sdk.core.XmlField;

/**
 * Created by wlrllr on 2017/11/9.
 */
public class ScanCodeInfo {

    @XmlField("ScanResult")
    private String scanResult;

    public String getScanResult() {
        return scanResult;
    }

    public void setScanResult(String scanResult) {
        this.scanResult = scanResult;
    }
}
