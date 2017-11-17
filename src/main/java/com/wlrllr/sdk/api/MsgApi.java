package com.wlrllr.sdk.api;

import com.wlrllr.sdk.api.model.JSONObj;
import com.wlrllr.sdk.util.HttpUtils;
import com.wlrllr.sdk.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息推送，群发消息，模板消息
 */
@Component
public class MsgApi extends BaseApi {

    public static final String PARAM_IS_TO_ALL = "is_to_all";
    public static final String PARAM_TAG_ID = "tag_id";
    public static final String PARAM_MEDIA_ID = "media_id";
    public static final String PARAM_SEND_IGNORE_REPRINT = "send_ignore_reprint";
    public static final String PARAM_CONTENT = "content";
    public static final String PARAM_CARD_ID = "card_id";
    public static final String PARAM_FILTER = "filter";
    public static final String PARAM_MSG_TYPE = "msgtype";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_DESCRIPTION = "description";
    public static final String PARAM_TO_USER= "touser";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_MP_NEWS = "mpnews";
    public static final String TYPE_VIDEO= "mpvideo";
    public static final String TYPE_WX_CARD = "wxcard";


    /**
     * 根据标签群发消息
     * @param tagId
     * @param data
     * @param type
     * @return
     */
    public String sendByTag(String tagId, String data, String type) {
        JSONObj param = new JSONObj();
        param.put(PARAM_FILTER, getFilter(tagId));
        param.put(type, getBody(data, type));
        param.put(PARAM_MSG_TYPE, type);
        if (TYPE_MP_NEWS.equals(type)) {
            param.put(PARAM_SEND_IGNORE_REPRINT, 0);
        }
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getSendByTag()), param);
        return returnString(result, "media_id");
    }


    public String sendByOpenId(List<String> openIds, String data, String type,String title,String description) {
        JSONObj param = new JSONObj();
        param.put(PARAM_TO_USER, openIds);
        param.put(type, getBody(data, type,title,description));
        param.put(PARAM_MSG_TYPE, type);
        if (TYPE_MP_NEWS.equals(type)) {
            param.put(PARAM_SEND_IGNORE_REPRINT, 0);
        }
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getSendByOpenId()), param);
        return returnString(result, "media_id");
    }

    public String sendByOpenId(List<String> openIds, String data, String type) {
        return sendByOpenId(openIds,data,type,"","");
    }

    private JSONObj getFilter(String tagId) {
        JSONObj filter = new JSONObj();
        if (StringUtils.isBlank(tagId)) {
            filter.put(PARAM_IS_TO_ALL, true);
        } else {
            filter.put(PARAM_IS_TO_ALL, false);
            filter.put(PARAM_TAG_ID, tagId);
        }
        return filter;
    }

    private JSONObj getBody(String data, String type) {
        return getBody(data,type,"","");
    }

    private JSONObj getBody(String data, String type,String title,String description) {
        JSONObj body = new JSONObj();
        if (type.equals(TYPE_TEXT)) {
            body.put(PARAM_CONTENT, data);
        } else if (type.equals(TYPE_WX_CARD)) {
            body.put(PARAM_CARD_ID, data);
        } else if (TYPE_VIDEO.equals(type)) {
            JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getUploadVideo()),
                    new JSONObj(PARAM_MEDIA_ID, data).build(PARAM_TITLE, title).build(PARAM_DESCRIPTION, description));
            body.put(PARAM_MEDIA_ID, returnString(result, "media_id"));
            body.put(PARAM_TITLE, title);
            body.put(PARAM_DESCRIPTION, description);
        } else {
            body.put(PARAM_MEDIA_ID, data);
        }
        return body;
    }

}
