package com.diandou.user.service;

import com.diandou.enumerable.FollowActionEnum;
import com.diandou.user.entity.FriendCount;

import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IUserFriendshipService {

    public List<String> getFriendIdList(String userId);

    public int getFriendCount(String userId);

    public Map<String,Integer> getFriendCounts(List<String> userIds);

    public boolean follow(String selfId, String targeId, FollowActionEnum followAction);

}
