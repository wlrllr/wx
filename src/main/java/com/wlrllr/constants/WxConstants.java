package com.wlrllr.constants;

/**
 * 微信相关常量
 *
 * @author wlrllr
 */
public class WxConstants {

    public static final String MATERIAL_TYPE_IMAGE = "image";
    public static final String MATERIAL_TYPE_VOICE = "voice";
    public static final String MATERIAL_TYPE_VIDEO = "video";
    public static final String MATERIAL_TYPE_THUMB = "thumb";

    // 消息类型：文本
    public static final String MESSAGE_TYPE_TEXT = "text";
    // 消息类型：图片
    public static final String MESSAGE_TYPE_IMAGE = "image";
    // 消息类型：语音
    public static final String MESSAGE_TYPE_VOICE = "voice";
    // 消息类型：视频
    public static final String MESSAGE_TYPE_VIDEO = "video";
    // 消息类型：地理位置
    public static final String MESSAGE_TYPE_LOCATION = "location";
    // 消息类型：链接
    public static final String MESSAGE_TYPE_LINK = "link";
    // 消息类型：事件推送
    public static final String MESSAGE_TYPE_EVENT = "event";
    // 消息类型：音乐
    public static final String MESSAGE_TYPE_MUSIC = "music";
    // 消息类型：图文
    public static final String MESSAGE_TYPE_NEWS = "news";

    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "SCAN";
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    // 事件类型：CLICK(自定义菜单)
    public static final String EVENT_TYPE_CLICK = "CLICK";
    // 事件类型：VIEW(自定义菜单)
    public static final String EVENT_TYPE_VIEW = "VIEW";

    //菜单按钮点击事件类型
    //点击
    public static final String MENU_BUTTON_TYPE_CLICK="click";
    //扫码带提示
    public static final String MENU_BUTTON_TYPE_SCANCODE_WAITMGS="scancode_waitmsg";
    //扫码推事件
    public static final String MENU_BUTTON_TYPE_SCANCODE_PUSH="scancode_push";
    //系统拍照发图
    public static final String MENU_BUTTON_TYPE_PIC_SYSPHOTO="pic_sysphoto";
    //拍照或者相册发图
    public static final String MENU_BUTTON_TYPE_PIC_PHOTO="pic_photo_or_album";
    //微信相册发图
    public static final String MENU_BUTTON_TYPE_PIC_WX="pic_weixin";
    //发送位置
    public static final String MENU_BUTTON_TYPE_LOCATION="location_select";
    //连接
    public static final String MENU_BUTTON_TYPE_VIEW="view";
    //小程序
    public static final String MENU_BUTTON_TYPE_MINIPROGRAM="miniprogram";
    //图片
    public static final String MENU_BUTTON_TYPE_MEDIA="media_id";
    //图文消息
    public static final String MENU_BUTTON_TYPE_VIEW_LIMITED="view_limited";
}
