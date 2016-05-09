package com.diandou.enumerable;

/**
 * Created by 胡志洁 on 2016/5/8.
 */
public enum AuthFieldEnum {

    sessionToken("SESSION_TOKEN");

    private String authField;

    private AuthFieldEnum(String authField){
        this.authField = authField;
    }

    public String getAuthField(){
        return this.authField;
    }
}
