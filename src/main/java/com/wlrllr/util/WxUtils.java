package com.wlrllr.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/19.
 */
public class WxUtils {

    private static final Logger logger = LoggerFactory.getLogger(WxUtils.class);
    /**
     * 校验链接有效性
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param token
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        if (StringUtils.isNotBlank(signature) && StringUtils.isNotBlank(timestamp) && StringUtils.isNotBlank(nonce)) {
            List<String> params = new ArrayList<>();
            params.add(token);
            params.add(timestamp);
            params.add(nonce);
            Collections.sort(params);
            StringBuilder signBuilder = new StringBuilder();
            for (String string : params) {
                signBuilder.append(string);
            }
            String sign = DigestUtils.sha1Hex(signBuilder.toString()).toLowerCase();
            if (sign.equalsIgnoreCase(signature)) {
                return true;
            }
        }
        return false;
    }

    public static JSONObject parseRequest(HttpServletRequest request) {
        JSONObject data = new JSONObject();
        try {
            Document document = new SAXReader().read(request.getInputStream());
            Element root = document.getRootElement();
            XmlUtils.xmlToJson(root,data);
        } catch (Exception e) {
            logger.error(">>>>>>>解析请求参数异常<<<<<<<<<<",e);
        }
        return data;
    }
}
