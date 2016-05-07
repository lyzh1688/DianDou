package com.diandou.user.mapper;

import com.diandou.user.entity.FriendCount;
import com.diandou.user.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class FriendCountMapper implements RowMapper<FriendCount> {
    @Override
    public FriendCount mapRow(ResultSet resultSet, int i) throws SQLException {

        FriendCount obj = new FriendCount(resultSet.getString("user_id"),resultSet.getInt("count"));

        return obj;
    }
}
