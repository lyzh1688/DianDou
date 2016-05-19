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
    public boolean updateUserInfo(User user);
    public List<User> getUserListByRole(String pageIdx,String pageSize,String roleId);
    public String userLogin(String mobile,String password);
    public AuthStatusEnum userRegister(User user);
}
