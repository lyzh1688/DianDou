package com.diandou.user.service.impl;

import com.diandou.authority.service.IAuthorityService;
import com.diandou.authority.vmodel.AuthModel;
import com.diandou.common.Authority.EncodePassword;
import com.diandou.common.Authority.TokenContainer;
import com.diandou.common.util.StringUtil;
import com.diandou.enumerable.AuthStatusEnum;
import com.diandou.user.dao.IUserDao;
import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;
import com.diandou.user.entity.VideoCount;
import com.diandou.user.service.IUserFriendshipService;
import com.diandou.user.service.IUserService;
import com.diandou.user.service.IUserTagService;
import com.diandou.user.vmodel.UserModel;
import com.diandou.video.service.IVideoService;
import com.diandou.video.vmodel.VideoModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<String,Integer> getVideoCounts(List<String> userIds) {

        List<VideoCount> videoCounts = this.userDao.getVideoCounts(userIds);

        return  videoCounts.stream().collect(Collectors.toMap(VideoCount::getUserId,VideoCount::getVideoCount));
    }

    @Override
    public User getUserInfoByMobile(String mobile) {
        return this.userDao.getUserInfoByMobile(mobile);
    }

    @Override
    public boolean updateUserName(String userName, String userId) {
        return this.userDao.updateUserName(userName,userId);
    }

    @Override
    public boolean updateUserSex(String sex, String userId) {
        return this.userDao.updateUserSex(sex,userId);
    }

    @Override
    public boolean updateUserBrief(String brief, String userId) {
        return this.userDao.updateUserBrief(brief,userId);
    }

    @Override
    public List<UserModel> getUserListByRole(String pageIdx, String pageSize, String roleId,String followerId) {

        //get the user by role
        List<User> userList = this.userDao.getUserListByRole(pageIdx,pageSize,roleId);

        return this.getUserModelList(userList,followerId);
    }

    @Override
    public List<UserModel> getUserListByName(String pageIdx, String pageSize, String userName,String followerId) {
        //get the user by role
        List<User> userList = this.userDao.getUserListByName(pageIdx,pageSize,userName);

        return this.getUserModelList(userList,followerId);
    }

    @Override
    public AuthModel userRegister(String mobile, String pswd) {

        String password = EncodePassword.encodePassword(pswd);
        User newUser = new User.Builder().mobile(mobile).password(password).build();
        AuthStatusEnum registerRetStatus = this.userDao.userRegister(newUser);
        if ( registerRetStatus.equals(AuthStatusEnum.reg_success)){

            return this.authorityService.loginAuthority(mobile,pswd);
        }
        else{
            return new AuthModel.Builder().authStatus(registerRetStatus).build();
        }
    }

    @Override
    public User getUserInfoById(String userId) {
        if(StringUtil.isNullOrEmpty(userId)){
            return null;
        }

        return this.userDao.getUserInfoById(userId);
    }

    private List<UserModel> getUserModelList(List<User> userList,String followerId){

        List<UserModel> userModelList = new ArrayList<UserModel>();

        //get the user by role
        //List<User> userList = this.userDao.getUserListByRole(pageIdx,pageSize,roleId);

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
}
