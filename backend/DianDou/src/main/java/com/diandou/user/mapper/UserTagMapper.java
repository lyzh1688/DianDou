package com.diandou.user.mapper;

import com.diandou.user.entity.User;
import com.diandou.user.entity.UserTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 胡志洁 on 2016/5/18.
 */
public class UserTagMapper implements RowMapper<UserTag> {

    @Override
    public UserTag mapRow(ResultSet rs, int rowNum) throws SQLException {

        // TODO Auto-generated method stub

        UserTag obj = new UserTag.Builder()
                            .tagId(rs.getString("tag_id"))
                            .userId(rs.getString("user_id"))
                            .tagName(rs.getString("tag_name"))
                            .build();

        return obj;
    }

}
