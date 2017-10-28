package com.wlrllr.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(WxException.class);

	/**
	 * accessToken超时错误码
	 */
	public static final String ERROR_ACCESS_TOKEN_TIMEOUT = "42001";

	private String errorCode;
	private String errorMsg;

	public static WxException getWechatException(String code, String msg) {
		logger.error("wechat exception : code={}, msg={}", code, msg);
		return new WxException(code, msg);
	}

	public WxException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
