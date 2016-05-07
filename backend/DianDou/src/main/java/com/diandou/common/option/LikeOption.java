package com.diandou.common.option;

import com.diandou.common.util.StringUtil;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class LikeOption extends Option {

    private final String likeVal;

    public LikeOption(String likeVal) {
        this.likeVal = likeVal;
    }

    @Override
    public String genOptionCode() {
        if(StringUtil.isNullOrEmpty(likeVal))
        {
            return "%%";
        }

        return Option.like + this.likeVal + Option.like;
    }
}
