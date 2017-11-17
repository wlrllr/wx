package com.wlrllr.sdk.api;

import com.alibaba.fastjson.JSONObject;
import com.wlrllr.sdk.api.model.JSONObj;
import com.wlrllr.sdk.core.ThreadLocalParam;
import com.wlrllr.sdk.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * 客服接口
 * 每个公众号最多添加10个客服账号
 * Created by w_zhanglong on 2017/10/27.
 */
@Component
public class KfApi extends BaseApi {


    /**
     * 添加客服帐号
     * {"kf_account" : "test","nickname" : "客服1"}
     *
     * @param account
     * @param nickName
     * @return
     */
    public Boolean addKf(String account, String nickName) {
        return operateKf(wxProperties.getAddkf(), account, nickName);
    }

    /**
     * 修改客服帐号
     *
     * @param account
     * @param nickName
     * @return
     */
    public Boolean updateKf(String account, String nickName) {
        return operateKf(wxProperties.getUpdatekf(), account, nickName);
    }

    /**
     * 删除客服帐号
     */
    public Boolean delKf(String account, String nickName) {
        return operateKf(wxProperties.getDeletekf(), account, nickName);
    }

    private Boolean operateKf(String url, String account, String nickName) {
        JSONObject result = HttpUtils.post(fillUrlParam(url),
                new JSONObj("kf_account", account).build("nickname", nickName));
        return returnBoolean(result);
    }

    /**
     * 设置客服帐号的头像
     *
     * @param account
     * @param headerImg
     * @return
     */
    public Boolean updateHeaderImg(String account, InputStream headerImg) {
        JSONObject result = HttpUtils.uploadFile(fillUrlParam(wxProperties.getUploadkfimg(), account), new JSONObj("headerImg", headerImg));
        return returnBoolean(result);
    }

    /**
     * 获取所有客服账号
     *
     * @return {"kf_list": [
     * {
     * "kf_account": "test1@test",
     * "kf_nick": "ntest1",
     * "kf_id": "1001"
     * "kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0"
     * },
     * ]
     * }
     */
    public JSONObj getKfList() {
        return HttpUtils.get(fillUrlParam(wxProperties.getGetkflist()));
    }

    /**
     * 发送消息
     *
     * @param openId    发送对象
     * @param type      消息类型
     * @param kfAccount 客服账号
     * @param param     消息体
     * @return 具体返回什么通过测试才知道。。
     */
    public JSONObj sendMsg(String openId, String type, String kfAccount, JSONObj param) {
        JSONObj data = new JSONObj("touser", openId).build("msgtype", type).build(type, param);
        if (StringUtils.isNotBlank(kfAccount)) {
            data.build("customservice", new JSONObj("kf_account", kfAccount));
        }
        return HttpUtils.post(fillUrlParam(wxProperties.getSendkfmsg()), data);
    }

    /**
     * 客服输入状态
     * 此接口需要客服消息接口权限。
     * 1. 如果不满足发送客服消息的触发条件，则无法下发输入状态。
     * 2. 下发输入状态，需要客服之前30秒内跟用户有过消息交互。
     * 3. 在输入状态中（持续15s），不可重复下发输入态。
     * 4. 在输入状态中，如果向用户下发消息，会同时取消输入状态。
     *
     * @param touser  普通用户（openid）
     * @param command "Typing"：对用户下发“正在输入"状态" CancelTyping"：取消对用户的”正在输入"状态
     * @return
     */
    public Boolean typing(String touser, String command) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getTyping())
                , new JSONObj("touser", touser).build("msgtype", command));
        return returnBoolean(result);
    }


    /**
     * 创建会话
     * @param account 公众号名称@test
     * @param openId
     * @return
     */
    public Boolean createSeesion(String account,String openId){
        JSONObj param = new JSONObj("kf_account",account).build("openid",openId);
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getCreateSession()),param);
        return returnBoolean(result);
    }


    public static JSONObj buildTextMsg(String content) {
        return new JSONObj("content", content);
    }

    public static JSONObj buildImageOrVoiceMsg(String mediaId) {
        return new JSONObj("media_id", mediaId);
    }

    public static JSONObj buildVideoMsg(String mediaId, String thumbMediaId, String title, String desc) {
        return new JSONObj("media_id", mediaId).build("thumb_media_id", thumbMediaId).build("title", title).build("description", desc);
    }

    /**
     * @param musicUrl     音乐链接
     * @param hqmusicurl   高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     * @param title
     * @param desc
     * @return
     */
    public static JSONObj buildMusicMsg(String musicUrl, String hqmusicurl, String thumbMediaId, String title, String desc) {
        return new JSONObj("musicurl", musicUrl).build("thumb_media_id", thumbMediaId)
                .build("hqmusicurl", hqmusicurl).build("title", title).build("description", desc);
    }

    public static JSONObj buildNewsMsg(List<JSONObj> articles) {
        return new JSONObj("articles", articles);
    }

    /**
     * 只适用于客服发送图文消息，图文消息体的组装
     *
     * @param title
     * @param description
     * @param url
     * @param picurl
     * @return
     */
    public static JSONObj buildArticle(String title, String description, String url, String picurl) {
        return new JSONObj("title", title).build("description", description).build("url", url).build("picurl", picurl);
    }
}
