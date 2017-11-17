package com.wlrllr.sdk.api;

import com.wlrllr.sdk.core.config.WxProperties;
import com.wlrllr.sdk.api.model.JSONObj;
import com.wlrllr.sdk.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户相关接口
 * Created by w_zhanglong on 2017/10/24.
 */
@Component
public class UserApi extends BaseApi {

    @Autowired
    private WxProperties wxProperties;

    public JSONObj getUserInfo(String openId) {
        JSONObj result = HttpUtils.get(fillUrlParam(wxProperties.getUserInfo(), openId));
        if (result != null) {
            return result;
        }
        return result;
    }

    /**
     * 创建标签
     * {"tag" : {"name" : "广东"}}
     *
     * @param tagName
     * @return {"tag":{"id":134,//标签id "name":"广东"}}
     */
    public Integer addTag(String tagName) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getAddTag()), new JSONObj("tag", new JSONObj("name", tagName)));
        if (result != null) {
            return result.getInteger("id");
        }
        return null;
    }

    /**
     * 获取已创建的标签
     *
     * @return {"tags":[{"id":1,"name":"每天一罐可乐星人","count":0 //此标签下粉丝数}]}
     */
    public JSONObj getTag() {
        return HttpUtils.get(fillUrlParam(wxProperties.getGetTag()));
    }

    /**
     * 编辑标签
     * {"tag" : {"id" : 134,"name" : "广东人"}}
     *
     * @param id
     * @param name
     * @return
     */
    public Boolean updateTag(int id, String name) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getUpdateTag()), new JSONObj("tag", new JSONObj("name", name).build("id", id)));
        return returnBoolean(result);
    }

    /**
     * 获取标签下粉丝列表
     * {"tagid" : 134,"next_openid":""//第一个拉取的OPENID，不填默认从头开始拉取}
     *
     * @param tagId
     * @param openId
     * @return {
     * "count":2,//这次获取的粉丝数量
     * "data":{//粉丝列表
     * "openid":[
     * "ocYxcuAEy30bX0NXmGn4ypqx3tI0",
     * "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"
     * ]
     * },
     * "next_openid":"ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"//拉取列表最后一个用户的openid
     * }
     */
    public JSONObj getTagUsers(int tagId, String openId) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getGetTagUsers()), new JSONObj("tagid", tagId).build("next_openid", openId));
        if (result != null && result.getIntValue("count") > 0) {
            return result;
        }
        return null;
    }

    /**
     * 批量为用户打标签/取消标签
     * {
     * "openid_list" : [//粉丝列表
     * "ocYxcuAEy30bX0NXmGn4ypqx3tI0",
     * "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"
     * ],
     * "tagid" : 134
     * }
     *
     * @param tagId
     * @param openId
     * @return
     */
    private Boolean updateUserTag(int tagId, List<String> openId, String url) {
        JSONObj result = HttpUtils.post(url, new JSONObj("tagid", tagId).build("openid_list", openId));
        return returnBoolean(result);
    }

    /**
     * 批量为用户打标签
     *
     * @param tagId
     * @param openId
     * @return
     */
    public Boolean bindTag(int tagId, List<String> openId) {
        return updateUserTag(tagId, openId, fillUrlParam(wxProperties.getBindTag()));
    }

    /**
     * 批量为用户取消标签
     *
     * @param tagId
     * @param openId
     * @return
     */
    public Boolean unbindTag(int tagId, List<String> openId) {
        return updateUserTag(tagId, openId, fillUrlParam(wxProperties.getUnbindTag()));
    }

    /**
     * 获取用户身上的标签列表
     * {
     * "openid" : "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"
     * }
     *
     * @param openId
     * @return
     */
    public JSONObj getUserTags(String openId) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getGetUserTags()), new JSONObj("openid", openId));
        return returnJson(result);
    }

    /**
     * 设置用户备注名
     * {
     * "openid":"oDF3iY9ffA-hqb2vVvbr7qxf6A0Q",
     * "remark":"pangzi"
     * }
     *
     * @param openId
     * @param remark
     * @return
     */
    public Boolean updateremark(String openId, String remark) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getUpdateRemark()), new JSONObj("openid", openId).build("remark", remark));
        return returnBoolean(result);
    }

    /**
     * 获取用户列表 每次最多拉取1w条
     *
     * @param nextOpenId 第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    public JSONObj getUserList(String nextOpenId) {
        JSONObj result = HttpUtils.get(fillUrlParam(wxProperties.getUserList(), nextOpenId));
        return returnJson(result);
    }

    /**
     * 获取公众号的黑名单列表 每次最多拉取1w条
     *
     * @param beginOpenId
     * @return
     */
    public JSONObj getBlackList(String beginOpenId) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getBlackList()), new JSONObj("begin_openid", beginOpenId));
        return returnJson(result);
    }

    /**
     * 拉黑用户
     * {"openid_list":["OPENID1”,” OPENID2”]}
     *
     * @param openIds
     * @return
     */
    public JSONObj blackUser(List<String> openIds) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getBatchBlack()), new JSONObj("begin_openid", openIds));
        return returnJson(result);
    }

    /**
     * 取消拉黑用户
     * {"openid_list":["OPENID1”,” OPENID2”]}
     *
     * @param openIds
     * @return
     */
    public JSONObj unBlackUser(List<String> openIds) {
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getBatchUnBlack()), new JSONObj("begin_openid", openIds));
        return returnJson(result);
    }
}
