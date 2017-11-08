package com.wlrllr.demo;

import com.wlrllr.constants.DataConstants;
import com.wlrllr.sdk.api.MenuApi;
import com.wlrllr.sdk.api.TokenApi;
import com.wlrllr.sdk.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/12.
 */
@RestController
@RequestMapping("/test")
public class WxController {

    @Autowired
    private TokenApi wxApi;
    @Autowired
    private MenuApi menuApi;

    @RequestMapping("/token")
    String token(){
        return DataConstants.ACCESSTOKEN;
    }

    @RequestMapping("/createMenu")
    Boolean createMenu(){
        List<MenuItem> list = new ArrayList<>();
        MenuItem item = new MenuItem();
        list.add(item);
        item.setUrl("http://www.baidu.com");
        item.setType("view");
        item.setName("网页");
        MenuItem item1 = new MenuItem();
        list.add(item1);
        item1.setKey("SEARCH_SOMETHING");
        item1.setType("click");
        item1.setName("查询一些");
       return menuApi.createMenu(list);
    }
}
