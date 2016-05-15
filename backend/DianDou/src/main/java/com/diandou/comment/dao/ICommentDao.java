package com.diandou.comment.dao;

import com.diandou.comment.entity.Comment;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/14.
 */
public interface ICommentDao {
    public List<Comment> getCommentsByVideo(String videoId);

    public boolean makeComment(Comment comment);


}
