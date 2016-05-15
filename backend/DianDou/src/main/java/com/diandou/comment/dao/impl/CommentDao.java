package com.diandou.comment.dao.impl;

import com.diandou.comment.dao.ICommentDao;
import com.diandou.comment.entity.Comment;
import com.diandou.comment.mapper.CommentMapper;
import com.diandou.user.entity.User;
import com.diandou.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/14.
 */
@Repository("CommentDao")
public class CommentDao implements ICommentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> getCommentsByVideo(String videoId) {
        String sql = "select t.video_id,t.user_id,u.user_name,t.comments,t.time " +
                " from dat_video_comments t,dat_user_info u " +
                " where t.user_id = u.user_id " +
                " and t.video_id = ? " +
                " order by t.time desc ";

        List<Comment> comments = this.jdbcTemplate.query(sql ,new Object[] { videoId }, new CommentMapper() );

        return comments;
    }

    @Override
    public boolean makeComment(Comment comment) {

        String sql = "insert into dat_video_comments(video_id,user_id,comments,time) values (?,?,?,?) ";

        int affectedRows = this.jdbcTemplate.update(sql,comment.getVideoId(),comment.getUserId(),comment.getComment(),comment.getTime());

        return affectedRows != 0;

    }
}
