package com.wlrllr.service.impl;

import com.wlrllr.mapper.BaseMapper;
import com.wlrllr.mapper.WxAppMapper;
import com.wlrllr.model.WxApp;
import com.wlrllr.model.WxAppExample;
import com.wlrllr.service.WxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
@Service
public class WxAppServiceImpl extends BaseServiceImpl<WxApp,WxAppExample,String> implements WxAppService {

    @Autowired
    private WxAppMapper wxAppMapper;


    @Override
    public BaseMapper<WxApp,WxAppExample,String> getMapper() {
        return wxAppMapper;
    }

    @Override
    public WxApp selectByAppId(String appId) {
        WxAppExample example = new WxAppExample();
        example.createCriteria().andAppIdEqualTo(appId);
        List<WxApp> list = wxAppMapper.selectByExample(example);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<WxApp> all(){
        return  wxAppMapper.selectByExample(new WxAppExample());
    }
}
