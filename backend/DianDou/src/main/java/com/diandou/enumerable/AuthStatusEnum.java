package com.diandou.enumerable;

/**
 * Created by 胡志洁 on 2016/5/8.
 */
public enum AuthStatusEnum {

    pass("PASS"),
    fail("FAIL"),
    logout("LOGOUT");

    private String status;

    private AuthStatusEnum(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
