package com.lg.util.annotation;

import java.lang.annotation.*;

/**
 * Created by liuguo on 2017/6/13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyBatisMapper {
    String value() default "";
}
