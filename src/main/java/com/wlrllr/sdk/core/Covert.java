package com.wlrllr.sdk.core;

import java.lang.annotation.*;

/**
 * 后面最补充，将对象转成JSON，包含别名，类型转换
 */
@Target(value= {ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Covert {
    Class target();
    String format() default "";
    Class source();
}
