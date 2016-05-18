package com.diandou.user.service.impl;

import com.diandou.authority.service.IAuthorityService;
import com.diandou.authority.vmodel.AuthModel;
import com.diandou.common.Authority.EncodePassword;
import com.diandou.enumerable.AuthStatusEnum;
import com.diandou.user.dao.IUserDao;
import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;
import com.diandou.user.service.IUserFriendshipService;
import com.diandou.user.service.IUserService;
import com.diandou.user.service.IUserTagService;
import com.diandou.user.vmodel.UserModel;
import com.diandou.video.service.IVideoService;
import com.diandou.video.vmodel.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private IAuthorityService authorityService;

    @Autowired
    private IUserTagService userTagService;

    @Override
    public User getUserInfoByMobile(String mobile) {
        return this.userDao.getUserInfoByMobile(mobile);
    }

    @Override
    public boolean updateUserInfo(User user) {
        return this.userDao.updateUserInfo(user);
    }

    @Override
    public List<UserModel> getUserListByRole(String pageIdx, String pageSize, String roleId,String followerId) {

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

        //get the all the user which the login user followed
        List<String> friendIdList = this.userFriendshipService.getFriendIdList(followerId);

        //get all the tags of the user
        Map<String,List<UserTag>> userTagMap = this.userTagService.getUserTagsByUsers(userIdList);

        //merge all parts of the user info
        for (User user:userList){

            Integer friendCnt = 0;
            VideoModel latestUploadedVideo = null;
            boolean isFollowed = false;
            List<UserTag> userTags = null;
            if(friendCntMap.containsKey(user.getUserId())){
                friendCnt = friendCntMap.get(user.getUserId());
            }

            if(ownerVideoMap.containsKey(user.getUserId())){
                latestUploadedVideo = ownerVideoMap.get(user.getUserId());
            }

            if(friendIdList.contains(user.getUserId())){
                isFollowed = true;
            }

            if(userTagMap.containsKey(user.getUserId())){
                userTags = userTagMap.get(user.getUserId());
            }
            else{
                userTags = new ArrayList<UserTag>();
            }

            userModelList.add(new UserModel.Builder()
                    .user(user)
                    .friendCount(friendCnt)
                    .latestVideo(latestUploadedVideo)
                    .isFollowed(isFollowed)
                    .tagList(userTags)
                    .build());
        }

        return userModelList;
    }

    @Override
    public AuthModel userRegister(String mobile, String pswd) {

        String password = EncodePassword.encodePassword(pswd);
        User newUser = new User.Builder().mobile(mobile).password(password).build();
        if (this.userDao.userRegister(newUser) == AuthStatusEnum.reg_success){
            return this.authorityService.loginAuthority(mobile,pswd);
        }
        else{
            return new AuthModel.Builder().authStatus(AuthStatusEnum.login_fail).build();
        }
    }
}
