package com.diandou.comment.entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by 胡志洁 on 2016/5/14.
 */
public class Comment {
    private final String videoId;

    private final String userId;

    private final String userName;

    private final String comment;

    private final String headPortrait;

    private final Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    private Comment(Builder builder){
        this.videoId = builder.videoId;
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.comment = builder.comment;
        this.time = builder.time;
        this.headPortrait = builder.headPortrait;
    }

    public static class Builder{

        private String videoId;

        private String userId;

        private String userName;

        private String comment;

        private Timestamp time;

        private String headPortrait;

        public Builder headPortrait(String headPortrait){
            this.headPortrait = headPortrait;
            return this;
        }

        public Builder time(Timestamp time){
            this.time = time;
            return this;
        }

        public Builder videoId(String videoId){
            this.videoId = videoId;
            return this;
        }

        public Builder userId(String userId){
            this.userId = userId;
            return this;
        }

        public Builder userName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }

        public Comment build(){
            return new Comment(this);
        }
    }
}
