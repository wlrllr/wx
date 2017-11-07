package com.wlrllr.sdk.msg.in;

import com.wlrllr.sdk.core.XmlField;
import com.wlrllr.sdk.msg.Msg;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class LocationMsg extends BaseMsg {

    @XmlField("Location_X")
    private String locationX;
    @XmlField("Location_Y")
    private String locationY;
    @XmlField("Scale")
    private String scale;
    @XmlField("Label")
    private String label;

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
