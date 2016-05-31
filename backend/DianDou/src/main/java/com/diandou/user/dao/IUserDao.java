package com.diandou.user.dao;

import com.diandou.enumerable.AuthStatusEnum;
import com.diandou.user.entity.User;
import com.diandou.user.entity.VideoCount;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IUserDao {

    public List<VideoCount> getVideoCounts(List<String> userIds);
    public User getUserInfoById(String userId);
    public User getUserInfoByMobile(String mobile);
    public boolean updateUserName(String userName,String userId);
    public boolean updateUserSex(String sex,String userId);
    public boolean updateUserBrief(String brief,String userId);
    public List<User> getUserListByRole(String pageIdx,String pageSize,String roleId);
    public List<User> getUserListByName(String pageIdx,String pageSize,String userName);
    public String userLogin(String mobile,String password);
    public AuthStatusEnum userRegister(User user);
}
