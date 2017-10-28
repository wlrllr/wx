package com.wlrllr.service.impl;

import com.wlrllr.mapper.BaseMapper;
import com.wlrllr.mapper.WxUserMapper;
import com.wlrllr.model.WxUser;
import com.wlrllr.model.WxUserExample;
import com.wlrllr.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
@Service
public class WxUserServiceImpl extends BaseServiceImpl<WxUser,WxUserExample,Integer> implements WxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;


    @Override
    public BaseMapper<WxUser, WxUserExample, Integer> getMapper() {
        return wxUserMapper;
    }

    @Override
    public void forbid(String openid,String appId) {
        WxUserExample example = new WxUserExample();
        example.createCriteria().andOpenIdEqualTo(openid).andAppIdEqualTo(appId);
        WxUser user = new WxUser();
        user.setForbidden("Y");
        user.setUnsubscribeTime(new Date());
        wxUserMapper.updateByExampleSelective(user,example);
    }

    @Override
    public void addUser(WxUser user) {
        WxUserExample example = new WxUserExample();
        example.createCriteria().andOpenIdEqualTo(user.getOpenId()).andAppIdEqualTo(user.getAppId());
        List<WxUser> list = wxUserMapper.selectByExample(example);
        if(list != null && list.size()>0){
            WxUser record = new WxUser();
            record.setId(list.get(0).getId());
            record.setForbidden("N");
            record.setSubscribeTime(new Date());
            wxUserMapper.updateByPrimaryKeySelective(record);
        }
        wxUserMapper.insertSelective(user);
    }
}
