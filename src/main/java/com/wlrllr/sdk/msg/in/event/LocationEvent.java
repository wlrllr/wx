package com.wlrllr.sdk.msg.in.event;

import com.wlrllr.sdk.core.XmlField;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class LocationEvent extends BaseEvent {

    @XmlField("Latitude")
    private String latitude;
    @XmlField("Longitude")
    private String longitude;
    @XmlField("Precision")
    private String precision;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}
