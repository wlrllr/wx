package com.wlrllr.sdk.api;

import com.wlrllr.constants.WxConstants;
import com.wlrllr.sdk.type.MenuType;
import com.wlrllr.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by wlrllr on 2017/11/8.
 */
public class MenuItem {

    private static final Logger logger = LoggerFactory.getLogger(MenuItem.class);

    //菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
    private String type;
    //菜单标题，不超过16个字节，子菜单不超过60个字节
    private String name;
    //菜单KEY值，用于消息接口推送，不超过128字节
    private String key;
    //网页链接，用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url。
    private String url;
    //调用新增永久素材接口返回的合法media_id
    private String media_id;
    //小程序的appid（仅认证公众号可配置）
    private String appid;
    //小程序的页面路径
    private String pagepath;
    private List<MenuItem> sub_button;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<MenuItem> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<MenuItem> sub_button) {
        this.sub_button = sub_button;
    }

    public MenuItem(){
    }
    public MenuItem(String name){
        this.name = name;
    }

    public MenuItem(String name,String key){
        this.key = key;
        this.name = name;
    }

    /**
     * 点击推事件用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event的结构给开发者（参考消息接口指南），
     * 并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
     * @param name
     * @param key
     * @return
     */
    public static MenuItem newButton(String name,String key){
        MenuItem item = new MenuItem(name,key);
        item.setType(MenuType.CLICK);
        return item;
    }

    /**
     * 跳转URL用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页URL，
     * 可与网页授权获取用户基本信息接口结合，获得用户基本信息。
     * @param name
     * @param url
     * @return
     */
    public static MenuItem newView(String name,String url){
        MenuItem item = new MenuItem(name);
        item.setUrl(url);
        item.setType(MenuType.VIEW);
        return item;
    }

    /**
     * 扫码推事件用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），
     * 且会将扫码的结果传给开发者，开发者可以下发消息。
     * @param name
     * @param key
     * @return
     */
    public static MenuItem newScanPush(String name,String key){
        MenuItem item = new MenuItem(name,key);
        item.setType(MenuType.SCAN_CODE_PUSH);
        return item;
    }

    /**
     * 扫码推事件且弹出“消息接收中”提示框用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，
     * 将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。
     * @param name
     * @param key
     * @return
     */
    public static MenuItem newScanWait(String name,String key){
        MenuItem item = new MenuItem(name,key);
        item.setType(MenuType.SCAN_CODE_WAIT_MSG);
        return item;
    }

    /**
     * 弹出拍照或者相册发图用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
     * @param name
     * @param key
     * @return
     */
    public static MenuItem newPic(String name,String key){
        MenuItem item = new MenuItem(name,key);
        item.setType(MenuType.PIC_PHOTO);
        return item;
    }

    /**
     * 弹出系统拍照发图用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，
     * 并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。
     * @param name
     * @param key
     * @return
     */
    public static MenuItem newSysPic(String name,String key){
        MenuItem item = new MenuItem(name,key);
        item.setType(MenuType.PIC_SYSPHOTO);
        return item;
    }

    public static MenuItem newMenu(String name,List<MenuItem> list){
        MenuItem item = new MenuItem(name);
        item.setSub_button(list);
        return item;
    }

    /**
     * 弹出微信相册发图器用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，
     * 将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。
     * @param name
     * @param key
     * @return
     */
    public static MenuItem newPicWx(String name,String key){
        MenuItem item = new MenuItem(name,key);
        item.setType(MenuType.PIC_WX);
        return item;
    }

    /**
     * 下发消息（除文本消息）用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，
     * 永久素材类型可以是图片、音频、视频、图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
     * @param name
     * @param mediaId
     * @return
     */
    public static MenuItem newMedia(String name,String mediaId){
        MenuItem item = new MenuItem(name);
        item.setMedia_id(mediaId);
        item.setType(MenuType.MEDIA);
        return item;
    }

    /**
     * 跳转图文消息URL用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，
     * 永久素材类型只支持图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
     * @param name
     * @param mediaId
     * @return
     */
    public static MenuItem newNews(String name,String mediaId){
        MenuItem item = new MenuItem(name);
        item.setMedia_id(mediaId);
        item.setType(MenuType.VIEW_LIMITED);
        return item;
    }

    /**
     * 小程序
     * @param name
     * @param url
     * @param appId
     * @param pagePath
     * @return
     */
    public static MenuItem newMiniprogram(String name,String url,String appId,String pagePath){
        MenuItem item = new MenuItem(name);
        item.setUrl(url);
        item.setAppid(appId);
        item.setPagepath(pagePath);
        item.setType(MenuType.MINIPROGRAM);
        return item;
    }

    /**
     * 弹出地理位置选择器用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，
     * 将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
     * @param name
     * @param key
     * @return
     */
    public static MenuItem newLocation(String name,String key){
        MenuItem item = new MenuItem(name,key);
        item.setType(MenuType.LOCATION);
        return item;
    }


    public Boolean validateKey() {
        if (StringUtils.isBlank(key) && (WxConstants.MENU_BUTTON_TYPE_CLICK.equals(type) || WxConstants.MENU_BUTTON_TYPE_LOCATION.equals(type)
                || WxConstants.MENU_BUTTON_TYPE_SCANCODE_WAITMGS.equals(type) || WxConstants.MENU_BUTTON_TYPE_SCANCODE_PUSH.equals(type)
                || WxConstants.MENU_BUTTON_TYPE_PIC_SYSPHOTO.equals(type) || WxConstants.MENU_BUTTON_TYPE_PIC_PHOTO.equals(type)
                || WxConstants.MENU_BUTTON_TYPE_PIC_WX.equals(type))) {
            logger.error("click等点击类型时key不能为空");
            return false;
        } else if (StringUtils.isNotBlank(key) && key.getBytes().length > 128) {
            logger.error("Key长度不能超过128字节");
            return false;
        }
        return true;
    }

    public Boolean validateUrl() {
        if (WxConstants.MENU_BUTTON_TYPE_VIEW.equals(type) || WxConstants.MENU_BUTTON_TYPE_MINIPROGRAM.equals(type)) {
            if (StringUtils.isBlank(url) || url.getBytes().length > 1024) {
                logger.error("网页链接,小程序时，url不能为空");
                return false;
            }
        }

        return true;
    }

    public Boolean validateMediaId() {
        if (WxConstants.MENU_BUTTON_TYPE_MEDIA.equals(type) || WxConstants.MENU_BUTTON_TYPE_VIEW_LIMITED.equals(type)) {
            if (StringUtils.isBlank(media_id)) {
                logger.error("图片,图文消息时，media_id不能为空");
                return false;
            }
        }
        return true;
    }

    public Boolean validate() {
        if (sub_button != null && sub_button.size() > 5) {
            logger.error("子菜单数量为1-5个");
            return false;
        }
        if (!validateKey()) {
            return false;
        }
        if (!validateUrl()) {
            return false;
        }
        if (!validateMediaId()) {
            return false;
        }
        return true;
    }
}
