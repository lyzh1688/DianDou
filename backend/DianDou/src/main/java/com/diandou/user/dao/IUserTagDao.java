package com.diandou.user.dao;

import com.diandou.user.entity.TagInfo;
import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/18.
 */
public interface IUserTagDao {

    List<TagInfo> getAllTag();

    List<UserTag> getUserTagsByUsers(List<String> userList);

    List<UserTag> getUserTagsByUserId(String userId);

    boolean updateUserTags(List<String> tagList,String userId);

    boolean removeUserTag(String userId);


}
