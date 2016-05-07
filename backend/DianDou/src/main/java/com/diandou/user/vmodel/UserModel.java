package com.diandou.user.vmodel;

import com.diandou.user.entity.User;
import com.diandou.video.entity.Video;
import com.diandou.video.vmodel.VideoModel;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class UserModel {

    private final User user;

    private final int friendCount;

    private final VideoModel latestVideo;

    public UserModel(Builder builder) {

        this.user = builder.user;
        this.friendCount = builder.friendCount;
        this.latestVideo = builder.latestVideo;
    }

    public User getUser() {
        return user;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public VideoModel getLatestVideo() {
        return latestVideo;
    }

    public static class Builder{

        private User user;

        private int friendCount;

        private VideoModel latestVideo;

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder friendCount(int friendCount){
            this.friendCount = friendCount;
            return this;
        }

        public Builder latestVideo(VideoModel latestVideo){
            this.latestVideo = latestVideo;
            return this;
        }
        public UserModel build(){
            return new UserModel(this);
        }
    }
}
