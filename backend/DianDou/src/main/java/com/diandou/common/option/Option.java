package com.diandou.common.option;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public abstract class Option {
    protected static final String and = "and";
    protected static final String eq = "=";
    protected static final String like = "%";
    public abstract String genOptionCode();
}
