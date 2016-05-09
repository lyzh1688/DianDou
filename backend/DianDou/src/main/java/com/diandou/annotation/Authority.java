package com.diandou.annotation;

import java.lang.annotation.*;

/**
 * Created by 胡志洁 on 2016/5/8.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    boolean validate() default true;
}
