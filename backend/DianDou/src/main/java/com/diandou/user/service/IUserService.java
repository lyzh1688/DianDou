package com.diandou.user.service;

import com.diandou.user.entity.User;
import com.diandou.user.vmodel.UserModel;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IUserService {
    public List<UserModel> getUserListByRole(String pageIdx, String pageSize, String roleId);

}
