package com.diandou.user.entity;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class FriendCount {

    private String userId;

    private int friendCount;

    public FriendCount(String userId, int friendCount) {
        this.userId = userId;
        this.friendCount = friendCount;
    }

    public String getUserId() {
        return userId;
    }

    public int getFriendCount() {
        return friendCount;
    }


}
