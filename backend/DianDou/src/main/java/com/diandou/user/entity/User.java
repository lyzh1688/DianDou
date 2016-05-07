package com.diandou.user.entity;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class User {
    private final String userId;

    private final String userName;

    private final String headPortrait;

    private final String brief;

    private final String mobile;
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public String getBrief() {
        return brief;
    }

    public String getMobile() {
        return mobile;
    }

    private User(Builder builder){
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.headPortrait = builder.headPortrait;
        this.brief = builder.brief;
        this.mobile = builder.mobile;
    }

    public static class Builder{

        private String userId;

        private String userName;

        private String headPortrait;

        private String brief;

        private String mobile;

        public Builder userId(String userId){
            this.userId = userId;
            return this;
        }

        public Builder userName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder headPortrait(String headPortrait){
            this.headPortrait = headPortrait;
            return this;
        }

        public Builder brief(String brief){
            this.brief = brief;
            return this;
        }

        public Builder mobile(String mobile){
            this.mobile = mobile;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
