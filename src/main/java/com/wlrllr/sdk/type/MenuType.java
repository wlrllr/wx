package com.wlrllr.sdk.type;

/**
 * Created by wlrllr on 2017/11/7.
 */
public enum MenuType {
    CLICK("click", "点击按钮事件"),
    SCANCODE_WAITMGS("scancode_waitmsg", "扫码带提示"),
    SCANCODE_PUSH("scancode_push", "扫码推事件"),
    PIC_SYSPHOTO("pic_sysphoto", "系统拍照发图"),
    PIC_PHOTO("pic_photo_or_album", "拍照或者相册发图"),
    PIC_WX("pic_weixin", "微信相册发图"),
    LOCATION("location_select", "发送位置"),
    MINIPROGRAM("miniprogram", "小程序"),
    MEDIA("media_id", "图片"),
    VIEW_LIMITED("view_limited", "图文消息"),
    VIEW("view", "链接");
    private String code;
    private String comment;

    private MenuType(String code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public String getCode() {
        return code;
    }

    public String getComment() {
        return comment;
    }
}
