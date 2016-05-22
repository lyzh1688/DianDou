package com.diandou.comment.service;

import com.diandou.comment.entity.Comment;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/15.
 */
public interface ICommentService {

    public List<Comment> getCommentsByVideo(String pageIdx, String pageSize,String videoId);

    public boolean makeComment(Comment comment);
}
