package com.wlrllr.sdk.type;

/**
 * Created by wlrllr on 2017/11/7.
 */
public class EventType {
    public static final String SUBSCRIBE = "subscribe";
    public static final String UN_SUBSCRIBE = "unsubscribe";
    public static final String SCAN = "SCAN";
    public static final String VIDEO = "video";
    public static final String LOCATION = "LOCATION";
    public static final String CLICK = "CLICK";
    public static final String VIEW = "VIEW";
    public static final String VIEW_LIMITED = "view_limited"; //图文消息
    public static final String MEDIA = "media_id"; //图片
    public static final String MINIPROGRAM = "miniprogram"; //小程序
    public static final String LOCATION_SELECT = "location_select"; //发送位置
    public static final String PIC_WX = "pic_weixin"; //微信相册发图
    public static final String PIC_PHOTO = "pic_photo_or_album"; //拍照或者相册发图
    public static final String PIC_SYSPHOTO = "pic_sysphoto"; //系统拍照发图
    public static final String SCAN_CODE_WAIT_MSG = "scancode_waitmsg";//扫码带提示
    public static final String SCAN_CODE_PUSH = "scancode_push"; //扫码推事件
}
