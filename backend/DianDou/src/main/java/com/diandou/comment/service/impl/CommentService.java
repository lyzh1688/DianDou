package com.diandou.comment.service.impl;

import com.diandou.comment.dao.ICommentDao;
import com.diandou.comment.entity.Comment;
import com.diandou.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/15.
 */
@Service("CommentService")
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDao commentDao;

    @Override
    public List<Comment> getCommentsByVideo(String pageIdx, String pageSize,String videoId) {
        return this.commentDao.getCommentsByVideo(pageIdx,pageSize,videoId);
    }

    @Override
    public boolean makeComment(Comment comment) {
        return this.commentDao.makeComment(comment);
    }
}
