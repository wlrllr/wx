package com.wlrllr.demo;

import com.wlrllr.constants.DataConstants;
import com.wlrllr.sdk.api.MenuApi;
import com.wlrllr.sdk.api.TokenApi;
import com.wlrllr.sdk.api.MenuItem;
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
        //创建3个一级菜单
        List<MenuItem> first = new ArrayList<>();
        first.add(MenuItem.newView("网页","http://www.baidu.com"));
        first.add(MenuItem.newButton("查询一些","SEARCH_SOMETHING"));
        first.add(MenuItem.newButton("查询一些","SEARCH_SOMETHING"));
        list.add(MenuItem.newMenu("菜单1",first));
        List<MenuItem> second = new ArrayList<>();
        second.add(MenuItem.newScanPush("扫码推送","SCAN_PUSH"));
        second.add(MenuItem.newScanWait("扫码等待","SCAN_WAIT"));
        second.add(MenuItem.newLocation("定位","LOCATION"));
        list.add(MenuItem.newMenu("菜单2",second));
        List<MenuItem> third = new ArrayList<>();
        third.add(MenuItem.newPic("拍照或相册","PHOTO_OR_ALBUM"));
        third.add(MenuItem.newSysPic("系统拍照","SYS_PHOTO"));
        third.add(MenuItem.newPicWx("微信相册","WX_PIC"));
        list.add(MenuItem.newMenu("菜单3",third));
       return menuApi.createMenu(list);
    }
}
