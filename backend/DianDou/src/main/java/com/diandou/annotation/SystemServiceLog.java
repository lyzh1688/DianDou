package com.diandou.annotation;

/**
 * Created by 胡志洁 on 2016/5/16.
 */

import java.lang.annotation.*;

/**
 *自定义注解 拦截service
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface SystemServiceLog {

    String description()  default "";


}