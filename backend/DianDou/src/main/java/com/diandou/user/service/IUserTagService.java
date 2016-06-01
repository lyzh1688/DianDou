package com.diandou.user.service;

import com.diandou.user.entity.TagInfo;
import com.diandou.user.entity.UserTag;

import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/18.
 */
public interface IUserTagService {
    List<TagInfo> getAllTag();
    Map<String,List<UserTag>> getUserTagsByUsers(List<String> userList);
    List<UserTag> getUserTagsByUserId(String userId);
    boolean updateUserTags(String tagList,String userId);
    boolean removeUserTag(String userId);
}
