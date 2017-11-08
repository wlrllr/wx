package com.wlrllr.sdk.core;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * Created by w_zhanglong on 2017/11/7.
 */
@Target(value= {ElementType.FIELD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlField {
    String value() default "";
}
