package com.diandou.user.dao;

import com.diandou.user.entity.User;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IUserDao {
    public List<User> getUserListByRole(String pageIdx,String pageSize,String roleId);
}
