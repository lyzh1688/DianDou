package com.diandou.user.dao.impl;

import com.diandou.common.option.InOption;
import com.diandou.user.dao.IUserTagDao;
import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;
import com.diandou.user.mapper.UserTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @Override
    public boolean updateUserTags(List<String> tagList, String userId) {

        String sql = " insert into dat_user_tag(user_id,tag_id) values(?,?) ";

        int[] res = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i)throws SQLException
            {
                String tagId = tagList.get(i);
                ps.setString(1, userId);
                ps.setString(2, tagId);
            }

            public int getBatchSize()
            {
                return tagList.size();
            }
        });

        return res.length != 0;
    }

    @Override
    public boolean removeUserTag(String userId) {

        String sql = " delete from dat_user_tag where user_id = ? ";

        int affectedRows = this.jdbcTemplate.update(sql,userId);

        return affectedRows >= 0;
    }


}
