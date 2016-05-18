package com.diandou.user.entity;

/**
 * Created by 胡志洁 on 2016/5/18.
 */
public class UserTag {

    private final String userId;

    private final String tagId;

    private final String tagName;

    public String getTagName() {
        return tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public String getUserId() {
        return userId;
    }

    public UserTag(Builder builder)
    {
        this.userId = builder.userId;
        this.tagId = builder.tagId;
        this.tagName = builder.tagName;
    }

    public static class Builder{

        private String userId;

        private String tagId;

        private String tagName;

        public Builder userId(String userId){
            this.userId = userId;
            return this;
        }

        public Builder tagId(String tagId){
            this.tagId = tagId;
            return this;
        }

        public Builder tagName(String tagName){
            this.tagName = tagName;
            return this;
        }

        public UserTag build(){
            return new UserTag(this);
        }
    }
}
