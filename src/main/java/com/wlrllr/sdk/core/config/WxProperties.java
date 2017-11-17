package com.wlrllr.sdk.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 微信用配置
 *
 * @author wlrllr
 *
 */
@Configuration
@PropertySource("classpath:wx.properties")
public class WxProperties {

	@Value("${base.serverUrl}")
	private String serverUrl;
	@Value("${base.appId}")
	private String appId;
	@Value("${base.appSecret}")
	private String appSecret;
	@Value("${base.token}")
	private String token;
	@Value("${base.encodingAESKey}")
	private String encodingAESKey;
	@Value("${base.mchId}")
	private String mchId;
	@Value("${api.accessTokenUrl}")
	private String accessTokenUrl;
	@Value("${api.jsapiTicketUrl}")
	private String jsapiTicketUrl;
	@Value("${api.openidUrl}")
	private String openidUrl;
	@Value("${api.openidGet}")
	private String openidGet;
	@Value("${api.openidNotify}")
	private String openidNotify;
	@Value("${api.authUserAccessTokenUrl}")
	private String authUserAccessTokenUrl;
	@Value("${api.paymentUnifiedOrderUrl}")
	private String paymentUnifiedOrderUrl;
	@Value("${api.paymentNotifyUrl}")
	private String paymentNotifyUrl;
	@Value("${api.paymentQueryOrderUrl}")
	private String paymentQueryOrderUrl;
	@Value("${api.paymentCloseOrderUrl}")
	private String paymentCloseOrderUrl;
	@Value("${template.sendUrl}")
	private String sendUrl;
	@Value("${template.repayInfoId}")
	private String repayInfoId;
	@Value("${template.redirectDescUrl}")
	private String redirectDescUrl;
	// =================================菜单管理=======================
	@Value("${api.addMenu}")
	private String addMenu;
	@Value("${api.deleteMenu}")
	private String deleteMenu;
	@Value("${api.getMenu}")
	private String getMenu;
	@Value("${api.addConditional}")
	private String addConditional;
	// ==============================客服管理=======================
	@Value("${api.addkf}")
	private String addkf;
	@Value("${api.updatekf}")
	private String updatekf;
	@Value("${api.deletekf}")
	private String deletekf;
	@Value("${api.uploadkfimg}")
	private String uploadkfimg;
	@Value("${api.getkflist}")
	private String getkflist;
	@Value("${api.sendkfmsg}")
	private String sendkfmsg;
    @Value("${api.typing}")
    private String typing;

	@Value("${api.createSession}")
	private String createSession; //创建会话
	@Value("${api.closeSession}")
	private String closeSession; //关闭会话
	@Value("${api.getSession}")
	private String getSession; //获取一个会话
	@Value("${api.getSessionList}")
	private String getSessionList; //获取客服会话列表
	@Value("${api.getWaitCase}")
	private String getWaitCase; //获取未接入会话列表
	/**===============================素材管理=============================**/
	@Value("${api.addMedia}")
	private String addMedia;
	@Value("${api.getMedia}")
	private String getMedia;
	@Value("${api.addNews}")
	private String addNews;
	@Value("${api.uploadImg}")
	private String uploadImg;
	@Value("${api.addMaterial}")
	private String addMaterial;
	@Value("${api.getMaterial}")
	private String getMaterial;
	@Value("${api.delMaterial}")
	private String delMaterial;
	@Value("${api.getMaterialCount}")
	private String getMaterialCount;
	@Value("${api.getMaterialList}")
	private String getMaterialList;
	@Value("${api.updateNews}")
	private String updateNews;
    @Value("${api.uploadNews}")
    private String uploadNews;
    @Value("${api.uploadVideo}")
    private String uploadVideo;

    /**
     * ================================发送消息=============================
     **/

    @Value("${api.sendByTag}")
    private String sendByTag;
    @Value("${api.sendByOpenId}")
    private String sendByOpenId;
    /**
     * ===============================用户管理=============================
     **/
    @Value("${api.addTag}")
	private String addTag;
	@Value("${api.getTag}")
	private String getTag;
	@Value("${api.updateTag}")
	private String updateTag;
	@Value("${api.deleteTag}")
	private String deleteTag;
	@Value("${api.getTagUsers}")
	private String getTagUsers;
	@Value("${api.bindTag}")
	private String bindTag;
	@Value("${api.unbindTag}")
	private String unbindTag;
	@Value("${api.getUserTags}")
	private String getUserTags;

