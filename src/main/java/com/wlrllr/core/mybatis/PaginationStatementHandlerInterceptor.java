package com.wlrllr.core.mybatis;

import com.wlrllr.config.BaseProperties;
import com.wlrllr.core.dialect.Dialect;
import com.wlrllr.core.dialect.DialectFactory;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
)})
public class PaginationStatementHandlerInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(PaginationStatementHandlerInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Autowired
    private BaseProperties baseProperties;

    public Object intercept(Invocation invocation) throws Throwable {
        try {
            StatementHandler e = (StatementHandler)invocation.getTarget();
            ParameterHandler parameterHandler = e.getParameterHandler();
            BoundSql boundSql = e.getBoundSql();

            MetaObject metaStatementHandler;
            Object statementConfiguration;
            for(metaStatementHandler = MetaObject.forObject(e, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY); metaStatementHandler.hasGetter("h"); metaStatementHandler = MetaObject.forObject(statementConfiguration, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY)) {
                statementConfiguration = metaStatementHandler.getValue("h");
            }

            while(metaStatementHandler.hasGetter("target")) {
                statementConfiguration = metaStatementHandler.getValue("target");
                metaStatementHandler = MetaObject.forObject(statementConfiguration, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
            }

            Configuration statementConfiguration1 = (Configuration)metaStatementHandler.getValue("delegate.configuration");
            Connection connection = (Connection)invocation.getArgs()[0];
            String originalSql = boundSql.getSql();
            RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
            if(rowBounds != null && rowBounds instanceof Page) {
                int maxPageSize = baseProperties.getMaxPageSize();
                if(maxPageSize <= 0) {
                    maxPageSize = 50;
                }

                Page page = (Page)rowBounds;
                if(rowBounds.getLimit() > maxPageSize) {
                    page.setPageSize(maxPageSize);
                }

                Dialect dialect = DialectFactory.buildDialect(statementConfiguration1);
                String countSql = dialect.getCountString(originalSql);
                int total = this.getTotal(parameterHandler, connection, countSql);
                page.setTotalCount(total);
                metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, page.getOffset(), page.getLimit()));
                metaStatementHandler.setValue("delegate.rowBounds.offset", Integer.valueOf(0));
                metaStatementHandler.setValue("delegate.rowBounds.limit", Integer.valueOf(2147483647));
                PaginationCacheData data = PaginationPluginHolder.getData();
                if(data != null) {
                    data.setPageDB(true);
                    TransactionalCacheManager tcm = data.getTcm();
                    Cache cache = data.getCache();
                    CacheKey key = data.getKey();
                    if(tcm != null && cache != null && key != null) {
                        CacheKey totalKey = PaginationPluginHolder.buildTotalKey(key);
                        tcm.putObject(cache, totalKey, Integer.valueOf(total));
                    }
                }

                if(logger.isDebugEnabled()) {
                    logger.debug("分页SQL : " + boundSql.getSql());
                }
            }
        } catch (Exception var20) {
            logger.error("PaginationStatementHandlerInterceptor error", var20);
        }

        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }

    private int getTotal(ParameterHandler parameterHandler, Connection connection, String countSql) throws Exception {
        PreparedStatement prepareStatement = connection.prepareStatement(countSql);
        parameterHandler.setParameters(prepareStatement);
        ResultSet rs = prepareStatement.executeQuery();
        int count = 0;

        try {
            if(rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            if(rs != null) {
                rs.close();
            }

        }

        prepareStatement.close();
        return count;
    }
}
