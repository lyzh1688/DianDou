package com.diandou.user.service.impl;

import com.diandou.user.dao.IUserFriendshipDao;
import com.diandou.user.entity.FriendCount;
import com.diandou.user.service.IUserFriendshipService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Service("UserFriendshipService")
public class UserFriendshipService implements IUserFriendshipService{

    @Autowired
    private IUserFriendshipDao userFriendshipDao;

    @Override
    public int getFriendCount(String userId) {
        return this.userFriendshipDao.getFriendCount(userId);
    }

    @Override
    public Map<String,Integer> getFriendCounts(List<String> userIds) {

        List<FriendCount> friendCountList =  this.userFriendshipDao.getFriendCounts(userIds);

        Map<String,Integer> userFriendCountMap = new HashMap<String, Integer>();

        for (FriendCount friendCount: friendCountList) {
            userFriendCountMap.put(friendCount.getUserId(),friendCount.getFriendCount());
        }

        return userFriendCountMap;
    }


}