	@Value("${api.getUserInfo}")
	private String userInfo;
	@Value("${api.updateRemark}")
	private String updateRemark;
	@Value("${api.getUserList}")
	private String userList;
	@Value("${api.getBlackList}")
	private String blackList;
	@Value("${api.batchBlackList}")
	private String batchBlack;
	@Value("${api.batchUnBlackList}")
	private String batchUnBlack;


	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	public String getJsapiTicketUrl() {
		return jsapiTicketUrl;
	}

	public void setJsapiTicketUrl(String jsapiTicketUrl) {
		this.jsapiTicketUrl = jsapiTicketUrl;
	}

	public String getOpenidUrl() {
		return openidUrl;
	}

	public void setOpenidUrl(String openidUrl) {
		this.openidUrl = openidUrl;
	}

	public String getOpenidGet() {
		return openidGet;
	}

	public void setOpenidGet(String openidGet) {
		this.openidGet = openidGet;
	}

	public String getOpenidNotify() {
		return openidNotify;
	}

	public void setOpenidNotify(String openidNotify) {
		this.openidNotify = openidNotify;
	}

	public String getAuthUserAccessTokenUrl() {
		return authUserAccessTokenUrl;
	}

	public void setAuthUserAccessTokenUrl(String authUserAccessTokenUrl) {
		this.authUserAccessTokenUrl = authUserAccessTokenUrl;
	}

	public String getPaymentUnifiedOrderUrl() {
		return paymentUnifiedOrderUrl;
	}

	public void setPaymentUnifiedOrderUrl(String paymentUnifiedOrderUrl) {
		this.paymentUnifiedOrderUrl = paymentUnifiedOrderUrl;
	}

	public String getPaymentNotifyUrl() {
		return paymentNotifyUrl;
	}

	public void setPaymentNotifyUrl(String paymentNotifyUrl) {
		this.paymentNotifyUrl = paymentNotifyUrl;
	}

	public String getPaymentQueryOrderUrl() {
		return paymentQueryOrderUrl;
	}

	public void setPaymentQueryOrderUrl(String paymentQueryOrderUrl) {
		this.paymentQueryOrderUrl = paymentQueryOrderUrl;
	}

	public String getPaymentCloseOrderUrl() {
		return paymentCloseOrderUrl;
	}

