package com.wlrllr.sdk.api;

import com.wlrllr.sdk.core.config.WxProperties;
import com.wlrllr.sdk.api.model.JSONObj;
import com.wlrllr.sdk.api.model.MenuItem;
import com.wlrllr.sdk.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户相关接口
 * Created by w_zhanglong on 2017/10/24.
 */
@Component
public class MenuApi extends BaseApi {

    @Autowired
    private WxProperties wxProperties;

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    public Boolean createMenu(List<MenuItem> menu) {
        if (menu != null) {
            for (MenuItem item : menu) {
                if (!item.validate()) {
                    return false;
                }
            }
        }
        JSONObj result = HttpUtils.post(fillUrlParam(wxProperties.getAddMenu()), new JSONObj("button", menu));
        return returnBoolean(result);
    }

    /**
     * 删除菜单
     *
     * @return
     */
    public Boolean deleteMenu() {
        JSONObj result = HttpUtils.get(fillUrlParam(wxProperties.getDeleteMenu()));
        return returnBoolean(result);
    }

    /**
     * 获取菜单
     *
     * @return FIXME 这里应该转成List<MenuIten>对象，后面在做
     */
    public JSONObj getMenu() {
        JSONObj result = HttpUtils.get(fillUrlParam(wxProperties.getGetMenu()));
        return returnJson(result);
    }

    /**
     * 创建个性化菜单
     *
     * @param menu
     * @return
     */
    public JSONObj createCustomMenu(List<MenuItem> menu) {

        return null;
    }


}
