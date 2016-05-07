package com.diandou.annotation;

import java.lang.annotation.*;

/**
 * Created by 胡志洁 on 2016/5/4.
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TBMapper {
    String TabName() default "";
    String Alias() default "";
}
