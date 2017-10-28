package com.wlrllr;

import com.wlrllr.constants.DataConstants;
import com.wlrllr.core.WxApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by w_zhanglong on 2017/10/12.
 */
@RestController
@RequestMapping("/test")
public class WxController {

    @Autowired
    private WxApi wxApi;

    @RequestMapping("/token")
    String token(){
        return DataConstants.ACCESSTOKEN;
    }
}
