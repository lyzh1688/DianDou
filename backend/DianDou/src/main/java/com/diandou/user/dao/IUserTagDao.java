package com.diandou.user.dao;

import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/18.
 */
public interface IUserTagDao {
    List<UserTag> getUserTagsByUsers(List<String> userList);
}
