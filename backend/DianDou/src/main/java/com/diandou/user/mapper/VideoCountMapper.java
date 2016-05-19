package com.diandou.user.mapper;

import com.diandou.user.entity.FriendCount;
import com.diandou.user.entity.VideoCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 胡志洁 on 2016/5/19.
 */
public class VideoCountMapper implements RowMapper<VideoCount> {
    @Override
    public VideoCount mapRow(ResultSet resultSet, int i) throws SQLException {

        VideoCount obj = new VideoCount(resultSet.getString("owner_id"),resultSet.getInt("count"));

        return obj;
    }
}