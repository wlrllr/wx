package com.wlrllr.sdk.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wlrllr.config.WxProperties;
import com.wlrllr.constants.WxConstants;
import com.wlrllr.core.bean.JSONObj;
import com.wlrllr.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 素材接口
 * Created by w_zhanglong on 2017/10/27.
 */
@Component
public class MediaApi extends BaseApi {

    @Autowired
    private WxProperties wxProperties;

    /**
     * 新增临时素材 有效期3天
     *
     * @param type  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param media
     * @return {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
     */
    public String addMedia(String type, InputStream media) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("media", media);
        JSONObject result = HttpUtils.uploadFile(urlReplaceAccessToken(wxProperties.getAddMedia()), map);
        return returnString(result, "media_id");
    }

    /**
     * 获取临时素材
     *
     * @param mediaId 素材ID
     * @return 视频{"video_url":DOWN_URL},其他类型的Media未知？
     */
    public String getMedia(String mediaId) {
        JSONObject result = HttpUtils.get(urlReplaceAccessToken(wxProperties.getGetMedia(), mediaId));
        return returnString(result, "video_url");
    }

    /**
     * 新增永久图文素材
     *
     * @param title     标题
     * @param mediaId   图文消息的封面图片素材id（必须是永久mediaID）
     * @param author    作者
     * @param digest    图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
     * @param coverPic  是否显示封面，0为false，即不显示，1为true，即显示
     * @param content   图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源"上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
     * @param sourceUrl 图文消息的原文地址，即点击“阅读原文”后的URL
     * @return {"media_id":MEDIA_ID }
     */
    public String addNews(String title, String mediaId, String author, String digest, String coverPic, String content, String sourceUrl) {
        JSONArray articles = new JSONArray(1);
        articles.add(new JSONObj("title", title).build("thumb_media_id", mediaId).build("author", author).build("digest", digest)
                .build("show_cover_pic", coverPic).build("content", content).build("content_source_url", sourceUrl));
        JSONObject result = HttpUtils.post(urlReplaceAccessToken(wxProperties.getAddNews()), new JSONObj("articles", articles));
        return returnString(result, "media_id");
    }

    /**
     * 新增永久图文素材
     *
     * @param articles 多个图文集合
     * @return {"media_id":MEDIA_ID }
     */
    public String addNews(JSONArray articles) {
        JSONObject result = HttpUtils.post(urlReplaceAccessToken(wxProperties.getAddNews()), new JSONObj("articles", articles));
        return returnString(result, "media_id");
    }

    /**
     * 构建图文素材
     *
     * @param title     标题
     * @param mediaId   图文消息的封面图片素材id（必须是永久mediaID）
     * @param author    作者
     * @param digest    图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
     * @param coverPic  是否显示封面，0为false，即不显示，1为true，即显示
     * @param content   图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源"上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
     * @param sourceUrl 图文消息的原文地址，即点击“阅读原文”后的URL
     * @return 单个图文
     */
    public static JSONObj buildArticle(String title, String mediaId, String author, String digest, String coverPic, String content, String sourceUrl) {
        return new JSONObj("title", title).build("thumb_media_id", mediaId).build("author", author).build("digest", digest)
                .build("show_cover_pic", coverPic).build("content", content).build("content_source_url", sourceUrl);
    }

    /**
     * 上传图文消息内的图片获取URL
     * 本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。
     *
     * @param img form-data中媒体文件标识，有filename、filelength、content-type等信息
     * @return
     */
    public String uploadImg(InputStream img) {
        Map<String, Object> map = new HashMap<>();
        map.put("media", img);
        JSONObject result = HttpUtils.uploadFile(urlReplaceAccessToken(wxProperties.getUploadImg()), map);
        return returnString(result, "url");
    }

    /**
     * 新增其他类型永久素材
     *
     * @param type         媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param media        orm-data中媒体文件标识，有filename、filelength、content-type等信息
     *                     在上传视频素材时需要下面两个参数
     * @param title        视频素材的标题
     * @param introduction 视频素材的描述
     * @return {"media_id":MEDIA_ID,"url":URL}
     */
    public JSONObject addMaterial(String type, InputStream media, String title, String introduction) {
        JSONObj data = new JSONObj("media", media);
        if (type.equals(WxConstants.MATERIAL_TYPE_VIDEO)) {
            data.put("description", new JSONObj("title", title).build("introduction", introduction));
        }
        return HttpUtils.uploadFile(urlReplaceAccessToken(wxProperties.getAddMaterial(), type), data);
    }

    /**
     * 获取永久素材
     *
     * @param mediaId 要获取的素材的media_id
     * @return 图文消息{
     * "news_item":[
     * {   "title":TITLE,
     * "thumb_media_id"::THUMB_MEDIA_ID,
     * "show_cover_pic":SHOW_COVER_PIC(0/1),
     * "author":AUTHOR,
     * "digest":DIGEST,
     * "content":CONTENT,
     * "url":URL,
     * "content_source_url":CONTENT_SOURCE_URL
     * },
     * //多图文消息有多篇文章
     * ]
     * }
     * 视频消息素材：{"title":TITLE,"description":DESCRIPTION,"down_url":DOWN_URL,}
     * 其他类型的素材消息:{"title":TITLE,"thumb_media_id":THUMB_MEDIA_ID,"show_cover_pic":SHOW_COVER_PIC,
     * "author":AUTHOR,"digest":DIGEST,"content":CONTENT,"url":URL,
     * "content_source_url":CONTENT_SOURCE_URL}
     */
    public JSONObject getMaterial(String mediaId) {
        JSONObject result = HttpUtils.post(urlReplaceAccessToken(wxProperties.getGetMaterial()), new JSONObj("media_id", mediaId));
        if (result == null || org.springframework.util.StringUtils.isEmpty(result.getString("errcode"))) {
            return null;
        }
        return result;
    }

    /**
     * 删除永久素材
     *
     * @param mediaId 要获取的素材的media_id
     * @return
     */
    public boolean delMaterial(String mediaId) {
        JSONObject result = HttpUtils.post(urlReplaceAccessToken(wxProperties.getDelMaterial()), new JSONObj("media_id", mediaId));
        return returnBoolean(result);
    }

    /**
     * 修改永久图文素材
     *
     * @param mediaId 要修改的图文消息的id
     * @param index   要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param article 单图文对象，通过buildArticle()获取
     * @return
     */
    public boolean updateNews(String mediaId, int index, JSONObj article) {
        JSONObject result = HttpUtils.post(urlReplaceAccessToken(wxProperties.getUpdateNews()),
                new JSONObj("media_id", mediaId).build("index", index).build("articles", article));
        return returnBoolean(result);
    }

    /**
     * 获取素材总数
     *
     * @return {"voice_count":COUNT,"video_count":COUNT,"image_count":COUNT,"news_count":COUNT}
     */
    public JSONObject getMaterialCount() {
        return HttpUtils.get(urlReplaceAccessToken(wxProperties.getGetMaterialCount()));
    }

    /**
     * 获取素材列表
     *
     * @param type   素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     * @return 其他类型（图片、语音、视频）的返回如下:
     * { "total_count": TOTAL_COUNT, "item_count": ITEM_COUNT,
     * "item": [
     * { "media_id": MEDIA_ID, "name": NAME, "update_time": UPDATE_TIME, "url":URL },
     * //可能会有多个素材
     * ]
     * }
     * 图文的返回只有item内容不一样:
     * "item": [{"media_id": MEDIA_ID,
     * "content": {
     * "news_item": [{"title": TITLE,"thumb_media_id": THUMB_MEDIA_ID,"show_cover_pic": SHOW_COVER_PIC(0 / 1),
     * "author": AUTHOR,"digest": DIGEST,"content": CONTENT,"url": URL,"content_source_url": CONTETN_SOURCE_URL},
     * //多图文消息会在此处有多篇文章
     * ]
     * },
     * "update_time": UPDATE_TIME
     * }]
     */
    public JSONObject getMaterialList(String type, String offset, String count) {
        return HttpUtils.post(urlReplaceAccessToken(wxProperties.getGetMaterialList()),
                new JSONObj("type", type).build("offset", offset).build("count", count));
    }
}
