package com.wlrllr.sdk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static final List<Integer> indexsOf(String str, String target) {
        if(StringUtils.isNotBlank(str) && StringUtils.isNotBlank(target)) {
            ArrayList list = new ArrayList();
            Pattern pattern = Pattern.compile(target, 2);
            Matcher matcher = pattern.matcher(str);

            while(matcher.find()) {
                list.add(Integer.valueOf(matcher.start()));
            }

            return list;
        } else {
            return null;
        }
    }
}
