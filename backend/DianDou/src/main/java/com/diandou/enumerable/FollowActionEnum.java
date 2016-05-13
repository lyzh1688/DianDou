package com.diandou.enumerable;

/**
 * Created by 胡志洁 on 2016/5/13.
 */
public enum FollowActionEnum {
    follow("1"),
    nofollow("0");


    private String action;

    private FollowActionEnum(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }

}
