package com.diandou.user.service;

import com.diandou.authority.vmodel.AuthModel;
import com.diandou.user.entity.TagInfo;
import com.diandou.user.entity.User;
import com.diandou.user.entity.VideoCount;
import com.diandou.user.vmodel.UserModel;

import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IUserService {

    public Map<String,Integer> getVideoCounts(List<String> userIds);

    public User getUserInfoByMobile(String mobile);

    public boolean updateUserName(String userName,String userId);

    public boolean updateUserSex(String sex,String userId);

    public boolean updateUserBrief(String brief,String userId);

    public List<UserModel> getUserListByRole(String pageIdx, String pageSize, String roleId,String userId);

    public List<UserModel> getUserListByName(String pageIdx, String pageSize, String userName,String userId);

    public AuthModel userRegister( String mobile, String pswd);

    public User getUserInfoById(String userId);

    public UserModel getUserModelById(String userId,String followerId);
}
