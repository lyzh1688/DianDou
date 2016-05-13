package com.diandou.user.service;

import com.diandou.authority.vmodel.AuthModel;
import com.diandou.user.entity.User;
import com.diandou.user.vmodel.UserModel;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IUserService {

    public User getUserInfoByMobile(String mobile);

    public boolean updateUserInfo(User user);

    public List<UserModel> getUserListByRole(String pageIdx, String pageSize, String roleId,String userId);

    public AuthModel userRegister( String mobile, String pswd);
}
