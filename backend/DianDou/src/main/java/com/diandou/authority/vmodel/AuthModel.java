package com.diandou.authority.vmodel;

import com.diandou.enumerable.AuthStatusEnum;

/**
 * Created by 胡志洁 on 2016/5/8.
 */
public class AuthModel {

    private final AuthStatusEnum authStatus;

    private final String sessionToken;

    private final String userId;

    public String getUserId() {
        return userId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public AuthStatusEnum getAuthStatus() {
        return authStatus;
    }

    public AuthModel(Builder builder){
        this.sessionToken = builder.sessionToken;
        this.authStatus = builder.authStatus;
        this.userId = builder.userId;
    }

    public static class Builder{
        private String sessionToken;
        private AuthStatusEnum authStatus;
        private String userId;

        public Builder userId(String userId){
            this.userId = userId;
            return this;
        }

        public Builder sessionToken(String sessionToken){
            this.sessionToken = sessionToken;
            return this;
        }

        public Builder authStatus(AuthStatusEnum authStatus){
            this.authStatus = authStatus;
            return this;
        }

        public AuthModel build(){
            return new AuthModel(this);
        }
    }
}
