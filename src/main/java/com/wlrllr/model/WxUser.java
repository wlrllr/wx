package com.wlrllr.model;

import com.wlrllr.sdk.core.Alias;

import java.util.Date;

public class WxUser {
    private Integer id;

    //用户的昵称
    @Alias("nickname")
    private String nickName;

    private String phone;
    //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
    private String headimgurl;
    //用户所在国家
    private String country;
    //用户所在省份
    private String province;
    //用户所在城市
    private String city;
    //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private Integer sex;
    //用户的标识，对当前公众号唯一
    @Alias("openid")
    private String openId;
    //用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
    private Date subscribeTime;

    private Date unSubscribeTime;
    //Y禁用，N启用
    private String forbidden;
    //公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
    private String remark;

    private String appId;
    //用户被打上的标签ID列表
    @Alias("tagid_list")
    private Integer[] tagIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getUnSubscribeTime() {
        return unSubscribeTime;
    }

    public void setUnSubscribeTime(Date unSubscribeTime) {
        this.unSubscribeTime = unSubscribeTime;
    }

    public String getForbidden() {
        return forbidden;
    }

    public void setForbidden(String forbidden) {
        this.forbidden = forbidden == null ? null : forbidden.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }
}