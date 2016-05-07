package com.diandou.annotation;

import java.lang.annotation.*;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBMapper {
    String FieldName() default "";
}
