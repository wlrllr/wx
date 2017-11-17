package com.wlrllr.demo;

import com.wlrllr.sdk.api.*;
import com.wlrllr.sdk.api.model.JSONObj;
import com.wlrllr.sdk.core.ThreadLocalParam;
import com.wlrllr.sdk.util.InputStreamBody;
import com.wlrllr.sdk.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/12.
 */
@Controller
@RequestMapping("/msg")
public class WxWebController {

    private static Logger logger = LoggerFactory.getLogger(WxWebController.class);
    @Autowired
    private MsgApi msgApi;
    @Autowired
    private MediaApi mediaApi;
    @Autowired
    private KfApi kfApi;

    private static String account="gh_6197dceca93a";

    @RequestMapping("/init")
    public ModelAndView init(){
        ModelAndView model = new ModelAndView("sendMsg");
       return model;
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    JSONObj sendMsg(String to, String toType, String msgType, String content, @RequestParam(value="file",required = false) MultipartFile[] files) {
        ThreadLocalParam.setAccount(account);
        JSONObj result = new JSONObj("status",true);
        try{
            if("1".equals(toType)){
                List<String> toUser = Arrays.asList(to.split(","));
                msgApi.sendByOpenId(toUser,content,msgType);
            }else if("2".equals(toType)){
                msgApi.sendByTag(to,content,msgType);
            }
            result.build("msg","发送消息成功!");
        }catch (Exception e){
            logger.error(">>>>>>>>>>>>>>发送消息失败<<<<<<<<<<<",e);
            result.build("status",false).build("msg","发送消息失败");
        }
        return result;
    }

    @RequestMapping("/uploadMedia")
    @ResponseBody
    JSONObj uploadMedia(String mediaStyle,String mediaType,@RequestParam(value="file",required = false) MultipartFile[] files) {
        ThreadLocalParam.setAccount(account);
        JSONObj result = new JSONObj("status",true);
        try{
            String media="";
            InputStreamBody file = new InputStreamBody(files[0].getInputStream(),files[0].getSize(),files[0].getOriginalFilename());
            if("1".equals(mediaStyle)){
                media = mediaApi.addMedia(mediaType,file);
                if(StringUtils.isNotBlank(media)){
                    logger.info(">>>>>>>上传成功:{}<<<<<<<",media);
                }
            }else if("2".equals(mediaStyle)){
                JSONObj obj = mediaApi.addMaterial(mediaType,file,"","");
                media = obj.toJSONString();
                if(StringUtils.isNotBlank(media)){
                    logger.info(">>>>>>>上传成功:{}<<<<<<<",media);
                }
            }
            result.build("msg","上传成功!"+media);
        }catch (Exception e){
            logger.error(">>>>>>>>>>>>>>上传失败<<<<<<<<<<<",e);
            result.build("status",false).build("msg","上传失败");
        }
        return result;
    }

    @RequestMapping("/getMedia")
    @ResponseBody
    JSONObj getMedia(String getMediaStyle,String getMediaId) {
        ThreadLocalParam.setAccount(account);
        JSONObj result = new JSONObj("status",true);
        try{
            String media="";
            if("1".equals(getMediaStyle)){
                media = mediaApi.getMedia(getMediaId);
                if(StringUtils.isNotBlank(media)){
                    logger.info(">>>>>>>获取临时素材成功:{}<<<<<<<",media);
                }
            }else if("2".equals(getMediaStyle)){
                JSONObj obj = mediaApi.getMaterial(getMediaId);
                media = obj.toJSONString();
                if(StringUtils.isNotBlank(media)){
                    logger.info(">>>>>>>获取永久素材成功:{}<<<<<<<",media);
                }
            }
            result.build("msg","获取成功!"+media);
        }catch (Exception e){
            logger.error(">>>>>>>>>>>>>>获取素材失败<<<<<<<<<<<",e);
            result.build("status",false).build("msg","获取素材失败");
        }
        return result;
    }

    @RequestMapping("/addKf")
    @ResponseBody
    JSONObj addKf(String kfAccount,String kfNickName) {
        ThreadLocalParam.setAccount(account);
        JSONObj result = new JSONObj("status",true);
        try{
            kfAccount = buildAccount(kfAccount);
            if(kfApi.addKf(kfAccount,kfNickName)){
                result.build("msg","添加客服失败");
            }else{
                result.build("status",false).build("msg","添加客服失败");
            }
        }catch (Exception e){
            logger.error(">>>>>>>>>>>>>>添加客服失败<<<<<<<<<<<",e);
            result.build("status",false).build("msg","添加客服失败");
        }
        return result;
    }

    @RequestMapping("/createKf")
    @ResponseBody
    JSONObj createKf(String kfAccount,String kfNickName) {
        ThreadLocalParam.setAccount(account);
        JSONObj result = new JSONObj("status",true);
        try{
            kfAccount = buildAccount(kfAccount);
            if(kfApi.addKf(kfAccount,kfNickName)){
                result.build("msg","接入客服失败");
            }else{
                result.build("status",false).build("msg","接入客服失败");
            }
        }catch (Exception e){
            logger.error(">>>>>>>>>>>>>>接入客服失败<<<<<<<<<<<",e);
            result.build("status",false).build("msg","接入客服失败");
        }
        return result;
    }

    private String buildAccount(String account){
        String appName = ThreadLocalParam.getApp().getAccount();
        return account + "@"+appName;
    }
}
