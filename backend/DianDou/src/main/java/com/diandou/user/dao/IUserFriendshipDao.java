package com.diandou.user.dao;

import com.diandou.user.entity.FriendCount;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IUserFriendshipDao {

    public int getFriendCount(String userId);

    public List<FriendCount> getFriendCounts(List<String> userIds);


    public boolean follow(String selfId,String targeId);

}
