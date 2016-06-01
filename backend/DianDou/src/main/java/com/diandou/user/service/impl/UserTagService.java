package com.diandou.user.service.impl;

import com.diandou.user.dao.IUserDao;
import com.diandou.user.dao.IUserTagDao;
import com.diandou.user.entity.TagInfo;
import com.diandou.user.entity.UserTag;
import com.diandou.user.service.IUserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 胡志洁 on 2016/5/18.
 */
@Service("UserTagService")
public class UserTagService implements IUserTagService {

    @Autowired
    private IUserTagDao userTagDao;

    @Override
    public Map<String,List<UserTag>> getUserTagsByUsers(List<String> userList) {

        List<UserTag> userTagList = this.userTagDao.getUserTagsByUsers(userList);

        Map<String,List<UserTag>> userTagMap = new HashMap<String, List<UserTag>>();

        for (UserTag userTag:userTagList) {
            if(userTagMap.containsKey(userTag.getUserId())){
                userTagMap.get(userTag.getUserId()).add(userTag);
            }
            else{
                List<UserTag> userTags =  new ArrayList<UserTag>();
                userTags.add(userTag);
                userTagMap.put(userTag.getUserId(),userTags);
            }
        }

        return userTagMap;
    }

    @Override
    public List<UserTag> getUserTagsByUserId(String userId) {
        return this.userTagDao.getUserTagsByUserId(userId);
    }

    @Transactional
    @Override
    public boolean updateUserTags(String tags, String userId) {
        String[] tagList = tags.split(",");
        this.removeUserTag(userId);
        return this.userTagDao.updateUserTags(Arrays.asList(tagList),userId);
    }

    @Override
    public List<TagInfo> getAllTag() {
        return this.userTagDao.getAllTag();
    }

    @Override
    public boolean removeUserTag(String userId) {
        return this.userTagDao.removeUserTag(userId);
    }
}
