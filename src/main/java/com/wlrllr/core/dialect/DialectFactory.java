package com.wlrllr.core.dialect;

import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public class DialectFactory {
    private static final Logger logger = LoggerFactory.getLogger(DialectFactory.class);

    public static Dialect buildDialect(Configuration configuration) {
        String dialectClass = null;
        if(dialectClass == null) {
            Class dialect = DialectFactory.class;
            synchronized(DialectFactory.class) {
                dialectClass = configuration.getVariables().getProperty("dialectClass");
            }
        }

        Dialect dialect1 = null;

        try {
            dialect1 = (Dialect)Class.forName(dialectClass).newInstance();
        } catch (Exception var4) {
            logger.error("请检查 mybatis-config.xml 中  dialectClass 是否配置正确?", var4);
        }

        return dialect1;
    }
}
