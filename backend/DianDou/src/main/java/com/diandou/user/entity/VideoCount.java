package com.diandou.user.entity;

/**
 * Created by 胡志洁 on 2016/5/19.
 */
public class VideoCount {

    private String userId;

    private int videoCount;

    public VideoCount(String userId, int videoCount) {
        this.userId = userId;
        this.videoCount = videoCount;
    }

    public String getUserId() {
        return userId;
    }

    public int getVideoCount() {
        return videoCount;
    }

}
