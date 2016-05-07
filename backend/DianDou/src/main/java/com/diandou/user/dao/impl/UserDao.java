package com.diandou.user.dao.impl;

import com.diandou.common.option.PagenationOption;
import com.diandou.user.dao.IUserDao;
import com.diandou.user.entity.User;
import com.diandou.user.mapper.UserMapper;
import com.diandou.video.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Repository("UserDao")
public class UserDao implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUserListByRole(String pageIdx,String pageSize,String roleId) {

        List<User> userList = null;

        String sql = "SELECT i.user_id," +
                " i.user_name," +
                " i.head_portrait," +
                " i.brief," +
                " i.mobile " +
                " FROM dat_user_info i,dat_user_role r " +
                " where r.role_id = ? " +
                " and i.user_id = r.user_id " +
                " order by i.user_id " +
                new PagenationOption(pageSize,pageIdx).genOptionCode();

        userList = jdbcTemplate.query(sql,new Object[]{roleId} , new UserMapper());

        return userList;
    }
}