	public void setPaymentCloseOrderUrl(String paymentCloseOrderUrl) {
		this.paymentCloseOrderUrl = paymentCloseOrderUrl;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getRepayInfoId() {
		return repayInfoId;
	}

	public void setRepayInfoId(String repayInfoId) {
		this.repayInfoId = repayInfoId;
	}

	public String getRedirectDescUrl() {
		return redirectDescUrl;
	}

	public void setRedirectDescUrl(String redirectDescUrl) {
		this.redirectDescUrl = redirectDescUrl;
	}

	public String getAddTag() {
		return addTag;
	}

	public void setAddTag(String addTag) {
		this.addTag = addTag;
	}

	public String getGetTag() {
		return getTag;
	}

	public void setGetTag(String getTag) {
		this.getTag = getTag;
	}

	public String getUpdateTag() {
		return updateTag;
	}

	public void setUpdateTag(String updateTag) {
		this.updateTag = updateTag;
	}

	public String getDeleteTag() {
		return deleteTag;
	}

	public void setDeleteTag(String deleteTag) {
		this.deleteTag = deleteTag;
	}

	public String getGetTagUsers() {
		return getTagUsers;
	}

	public void setGetTagUsers(String getTagUsers) {
		this.getTagUsers = getTagUsers;
	}

	public String getBindTag() {
		return bindTag;
	}

	public void setBindTag(String bindTag) {
		this.bindTag = bindTag;
	}

	public String getUnbindTag() {
		return unbindTag;
	}

	public void setUnbindTag(String unbindTag) {
		this.unbindTag = unbindTag;
	}

	public String getGetUserTags() {
		return getUserTags;
	}

	public void setGetUserTags(String getUserTags) {
		this.getUserTags = getUserTags;
	}

	public String getAddkf() {
		return addkf;
	}

	public void setAddkf(String addkf) {
		this.addkf = addkf;
	}

	public String getUpdatekf() {
		return updatekf;
	}

	public void setUpdatekf(String updatekf) {
		this.updatekf = updatekf;
	}

	public String getDeletekf() {
		return deletekf;
	}

	public void setDeletekf(String deletekf) {
		this.deletekf = deletekf;
	}

	public String getUploadkfimg() {
		return uploadkfimg;
	}

	public void setUploadkfimg(String uploadkfimg) {
		this.uploadkfimg = uploadkfimg;
	}

	public String getGetkflist() {
		return getkflist;
	}

	public void setGetkflist(String getkflist) {
		this.getkflist = getkflist;
	}

	public String getSendkfmsg() {
		return sendkfmsg;
	}

	public void setSendkfmsg(String sendkfmsg) {
		this.sendkfmsg = sendkfmsg;
	}

	public String getAddMedia() {
		return addMedia;
	}

	public void setAddMedia(String addMedia) {
		this.addMedia = addMedia;
	}

	public String getGetMedia() {
		return getMedia;
	}

	public void setGetMedia(String getMedia) {
		this.getMedia = getMedia;
	}

	public String getAddNews() {
		return addNews;
	}

	public void setAddNews(String addNews) {
		this.addNews = addNews;
	}

	public String getUploadImg() {
		return uploadImg;
	}

	public void setUploadImg(String uploadImg) {
		this.uploadImg = uploadImg;
	}

	public String getAddMaterial() {
		return addMaterial;
	}

	public void setAddMaterial(String addMaterial) {
		this.addMaterial = addMaterial;
	}

	public String getGetMaterial() {
		return getMaterial;
	}

	public void setGetMaterial(String getMaterial) {
		this.getMaterial = getMaterial;
	}

	public String getDelMaterial() {
		return delMaterial;
	}

	public void setDelMaterial(String delMaterial) {
		this.delMaterial = delMaterial;
	}

	public String getGetMaterialCount() {
		return getMaterialCount;
	}

	public void setGetMaterialCount(String getMaterialCount) {
		this.getMaterialCount = getMaterialCount;
	}

	public String getGetMaterialList() {
		return getMaterialList;
	}

	public void setGetMaterialList(String getMaterialList) {
		this.getMaterialList = getMaterialList;
	}

	public String getUpdateNews() {
		return updateNews;
	}

	public void setUpdateNews(String updateNews) {
		this.updateNews = updateNews;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getUpdateRemark() {
		return updateRemark;
	}

	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public String getBlackList() {
		return blackList;
	}

	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}

	public String getBatchBlack() {
		return batchBlack;
	}

	public void setBatchBlack(String batchBlack) {
		this.batchBlack = batchBlack;
	}

	public String getBatchUnBlack() {
		return batchUnBlack;
	}

	public void setBatchUnBlack(String batchUnBlack) {
		this.batchUnBlack = batchUnBlack;
	}

	public String getAddMenu() {
		return addMenu;
	}

	public void setAddMenu(String addMenu) {
		this.addMenu = addMenu;
	}

	public String getDeleteMenu() {
		return deleteMenu;
	}

	public void setDeleteMenu(String deleteMenu) {
		this.deleteMenu = deleteMenu;
	}

	public String getGetMenu() {
		return getMenu;
	}

	public void setGetMenu(String getMenu) {
		this.getMenu = getMenu;
	}

    public String getAddConditional() {
        return addConditional;
    }

    public void setAddConditional(String addConditional) {
        this.addConditional = addConditional;
    }

    public String getTyping() {
        return typing;
    }

    public void setTyping(String typing) {
        this.typing = typing;
    }

    public String getUploadNews() {
        return uploadNews;
    }

    public void setUploadNews(String uploadNews) {
        this.uploadNews = uploadNews;
    }

    public String getSendByTag() {
        return sendByTag;
    }

    public void setSendByTag(String sendByTag) {
        this.sendByTag = sendByTag;
    }

    public String getSendByOpenId() {
        return sendByOpenId;
    }

    public void setSendByOpenId(String sendByOpenId) {
        this.sendByOpenId = sendByOpenId;
    }

    public String getUploadVideo() {
        return uploadVideo;
    }

    public void setUploadVideo(String uploadVideo) {
        this.uploadVideo = uploadVideo;
    }

	public String getCreateSession() {
		return createSession;
	}

	public void setCreateSession(String createSession) {
		this.createSession = createSession;
	}

	public String getCloseSession() {
		return closeSession;
	}

	public void setCloseSession(String closeSession) {
		this.closeSession = closeSession;
	}

	public String getGetSession() {
		return getSession;
	}

	public void setGetSession(String getSession) {
		this.getSession = getSession;
	}

	public String getGetSessionList() {
		return getSessionList;
	}

	public void setGetSessionList(String getSessionList) {
		this.getSessionList = getSessionList;
	}

	public String getGetWaitCase() {
		return getWaitCase;
	}

	public void setGetWaitCase(String getWaitCase) {
		this.getWaitCase = getWaitCase;
	}
}
