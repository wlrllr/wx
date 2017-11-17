package com.wlrllr.sdk.api;

import com.alibaba.fastjson.JSONArray;
import com.wlrllr.sdk.core.config.WxProperties;
import com.wlrllr.constants.WxConstants;
import com.wlrllr.sdk.api.model.JSONObj;
import com.wlrllr.sdk.api.model.UploadNews;
import com.wlrllr.sdk.util.HttpUtils;
import com.wlrllr.sdk.util.InputStreamBody;
import com.wlrllr.sdk.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
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
    public String addMedia(String type, InputStreamBody media) {
        Map<String, Object> map = new HashMap<>();
        map.put("media", media);
        JSONObj result = HttpUtils.uploadFile(fillUrlParam(wxProperties.getAddMedia(),type), map);
        return returnString(result, "media_id");
    }

    /**
     * 获取临时素材
     *
     * @param mediaId 素材ID
     * @return 视频{"video_url":DOWN_URL},其他类型的Media未知？
     */
    public String getMedia(String mediaId) {
        JSONObj result = HttpUtils.get(fillUrlParam(wxProperties.getGetMedia(), mediaId));
        return returnString(result, "video_url");
    }

    /**
     * 新增永久图文素材
     *
     * @param news   图文
     * @return {"media_id":MEDIA_ID }
     */
    public String addNews(UploadNews news) {
        JSONArray articles = new JSONArray(1);
        articles.add(JsonUtils.toJson(news));
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getAddNews()), new JSONObj("articles", articles));
        return returnString(result, "media_id");
    }

    /**
     * 新增永久图文素材
     *
     * @param news 多个图文集合
     * @return {"media_id":MEDIA_ID }
     */
    public String addNews(List<UploadNews> news) {
        JSONArray articles = JsonUtils.toJson(news);
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getAddNews()), new JSONObj("articles", articles));
        return returnString(result, "media_id");
    }

    /**
     * 上传图文消息内的图片获取URL
     * 本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。
     *
     * @param img form-data中媒体文件标识，有filename、filelength、content-type等信息
     * @return
     */
    public String uploadImg(InputStreamBody img) {
        JSONObj result = HttpUtils.uploadFile(fillUrlParam(wxProperties.getUploadImg()), new JSONObj("media",img));
        return returnString(result, "url");
    }

    /**
     * 上传图文消息素材，用于群发
     * @param list
     * @return
     */
    public String uploadNews(List<UploadNews> list) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getUploadNews()), new JSONObj("articles", JsonUtils.toJson(list)));
        return returnString(result, "media_id");
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
    public JSONObj addMaterial(String type, InputStreamBody media, String title, String introduction) {
        JSONObj data = new JSONObj("media", media);
        if (type.equals(WxConstants.MATERIAL_TYPE_VIDEO)) {
            data.put("description", new JSONObj("title", title).build("introduction", introduction));
        }
        return HttpUtils.uploadFile(fillUrlParam(wxProperties.getAddMaterial(), type), data);
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
    public JSONObj getMaterial(String mediaId) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getGetMaterial()), new JSONObj("media_id", mediaId));
        return returnJson(result);
    }

    /**
     * 删除永久素材
     *
     * @param mediaId 要获取的素材的media_id
     * @return
     */
    public boolean delMaterial(String mediaId) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getDelMaterial()), new JSONObj("media_id", mediaId));
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
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getUpdateNews()),
                new JSONObj("media_id", mediaId).build("index", index).build("articles", article));
        return returnBoolean(result);
    }

    /**
     * 获取素材总数
     *
     * @return {"voice_count":COUNT,"video_count":COUNT,"image_count":COUNT,"news_count":COUNT}
     */
    public JSONObj getMaterialCount() {
        return HttpUtils.get(fillUrlParam(wxProperties.getGetMaterialCount()));
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
    public JSONObj getMaterialList(String type, String offset, String count) {
        return HttpUtils.post(fillUrlParam(wxProperties.getGetMaterialList()),
                new JSONObj("type", type).build("offset", offset).build("count", count));
    }
}
