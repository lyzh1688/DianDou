package com.diandou.user.mapper;

import com.diandou.user.entity.User;
import com.diandou.video.entity.Video;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        // TODO Auto-generated method stub

        User obj = new User.Builder()
                .userId(rs.getString("user_id"))
                .userName(rs.getString("user_name"))
                .brief(rs.getString("brief"))
                .headPortrait(rs.getString("head_portrait"))
                .mobile(rs.getString("mobile"))
                .sex(rs.getString("sex"))
                .build();

        return obj;
    }

}
