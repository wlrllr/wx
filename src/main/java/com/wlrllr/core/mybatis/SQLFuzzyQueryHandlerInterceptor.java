package com.wlrllr.core.mybatis;

import com.wlrllr.util.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
)})
public class SQLFuzzyQueryHandlerInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(SQLFuzzyQueryHandlerInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    private static final String LIKE_REG = "\\s+((like)|(LIKE))\\s+((concat)|(CONCAT))\\s*\\(([^\\(]*)\\)";
    private static final String PARAM_SEAT = "\\?";

    public SQLFuzzyQueryHandlerInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();

        MetaObject metaStatementHandler;
        Object mappedStatement;
        for(metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY); metaStatementHandler.hasGetter("h"); metaStatementHandler = MetaObject.forObject(mappedStatement, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY)) {
            mappedStatement = metaStatementHandler.getValue("h");
        }

        while(metaStatementHandler.hasGetter("target")) {
            mappedStatement = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(mappedStatement, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }

        MappedStatement mappedStatement1 = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
        Configuration mappedConfiguration = mappedStatement1.getConfiguration();
        String originalSql = boundSql.getSql();

        try {
            List e = this.getLikeParamsIndex(originalSql);
            if(e != null && e.size()>0) {
                List parameterMappings = boundSql.getParameterMappings();
                Object parameterObject = boundSql.getParameterObject();
                if(parameterMappings != null && parameterMappings.size()>0) {
                    TypeHandlerRegistry typeHandlerRegistry = mappedConfiguration.getTypeHandlerRegistry();
                    MetaObject metaObject = parameterObject == null?null:mappedConfiguration.newMetaObject(parameterObject);
                    Iterator i$ = e.iterator();

                    while(true) {
                        ParameterMapping parameterMapping;
                        do {
                            Integer i;
                            do {
                                do {
                                    if(!i$.hasNext()) {
                                        return invocation.proceed();
                                    }

                                    i = (Integer)i$.next();
                                } while(i == null);
                            } while(parameterMappings.size() < i.intValue() + 1);

                            parameterMapping = (ParameterMapping)parameterMappings.get(i.intValue());
                        } while(!parameterMapping.getJavaType().equals(String.class));

                        Object value = null;
                        String propertyName = parameterMapping.getProperty();
                        if(parameterMapping.getMode() != ParameterMode.OUT) {
                            PropertyTokenizer oldValue = new PropertyTokenizer(propertyName);
                            if(parameterObject == null) {
                                value = null;
                            } else if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                                value = parameterObject;
                            } else if(boundSql.hasAdditionalParameter(propertyName)) {
                                value = boundSql.getAdditionalParameter(propertyName);
                            } else if(propertyName.startsWith("__frch_") && boundSql.hasAdditionalParameter(oldValue.getName())) {
                                value = boundSql.getAdditionalParameter(oldValue.getName());
                                if(value != null) {
                                    value = mappedConfiguration.newMetaObject(value).getValue(propertyName.substring(oldValue.getName().length()));
                                }
                            } else {
                                value = metaObject == null?null:metaObject.getValue(propertyName);
                            }
                        }

                        if(value != null) {
                            String oldValue1 = value.toString();
                            String newValue = this.escapeParam(oldValue1);
                            boundSql.setAdditionalParameter(propertyName, newValue);
                            logger.info("escape : propertyName={}, oldValue={}, newValue={}", new Object[]{propertyName, oldValue1, newValue});
                        }
                    }
                }
            }
        } catch (Exception var20) {
            logger.error("filter fuzzy query error", var20);
        }

        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }

    private List<Integer> getLikeParamsIndex(String sql) {
        Pattern pattern = Pattern.compile("\\s+((like)|(LIKE))\\s+((concat)|(CONCAT))\\s*\\(([^\\(]*)\\)", 32);
        Matcher matcher = pattern.matcher(sql);
        HashMap likes = new HashMap();

        while(matcher.find()) {
            String sqlSeatIndex = matcher.group();
            likes.put(Integer.valueOf(matcher.start()), sqlSeatIndex);
        }

        if(likes == null) {
            return null;
        } else {
            List var15 = StringUtils.indexsOf(sql, "\\?");
            HashMap sqlSeatIndexMap = new HashMap();
            if(var15 != null) {
                for(int paramsIndex = 0; paramsIndex < var15.size(); ++paramsIndex) {
                    sqlSeatIndexMap.put(var15.get(paramsIndex), Integer.valueOf(paramsIndex));
                }
            }

            ArrayList var16 = new ArrayList();
            Iterator i$ = likes.entrySet().iterator();

            while(true) {
                int likeIndex;
                List likeSeatIndex;
                do {
                    if(!i$.hasNext()) {
                        return var16;
                    }

                    Map.Entry entry = (Map.Entry)i$.next();
                    likeIndex = ((Integer)entry.getKey()).intValue();
                    String like = (String)entry.getValue();
                    likeSeatIndex = StringUtils.indexsOf(like, "\\?");
                } while(likeSeatIndex == null);

                Iterator i$1 = likeSeatIndex.iterator();

                while(i$1.hasNext()) {
                    Integer index = (Integer)i$1.next();
                    index = Integer.valueOf(index.intValue() + likeIndex);
                    var16.add(sqlSeatIndexMap.get(index));
                }
            }
        }
    }

    private String escapeParam(String param) {
        if(StringUtils.isNotBlank(param)) {
            StringBuilder builder = new StringBuilder();
            char[] chars = param.toCharArray();
            char[] arr$ = chars;
            int len$ = chars.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                char c = arr$[i$];
                if(c == 37) {
                    builder.append("\\%");
                } else if(c == 95) {
                    builder.append("\\_");
                } else if(c == 94) {
                    builder.append("\\^");
                } else if(c == 91) {
                    builder.append("\\[");
                } else {
                    builder.append(c);
                }
            }

            param = builder.toString();
        }

        return param;
    }
}
