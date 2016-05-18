package com.diandou.user.dao.impl;

import com.diandou.common.option.InOption;
import com.diandou.user.dao.IUserTagDao;
import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;
import com.diandou.user.mapper.UserTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/18.
 */
@Repository("UserTagDao")
public class UserTagDao implements IUserTagDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserTag> getUserTagsByUsers(List<String> userList) {
        if(userList == null || userList.size() == 0){
            return new ArrayList<UserTag>();
        }

        List<UserTag> userTagList = null;

        String sql = " select t.user_id," +
                " t.tag_id," +
                " p.tag_name " +
                " from dat_user_tag t,prm_user_tag p " +
                " where t.tag_id = p.tag_id " +
                " and t.user_id in " +
                new InOption(userList).genOptionCode();

        userTagList = this.jdbcTemplate.query(sql,new UserTagMapper());

        return userTagList;
    }
}
