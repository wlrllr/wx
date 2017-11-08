package com.wlrllr.sdk.model;

import com.wlrllr.constants.WxConstants;
import com.wlrllr.util.StringUtils;

import java.util.List;

/**
 * Created by wlrllr on 2017/11/8.
 */
public class MenuItem {

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

    public Boolean validateKey() {
        if (StringUtils.isBlank(key) && (WxConstants.MENU_BUTTON_TYPE_CLICK.equals(type) || WxConstants.MENU_BUTTON_TYPE_LOCATION.equals(type)
                || WxConstants.MENU_BUTTON_TYPE_SCANCODE_WAITMGS.equals(type) || WxConstants.MENU_BUTTON_TYPE_SCANCODE_PUSH.equals(type)
                || WxConstants.MENU_BUTTON_TYPE_PIC_SYSPHOTO.equals(type) || WxConstants.MENU_BUTTON_TYPE_PIC_PHOTO.equals(type)
                || WxConstants.MENU_BUTTON_TYPE_PIC_WX.equals(type))) {
           // logger.error("click等点击类型时key不能为空");
            return false;
        } else if (StringUtils.isNotBlank(key) && key.getBytes().length > 128) {
           // logger.error("Key长度不能超过128字节");
            return false;
        }
        return true;
    }

    public Boolean validateUrl() {
        if(WxConstants.MENU_BUTTON_TYPE_VIEW.equals(type) || WxConstants.MENU_BUTTON_TYPE_MINIPROGRAM.equals(type)){
            if (StringUtils.isBlank(url) ||  url.getBytes().length > 1024) {
                //logger.error("网页链接,小程序时，url不能为空");
                return false;
            }
        }

        return true;
    }

    public Boolean validateMediaId() {
        if(WxConstants.MENU_BUTTON_TYPE_MEDIA.equals(type) || WxConstants.MENU_BUTTON_TYPE_VIEW_LIMITED.equals(type)){
            if(StringUtils.isBlank(media_id)){
                // logger.error("图片,图文消息时，media_id不能为空");
                return false;
            }
        }
        return true;
    }

    public Boolean validate() {
        if (sub_button != null && sub_button.size() > 5) {
            //logger.error("子菜单数量为1-5个");
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
