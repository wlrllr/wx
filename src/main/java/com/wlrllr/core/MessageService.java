package com.wlrllr.core;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.config.WxProperties;
import com.wlrllr.constants.WxConstants;
import com.wlrllr.core.bean.ResponseMessage;
import com.wlrllr.model.WxUser;
import com.wlrllr.service.WxUserService;
import com.wlrllr.util.JsonUtils;
import com.wlrllr.util.XmlUtils;
import com.wlrllr.wxapi.UserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by w_zhanglong on 2017/10/19.
 */
@Service
public class MessageService {

    @Autowired
    private WxUserService wxUserService;
    @Autowired
    private UserManager userManager;
    @Autowired
    private WxProperties wxProperties;

    public String getResponseMessge(JSONObject param){
        switch (param.getString("MsgType")) {
            case "text":
                return textReply(param);
            case "image":
                return textReply(param);
            case "voice":
                return textReply(param);
            case "video":
                break;
            case "shortvideo":
                break;
            case "location":
                break;
            case "link":
                break;
            case "event": //事件处理
                return dealEvent(param);
        }
        return null;
    }

    private String dealEvent(JSONObject param) {
        String content;
        String eventKey;
        String openid;
        switch (param.getString("Event")) {
            case WxConstants.EVENT_TYPE_SUBSCRIBE:
                content = "感谢关注！";
                eventKey = param.getString("EventKey");
                if (StringUtils.isNotBlank(eventKey)) {
                    content += eventKey.substring(eventKey.indexOf("_"));
                }
                openid = param.getString("FromUserName");
                //保存用户
                WxUser wxUser = (WxUser)JsonUtils.toBean(userManager.getUserInfo(openid), WxUser.class);
                if(wxUser == null){
                    wxUser = new WxUser();
                    wxUser.setOpenId(openid);
                    wxUser.setRemark("没有获取到用户信息");
                    wxUser.setSubscribeTime(new Date());
                }
                wxUser.setAppId(wxProperties.getAppId());
                wxUserService.addUser(wxUser);
                //获取自动回复消息
                return textReply(param, content);
            case WxConstants.EVENT_TYPE_UNSUBSCRIBE:
                content = "取消关注！";
                eventKey = param.getString("EventKey");
                if (StringUtils.isNotBlank(eventKey)) {
                    content += eventKey.substring(eventKey.indexOf("_"));
                }
                openid = param.getString("FromUserName");
                wxUserService.forbid(openid,wxProperties.getAppId());
                return textReply(param, content);
            case WxConstants.EVENT_TYPE_SCAN:
                content = "你扫描了二维码 ";
                eventKey = param.getString("EventKey");
                if (StringUtils.isNotBlank(eventKey)) {
                    content += "scene_id 为"+eventKey;
                }
                return textReply(param, content);
            case WxConstants.EVENT_TYPE_LOCATION:
                content = "上传定位信息 ";
                content += "Latitude:"+param.getString("Latitude")+"Longitude:"+param.getString("Longitude");
                return textReply(param, content);
            case WxConstants.EVENT_TYPE_CLICK:
                content = "点击自定义菜单，点击事件 ";
                content += "菜单code为"+param.getString("EventKey");
                return textReply(param, content);
            case WxConstants.EVENT_TYPE_VIEW:
                content = "点击自定义菜单，URL ";
                content += "跳转链接为"+param.getString("EventKey");
                return textReply(param, content);
        }
        return null;
    }

    /**
     * 自动回复匹配回复内容
     *
     * @param data
     * @return
     */
    private String textReply(JSONObject data) {
        String requestContent = data.getString("Content");
        //根据请求内容匹配回复内容 查找ResponseMessage
        //根据ResponseMessage生成对应的Message，先默认生成text
        ResponseMessage resp = new ResponseMessage();
        resp.setContent("aaaaaaaaaaaaa");
        resp.setMessageType(WxConstants.MESSAGE_TYPE_TEXT);
        return XmlUtils.messageToXML(resp.create(data.getString("ToUserName"),data.getString("FromUserName")));
    }

    private String textReply(JSONObject data,String content) {
        ResponseMessage resp = new ResponseMessage();
        resp.setContent(content);
        resp.setMessageType(WxConstants.MESSAGE_TYPE_TEXT);
        return XmlUtils.messageToXML(resp.create(data.getString("ToUserName"),data.getString("FromUserName")));
    }

}
