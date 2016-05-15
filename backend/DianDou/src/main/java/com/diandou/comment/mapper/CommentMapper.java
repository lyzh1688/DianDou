package com.diandou.comment.mapper;
import com.diandou.comment.entity.Comment;
import com.diandou.user.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by 胡志洁 on 2016/5/15.
 */
public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment obj = new Comment.Builder()
                .userId(resultSet.getString("user_id"))
                .userName(resultSet.getString("user_name"))
                .videoId(resultSet.getString("video_id"))
                .comment(resultSet.getString("comment"))
                .time(resultSet.getTimestamp("time"))
                .build();

        return obj;
    }
}
