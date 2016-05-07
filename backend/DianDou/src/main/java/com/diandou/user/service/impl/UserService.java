package com.diandou.user.service.impl;

import com.diandou.user.dao.IUserDao;
import com.diandou.user.entity.FriendCount;
import com.diandou.user.entity.User;
import com.diandou.user.service.IUserFriendshipService;
import com.diandou.user.service.IUserService;
import com.diandou.user.vmodel.UserModel;
import com.diandou.video.entity.Video;
import com.diandou.video.service.IVideoService;
import com.diandou.video.vmodel.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Service("UserService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserFriendshipService userFriendshipService;

    @Autowired
    private IVideoService videoService;

    @Override
    public List<UserModel> getUserListByRole(String pageIdx, String pageSize, String roleId) {

        List<UserModel> userModelList = new ArrayList<UserModel>();

        //get the user by role
        List<User> userList = this.userDao.getUserListByRole(pageIdx,pageSize,roleId);

        List<String> userIdList = new ArrayList<String>();


        for (User user: userList) {
            userIdList.add(user.getUserId());
        }

        //get the friend count by user id list
        Map<String,Integer> friendCntMap = this.userFriendshipService.getFriendCounts(userIdList);

        //get the latest uploaded video by user id list
        Map<String,VideoModel> ownerVideoMap = this.videoService.getLatestVideoListByOwnerList(userIdList);

        //merge all parts of the user info
        for (User user:userList){

            Integer friendCnt = 0;
            VideoModel latestUploadedVideo = null;

            if(friendCntMap.containsKey(user.getUserId())){
                friendCnt = friendCntMap.get(user.getUserId());
            }

            if(ownerVideoMap.containsKey(user.getUserId())){
                latestUploadedVideo = ownerVideoMap.get(user.getUserId());
            }

            userModelList.add(new UserModel.Builder()
                    .user(user)
                    .friendCount(friendCnt)
                    .latestVideo(latestUploadedVideo)
                    .build());
        }

        return userModelList;
    }
}
