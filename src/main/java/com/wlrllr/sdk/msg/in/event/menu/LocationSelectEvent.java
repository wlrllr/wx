package com.wlrllr.sdk.msg.in.event.menu;

import com.wlrllr.sdk.core.Alias;
import com.wlrllr.sdk.msg.in.event.BaseEvent;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class LocationSelectEvent extends BaseEvent {

    @Alias("EventKey")
    private String eventKey;
    @Alias("SendLocationInfo")
    private SendLocationInfo locationInfo;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public SendLocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(SendLocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    class SendLocationInfo {
        @Alias("Location_X")
        private String locationX;
        @Alias("Location_Y")
        private String locationY;
        @Alias("Scale")
        private String scale;
        @Alias("Label")
        private String label;
        @Alias("Poiname")
        private String poiname;

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

        public String getPoiname() {
            return poiname;
        }

        public void setPoiname(String poiname) {
            this.poiname = poiname;
        }
    }
}
