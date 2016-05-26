package com.diandou.user.service.impl;

import com.diandou.enumerable.FollowActionEnum;
import com.diandou.user.dao.IUserFriendshipDao;
import com.diandou.user.entity.FriendCount;
import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;
import com.diandou.user.service.IUserFriendshipService;
import com.diandou.user.service.IUserService;
import com.diandou.user.service.IUserTagService;
import com.diandou.user.vmodel.UserModel;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Service("UserFriendshipService")
public class UserFriendshipService implements IUserFriendshipService{

    @Autowired
    private IUserFriendshipDao userFriendshipDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserTagService userTagService;

    @Override
    public List<String> getFriendIdList(String userId) {
        return this.userFriendshipDao.getFriendIdList(userId);
    }

    @Override
    public int getFriendCount(String userId) {
        return this.userFriendshipDao.getFriendCount(userId);
    }

    @Override
    public Map<String,Integer> getFriendCounts(List<String> userIds) {

        List<FriendCount> friendCountList =  this.userFriendshipDao.getFriendCounts(userIds);

        return friendCountList.stream().collect(Collectors.toMap(FriendCount::getUserId,FriendCount::getFriendCount));

//        Map<String,Integer> userFriendCountMap = new HashMap<String, Integer>();
//
//        for (FriendCount friendCount: friendCountList) {
//            userFriendCountMap.put(friendCount.getUserId(),friendCount.getFriendCount());
//        }
//
//        return userFriendCountMap;

    }

    @Override
    public boolean follow(String selfId, String targeId,FollowActionEnum followAction) {
        return this.userFriendshipDao.follow(selfId,targeId, followAction);
    }

    @Override
    public List<UserModel> getFriendsByUserId(String pageIdx, String pageSize, String userId) {

        List<User> userList = this.userFriendshipDao.getFriendsByUserId(pageIdx,pageSize,userId);

        List<String> userIdList = userList.stream().map(user->user.getUserId()).collect(Collectors.toList());

        List<String> userIds = userList.stream().map(user->user.getUserId()).collect(Collectors.toList());

        Map<String,Integer> userVideoMap = this.userService.getVideoCounts(userIds);

        Map<String,List<UserTag>> usersTags = userTagService.getUserTagsByUsers(userIds);

        Map<String,Integer> friendCntMap = this.getFriendCounts(userIdList);

        return userList.stream().map(user->new UserModel.Builder()
                                                .user(user)
                                                .isFollowed(true)
                                                .friendCount(friendCntMap.get(user.getUserId()))
                                                .tagList(usersTags.get(user.getUserId()))
                                                .videoCount(userVideoMap.get(user.getUserId()))
                                                .build()).collect(Collectors.toList());
    }

}
